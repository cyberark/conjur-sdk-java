package org.conjur.sdk.endpoint;

import org.conjur.sdk.*;
import org.conjur.sdk.auth.*;
import org.junit.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.*;

public class ConfiguredTest {
    protected ApiClient client;
    protected ApiClient nonAuthClient;
    protected String login;
    protected static String account = System.getenv("CONJUR_ACCOUNT");
    protected HttpBasicAuth basicAuth;
    protected ApiKeyAuth conjurAuth;

    private static final String OIDC_POLICY_FILE = "/config/oidc-webservice.yml";
    private static final String DEFAULT_POLICY_FILE = "/config/policy.yaml";

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

    public static void setupOIDCWebservice() throws ApiException {
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

    private String getApiKey(String username, String role) throws ApiException {
        if (username.equals("admin"))
            return System.getenv().getOrDefault("CONJUR_AUTHN_API_KEY", null);
        AuthenticationApi authApi = new AuthenticationApi();
        String apiKey = authApi.rotateApiKey(account, String.format("%s:%s", role, username), null);
        return apiKey;
    }

    public ApiClient getApiClient(String username, String role) throws ApiException {
        String apiKey = getApiKey(username, role);

        if (role.equals("host"))
            username = "host/" + username;

        ApiClient newClient = new ApiClient();
        newClient.setUsername(username);
        newClient.setPassword(apiKey);
        newClient.setApiKey(apiKey);
        newClient.setSslCaCert(newClient.getCertInputStream());
        ApiKeyAuth conjurAuth = (ApiKeyAuth) newClient.getAuthentication("conjurAuth");
        conjurAuth.setApiKeyPrefix("Token");
        return newClient;
    }

    public ApiClient getApiClient(String username) throws ApiException {
        return getApiClient(username, "user");
    }

    @AfterClass
    public static void loadDefaultPolicy() throws ApiException, IOException {
        PoliciesApi policiesApi = new PoliciesApi();
        List<String> lines = Files.readAllLines(Paths.get(DEFAULT_POLICY_FILE));
        String policyText = String.join(System.lineSeparator(), lines);
        policiesApi.replacePolicy(account, "root", policyText, null);
    }

    @BeforeClass
    public static void setUpClass() throws ApiException{
        ApiClient client = Configuration.getDefaultApiClient();
        client.setSslCaCert(client.getCertInputStream());
        setupClientAuth();
    }

    @Before
    public void setUp() throws ApiException, IOException {
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
