/*
 * Conjur
 * This is an API definition for CyberArk Conjur OSS. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.1.0
 * Contact: conj_maintainers@cyberark.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.cyberark.conjur.sdk.endpoint;

import com.cyberark.conjur.sdk.*;
import com.cyberark.conjur.sdk.ApiException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 * API tests for SecretsApi.
 */
public class SecretsApiTest extends ConfiguredTest {

    private SecretsApi api;
    private SecretsApi badAuthApi;
    private static Map<String, String> defaultSecrets;
    private final String[] testVariables = {"one/password", "testSecret"};
    private final String grantPolicy = String.join("\n",
            "- !permit",
            "  role: !user alice",
            "  privileges: [ read ]",
            String.format("  resource: !variable %s", testVariables[0]));
    private SecretsApi aliceApi;


    /**
     * Creates two variables in Conjur for testing secret retrieval.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Before
    public void setDefaultSecrets() throws ApiException {
        badAuthApi = new SecretsApi(nonAuthClient);
        api = new SecretsApi();

        defaultSecrets = new HashMap<String, String>();
        defaultSecrets.put("testSecret", "testvalue");
        defaultSecrets.put("one/password", "testvalue2");
        ApiClient aliceClient = getApiClient("alice");
        aliceApi = new SecretsApi(aliceClient);

        for (String identifier : defaultSecrets.keySet()) {
            String secretValue = defaultSecrets.get(identifier);
            api.createSecret(account, "variable", identifier, null, null, secretValue);
        }

        PoliciesApi policiesApi = new PoliciesApi();
        policiesApi.updatePolicy(account, "root", grantPolicy);
    }

    /**
     * Creates a secret value within the specified variable. Test case for 200 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretTest200() throws ApiException {
        String kind = "variable";
        String identifier = "testSecret";
        String expirations = null;
        String requestId = null;
        ApiResponse<?> response = api.createSecretWithHttpInfo(
            account,
            kind,
            identifier,
            expirations,
            requestId,
            defaultSecrets.get(identifier)
        );

        Assert.assertEquals(201, response.getStatusCode());
    }

    /**
     * Creates a secret value within the specified variable. Test case for 401 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretTest401() throws ApiException {
        String expirations = null;
        String requestId = null;
        try {
            badAuthApi.createSecret(
                    account,
                    "variable",
                    testVariables[0],
                    expirations,
                    requestId,
                    "testing");
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Creates a secret value within the specified variable. Test case for 403 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretTest403() throws ApiException {
        String expirations = null;
        String requestId = null;
        try {
            aliceApi.createSecret(
                    account,
                    "variable",
                    testVariables[0],
                    expirations,
                    requestId,
                    "testing");
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Creates a secret value within the specified variable. Test case for 422 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretTest422() throws ApiException {
        String expirations = null;
        String requestId = null;
        try {
            api.createSecret(account, "variable", testVariables[0], expirations, requestId, "");
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }
    }

    /**
     * Sets the expiration for the specified variable. Test case for 201 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretExpirationsTest201() throws ApiException {
        String requestId = null;
        String body = null;
        ApiResponse<?> response = api.createSecretWithHttpInfo(
                account,
                "variable",
                testVariables[0],
                "",
                requestId,
                body);

        Assert.assertEquals(201, response.getStatusCode());
    }

    /**
     * Sets the expiration for the specified variable. Test case for 401 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretExpirationsTest401() throws ApiException {
        String requestId = null;
        String body = null;
        try {
            badAuthApi.createSecretWithHttpInfo(
                    account,
                    "variable",
                    testVariables[0],
                    "",
                    requestId,
                    body);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Sets the expiration for the specified variable. Test case for 403 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretExpirationsTest403() throws ApiException {
        String requestId = null;
        String body = null;
        try {
            aliceApi.createSecretWithHttpInfo(
                    account,
                    "variable",
                    testVariables[0],
                    "",
                    requestId,
                    body);
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Sets the expiration for the specified variable. Test case for 404 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createSecretExpirationsTest404() throws ApiException {
        String requestId = null;
        String body = null;
        try {
            aliceApi.createSecretWithHttpInfo(account, "variable", "fakevar", "", requestId, body);
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }
    
    /**
     * Fetches the value of a secret from the specified Secret. Test case for 200 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretTest200() throws ApiException {
        String secretValue;
        for (String identifier : defaultSecrets.keySet()) {
            secretValue = defaultSecrets.get(identifier);
            String response = api.getSecret(account, "variable", identifier);
            Assert.assertEquals(secretValue, response);
        }
    }

    /**
     * Fetches the value of a secret from the specified Secret. Test case for 401 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretTest401() throws ApiException {
        try {
            badAuthApi.getSecret(account, "variable", testVariables[0]);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Fetches the value of a secret from the specified Secret. Test case for 403 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretTest403() throws ApiException {
        try {
            aliceApi.getSecret(account, "variable", testVariables[0]);
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Fetches the value of a secret from the specified Secret. Test case for 404 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretTest404() throws ApiException {
        try {
            api.getSecret(account, "variable", "nonexist");
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }

    /**
     * Fetches the value of a secret from the specified Secret. Test case for 422 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Ignore("Depricated because we now force https")
    @Test
    public void getSecretTest422() throws ApiException {
        client.setBasePath(System.getenv("CONJUR_HTTP_APPLIANCE_URL"));

        try {
            api.getSecret(account, "variable", "\0", 1, null);
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }

        client = client.setBasePath(System.getenv("APPLIANCE_URL"));
    }

    /**
     * Combines all testing secrets into one comma delimited string.
     */
    public String getDefaultSecretList() {
        String variableIds = "";
        String kind = "variable";
        String nextId;
        List<String> ids = new ArrayList<String>();
        for (String identifier : defaultSecrets.keySet()) {
            nextId = String.format("%s:%s:%s,", account, kind, identifier);
            variableIds = variableIds.concat(nextId);
            ids.add(nextId.substring(0, nextId.length()));
        }

        return variableIds.substring(0, variableIds.length());
    }
    
