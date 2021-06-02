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


package org.conjur.sdk.endpoint;

import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.conjur.sdk.*;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.endpoint.*;
import org.conjur.sdk.model.*;
import org.conjur.sdk.model.CreateHost;
import org.junit.*;

/**
 * API tests for HostFactoryApi.
 */
public class HostFactoryApiTest extends ConfiguredTest {

    private final HostFactoryApi api = new HostFactoryApi();
    private HostFactoryApi carlApi;
    private HostFactoryApi badAuthApi;

    private static final String HOST_FACTORY_ID = String.format(
        "%s:host_factory:testFactory",
        System.getenv("CONJUR_ACCOUNT"));

    private static final String FACTORY_POLICY = String.join("\n", "- !layer testLayer",
        "- !host_factory",
        "  id: testFactory",
        "  annotations:",
        "    description: Testing factory",
        "  layers: [ !layer testLayer ]",

        "- !user carl",
        "- !permit",
        "  role: !user carl",
        "  privileges: [ read ]",
        "  resource: !host_factory testFactory"
    );

    /**
     * Set up Conjur with Host Factory policy.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @BeforeClass
    public static void setUpClass() throws ApiException {
        ConfiguredTest.setUpClass();
        PoliciesApi policiesApi = new PoliciesApi();
        policiesApi.replacePolicy(System.getenv("CONJUR_ACCOUNT"), "root", FACTORY_POLICY);
    }

    /**
     * Set up the helper api instances.
     */
    @Before
    public void setUpApis() throws ApiException {
        badAuthApi = new HostFactoryApi(nonAuthClient);
        ApiClient carlClient = getApiClient("carl");
        carlApi = new HostFactoryApi(carlClient);
    }

    /**
     * Get a new token from the Host Factory.
     *
     * @return host token
     * @throws ApiException
     *          if the Api call fails
     */
    public String getHostToken() throws ApiException {
        String expiration = "2100-05-05";
        String hostFactory = HOST_FACTORY_ID;
        List<?> response = api.createToken(expiration, hostFactory);

        LinkedTreeMap result = (LinkedTreeMap) response.get(0);
        return (String) result.get("token");
    }

    /**
     * Creates a Host using the Host Factory. Test for 201 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createHostTest201() throws ApiException {
        String token = this.getHostToken();
        String oldApiKey = conjurAuth.getApiKey();
        conjurAuth.setApiKey(String.format("token=\"%s\"", token));

        String id = HOST_FACTORY_ID;

        ApiResponse<?> response = api.createHostWithHttpInfo(id);

        Assert.assertEquals(201, response.getStatusCode());
        conjurAuth.setApiKey(oldApiKey);
    }

    /**
     * Creates a Host using the Host Factory. Test for 401 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createHostTest401() throws ApiException {
        String token = "badtoken";
        String oldApiKey = conjurAuth.getApiKey();
        conjurAuth.setApiKey(String.format("token=\"%s\"", token));

        String id = HOST_FACTORY_ID;

        try {
            api.createHostWithHttpInfo(id);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }

        conjurAuth.setApiKey(oldApiKey);
    }

    /**
     * Creates a Host using the Host Factory. Test for 422 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createHostTest422() throws ApiException {
        String token = this.getHostToken();
        String oldApiKey = conjurAuth.getApiKey();
        conjurAuth.setApiKey(String.format("token=\"%s\"", token));

        String id = "bad_id";

        try {
            api.createHostWithHttpInfo(id);
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }

        conjurAuth.setApiKey(oldApiKey);
    }

    /**
     * Creates one or more host identity tokens. Test for 200 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTokenTest200() throws ApiException {
        String expiration = "2100-05-05";
        String hostFactory = HOST_FACTORY_ID;
        ApiResponse<List<Object>> response = api.createTokenWithHttpInfo(expiration, hostFactory);

        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Creates one or more host identity tokens. Test for 401 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTokenTest401() throws ApiException {
        String expiration = "2100-05-05";
        String hostFactory = HOST_FACTORY_ID;
        try {
            badAuthApi.createToken(expiration, hostFactory);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Creates one or more host identity tokens. Test for 403 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTokenTest403() throws ApiException {
        String expiration = "2100-05-05";
        String hostFactory = HOST_FACTORY_ID;
        try {
            carlApi.createToken(expiration, hostFactory);
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Creates one or more host identity tokens. Test for 404 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTokenTest404() throws ApiException {
        String expiration = "2100-05-05";
        String hostFactory = "nonexist";
        try {
            carlApi.createToken(expiration, hostFactory);
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }

    /**
     * Creates one or more host identity tokens. Test for 422 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTokenTest422() throws ApiException {
        String expiration = "bad-date";
        String hostFactory = HOST_FACTORY_ID;
        try {
            api.createToken(expiration, hostFactory);
        } catch (ApiException e) {
            Assert.assertEquals(422, e.getCode());
        }
    }

    /**
     * Revokes a token, immediately disabling it. Test for 204 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void revokeTokenTest204() throws ApiException {
        String token = this.getHostToken();

        ApiResponse<?> response = api.revokeTokenWithHttpInfo(token);
        Assert.assertEquals(204, response.getStatusCode());
    }

    /**
     * Revokes a token, immediately disabling it. Test for 400 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void revokeTokenTest400() throws ApiException {
        try {
            api.revokeTokenWithHttpInfo("\0");
        } catch (ApiException e) {
            Assert.assertEquals(400, e.getCode());
        }
    }

    /**
     * Revokes a token, immediately disabling it. Test for 401 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void revokeTokenTest401() throws ApiException {
        String token = this.getHostToken();

        try {
            badAuthApi.revokeTokenWithHttpInfo(token);
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Revokes a token, immediately disabling it. Test for 404 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void revokeTokenTest404() throws ApiException {
        try {
            api.revokeTokenWithHttpInfo("bad_token");
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }
}