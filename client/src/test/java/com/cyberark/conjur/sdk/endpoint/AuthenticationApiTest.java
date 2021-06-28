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
import com.cyberark.conjur.sdk.auth.*;
import com.cyberark.conjur.sdk.endpoint.AuthenticationApi;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * API tests for AuthenticationApi.
 */
public class AuthenticationApiTest extends ConfiguredTest {

    private AuthenticationApi api;
    private AuthenticationApi badAuthApi;


    /**
     * Set up Authentication API clients to test.
     */
    @Before
    public void setUpClients() {
        api = new AuthenticationApi();
        basicAuth = (HttpBasicAuth) client.getAuthentication("basicAuth");
        basicAuth.setUsername(login);
        basicAuth.setPassword(apiKey());

        badAuthApi = new AuthenticationApi(nonAuthClient);
    }

    /**
     * Reset basic auth password after each test.
     */
    @After
    public void tearDown() {
        basicAuth = (HttpBasicAuth) client.getAuthentication("basicAuth");
        basicAuth.setUsername(login);
        basicAuth.setPassword(apiKey());
    }

    /**
     * Get the API key admin user's API key from an env var.
     *
     * @return API key
     */
    public String apiKey() {
        return System.getenv("CONJUR_AUTHN_API_KEY");
    }

    /**
     * Test changing a user’s password.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void changePasswordTest204() throws ApiException {
        String newPassword = "@Sup3rS3cr3t@";
        ApiResponse response = api.changePasswordWithHttpInfo(account, newPassword);
        Assert.assertEquals(response.getStatusCode(), 204);

        basicAuth.setPassword(newPassword);
        response = api.getAPIKeyWithHttpInfo(account);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /**
     * Test 400 response when changing a user's password.
     */
    @Test
    public void changePasswordTest400() {
        String newPassword = "@Sup3rS3cre3t@";

        try {
            api.changePassword("\0", newPassword);
        } catch (ApiException e) {
            Assert.assertEquals(400, e.getCode());
        }
    }

    /**
     * Test 401 response when changing a user's password.
     */
    @Test
    public void changePasswordTest401() {
        String newPassword = "@Sup3rS3cre3t@";
        String invalidPassword = "SomethingInvalid";

        // change password with bad credentials
        try {
            badAuthApi.changePassword(account, newPassword);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Test 422 response when changing a user's password.
     */
    @Test
    public void changePasswordTest422() {
        String invalidPassword = "SomethingInvalid";

        try {
            api.changePassword(account, invalidPassword);
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }
    }

    /**
     * Tests getting the API key of a user given the
     * username and password via HTTP Basic Authentication.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getApiKeyTest200() throws ApiException {
        String response = api.getAPIKey(account);

        Assert.assertEquals(response, apiKey());
    }

    /**
     * Test 400 response when getting a user's API key.
     */
    @Test
    public void getApiKeyTest400() {
        try {
            api.getAPIKey("\0");
        } catch (ApiException e) {
            Assert.assertEquals(400, e.getCode());
        }
    }

    /**
     * Test 401 response when getting a user's API key.
     */
    @Test
    public void getApiKeyTest401() {
        try {
            badAuthApi.getAPIKey(account);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Test 422 response when getting a user's API key.
     */
    @Ignore("Depricated because we force usage of https now")
    @Test
    public void getApiKeyTest422() {
        ApiClient client = api.getApiClient();
        client.setBasePath("http://conjur");

        try {
            api.getAPIKey(account);
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }

        client.setBasePath("https://conjur-https");
    }

    /**
     * Tests getting an API token for a given user.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAccessTokenTest200() throws ApiException {
        String response = api.getAccessToken(account, login, apiKey());

        String[] keys = {
            "protected", "payload", "signature"
        };
        Gson gson = new Gson();
        Map<?, ?> map = gson.fromJson(response, Map.class);

        for (int i = 0; i < keys.length; i++) {
            Assert.assertTrue(map.keySet().contains(keys[i]));
        }
    }

    /**
     * Tests 400 response when getting a user's access token.
     */
    @Test
    public void getAccessTokenTest400() {
        try {
            api.getAccessToken(account, "\0", apiKey());
        } catch (ApiException e) {
            Assert.assertEquals(400, e.getCode());
        }
    }

    /**
     * Tests 401 response when getting a user's access token.
     */
    @Test
    public void getAccessTokenTest401() { 
        try {
            badAuthApi.getAccessToken(account, login, apiKey());
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Tests rotating the current role's api key.
     *
     * @throws ApiException
     *          if the api call fails
     */
    @Ignore("Causes issues with other tests - Java doesn't allow setting Environment variables")
    @Test
    public void rotateApiKeyTest200Self() throws ApiException {
        ApiResponse<String> response = api.rotateApiKeyWithHttpInfo(account);
        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Test rotating another role's api key.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void rotateApiKeyTest200Other() throws ApiException {
        String role = "user:alice";
        String requestId = null;

        ApiResponse response = api.rotateApiKeyWithHttpInfo(account, role, requestId);
        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Test 400 response when rotating the current role's api key.
     */
    @Test
    public void rotateApiKeyTest400() {
        try {
            api.rotateApiKey("\0");
        } catch (ApiException e) {
            Assert.assertEquals(400, e.getCode());
        }
    }

    /**
     * Test 401 response when rotating the current role's api key.
     */
    @Test
    public void rotateApiKeyTest401() {
        try {
            badAuthApi.rotateApiKey(account);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Test 422 response when rotating the current role's api key.
     */
    @Test
    public void rotateApiKeyTest422() {
        try {
            api.rotateApiKey(account, "\0", null);
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }
    }
}