    /**
     * Fetch multiple secrets. Test case for 200 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretsTest200() throws ApiException {
        String variableIds = getDefaultSecretList();
        String[] ids = variableIds.split(",");

        Map<?, ?> response = (Map<?, ?>) api.getSecrets(variableIds);
        for (String identifier : ids) {
            String splitId = identifier.split(":")[2];

            Assert.assertEquals(defaultSecrets.get(splitId), response.get(identifier));
        }
    }

    /**
     * Fetch multiple secrets. Test case for 401 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretsTest401() throws ApiException {
        String variableIds = getDefaultSecretList();

        try {
            badAuthApi.getSecrets(variableIds);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Fetch multiple secrets. Test case for 403 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretsTest403() throws ApiException {
        String variableIds = getDefaultSecretList();

        try {
            aliceApi.getSecrets(variableIds);
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Fetch multiple secrets. Test case for 404 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretsTest404() throws ApiException {
        try {
            api.getSecrets("nonexist");
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }

    /**
     * Fetch multiple secrets. Test case for 422 response code
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSecretsTest422() throws ApiException {
        try {
            aliceApi.getSecrets("\0");
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }
    }

    @Test
    public void createBinarySecretTest() throws ApiException {
        byte[] byteData = new byte[]{1, 126, 10, 35};
        // Use this charset so we dont lose any data in the transition from bytes to string
        String testData = new String(byteData, StandardCharsets.ISO_8859_1);

        api.createSecret(account, "variable", "testSecret", null, null, testData);

        String result = api.getSecret(account, "variable", "testSecret");
        Assert.assertEquals(testData, result);

        byte[] resultBytes = result.getBytes();
        for (int i = 0; i < resultBytes.length; i++) {
            Assert.assertEquals(byteData[i], resultBytes[i]);
        }
    }
}