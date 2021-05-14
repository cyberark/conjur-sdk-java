package org.conjur.sdk.endpoint;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.conjur.sdk.*;
import org.conjur.sdk.auth.*;
import org.junit.*;

/**
 * Configured Test Superclass.
 * Provides test subclasses with authenticated clients and
 * default policy loading function.
 */
public class ConfiguredTest {

    protected ApiClient client;
    protected ApiClient nonAuthClient;
    protected String login;
    protected static String account = System.getenv("CONJUR_ACCOUNT");
    protected HttpBasicAuth basicAuth;
    protected ApiKeyAuth conjurAuth;

    private static final String OIDC_POLICY_FILE = "/config/oidc-webservice.yml";
    private static final String DEFAULT_POLICY_FILE = "/config/policy.yaml";

    /**
     * Set up API client with HttpBasicAuth then ApiKeyAuth.
     *
     * @throws ApiException
     *          if an Api call fails
     */
    private static void setupClientAuth() throws ApiException {
        String apiKey = System.getenv("CONJUR_AUTHN_API_KEY");
        ApiClient client = Configuration.getDefaultApiClient();
        String login = System.getenv("CONJUR_AUTHN_LOGIN");
        HttpBasicAuth basicAuth = (HttpBasicAuth) client.getAuthentication("basicAuth");
        basicAuth.setUsername(login);
        basicAuth.setPassword(apiKey);
        ApiKeyAuth conjurAuth = (ApiKeyAuth) client.getAuthentication("conjurAuth");
        conjurAuth.setApiKeyPrefix("Token");
    }

    /**
     * Loads a policy for the OIDC webservice into Conjur.
     *
     * @throws ApiException
     *          if an Api call fails
     * @throws IOException
     *          if policy file is malformed or I/O error occurs
     */
    public static void setupOidcWebservice() throws ApiException, IOException {
        PoliciesApi policiesApi = new PoliciesApi();
        SecretsApi secretsApi = new SecretsApi();
        try {
            List<String> lines = Files.readAllLines(Paths.get(OIDC_POLICY_FILE));
            String policyText = String.join(System.lineSeparator(), lines);
            policiesApi.updatePolicy(account, "root", policyText, null);

            secretsApi.createSecret(
                account,
                "variable",
                "conjur/authn-oidc/test/provider-uri",
                null,
                null,
                "https://keycloak:8443/auth/realms/master"
            );
            secretsApi.createSecret(
                account,
                "variable",
                "conjur/authn-oidc/test/id-token-user-property",
                null,
                null,
                "preferred_username"
            );
        } catch (IOException e) {
            Assert.fail("Failed to read OIDC webservice policy file");
        }
    }

    /**
     * Retrieves the given role's API key.
     *
     * @param username the role's identifier
     * @param role the role's kind (user, host, group, etc.)
     * @return the role's API key
     * @throws ApiException
     *          if an Api call fails
     */
    private String getApiKey(String username, String role) throws ApiException {
        if (username.equals("admin")) {
            return System.getenv().getOrDefault("CONJUR_AUTHN_API_KEY", null);
        }
        AuthenticationApi authApi = new AuthenticationApi();
        String apiKey = authApi.rotateApiKey(account, String.format("%s:%s", role, username), null);
        return apiKey;
    }

    /**
     * Sets up and returns an authenticated API client for the given role.
     * Can set up clients for users or hosts using 'role' parameter.
     *
     * @param username the role's identifier
     * @param role the role's kind (user, host, group, etc.)
     * @return new Api client for the given role
     * @throws ApiException
     *          if an Api call fails
     */
    public ApiClient getApiClient(String username, String role) throws ApiException {
        String apiKey = getApiKey(username, role);

        if (role.equals("host")) {
            username = "host/" + username;
        }

        ApiClient newClient = new ApiClient();
        newClient.setUsername(username);
        newClient.setPassword(apiKey);
        newClient.setApiKey(apiKey);
        newClient.setSslCaCert(newClient.getCertInputStream());
        ApiKeyAuth conjurAuth = (ApiKeyAuth) newClient.getAuthentication("conjurAuth");
        conjurAuth.setApiKeyPrefix("Token");
        return newClient;
    }

    /**
     * Sets up and returns an authenticated API client for the given user.
     *
     * @param username the user's identifier
     * @return new Api client for the given user
     * @throws ApiException
     *          if an Api call fails
     */
    public ApiClient getApiClient(String username) throws ApiException {
        return getApiClient(username, "user");
    }

    /**
     * Loads the default policy into Conjur.
     * Used to reset Conjur objects.
     *
     * @throws ApiException
     *          if an Api call fails
     * @throws IOException
     *          if policy file is malformed of I/O error occurs
     */
    @AfterClass
    public static void loadDefaultPolicy() throws ApiException, IOException {
        PoliciesApi policiesApi = new PoliciesApi();
        List<String> lines = Files.readAllLines(Paths.get(DEFAULT_POLICY_FILE));
        String policyText = String.join(System.lineSeparator(), lines);
        policiesApi.replacePolicy(account, "root", policyText, null);
    }

    /**
     * Set up API client with SSL cert and authentication schemes.
     *
     * @throws ApiException
     *          if an Api call fails
     */
    @BeforeClass
    public static void setUpClass() throws ApiException {
        ApiClient client = Configuration.getDefaultApiClient();
        client.setSslCaCert(client.getCertInputStream());
        setupClientAuth();
    }


    /**
     * Set up an API client with basic and Conjur auth.
     *
     * @throws ApiException
     *          if an Api call fails
     */
    @Before
    public void setUp() throws ApiException {
        client = Configuration.getDefaultApiClient();
        nonAuthClient = new ApiClient();
        nonAuthClient.setSslCaCert(nonAuthClient.getCertInputStream());
        ApiKeyAuth badAuth = (ApiKeyAuth) nonAuthClient.getAuthentication("conjurAuth");
        badAuth.setApiKey("");
        basicAuth = (HttpBasicAuth) client.getAuthentication("basicAuth");
        conjurAuth = (ApiKeyAuth) client.getAuthentication("conjurAuth");
        login = System.getenv("CONJUR_AUTHN_LOGIN");
    }
}
