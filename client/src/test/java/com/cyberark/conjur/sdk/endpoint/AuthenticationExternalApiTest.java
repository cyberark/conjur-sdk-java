package com.cyberark.conjur.sdk.endpoint;

import com.cyberark.conjur.sdk.*;
import com.cyberark.conjur.sdk.model.ServiceAuthenticators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * API tests for AuthenticationApi.
 */
public class AuthenticationExternalApiTest extends ConfiguredTest {

    private AuthenticationApi api;
    private AuthenticationApi badAuthApi;
    private AuthenticationApi aliceApi;
    private AuthenticationApi mockApi;
    private ApiClient mockClient;
    private ArgumentCaptor<Map> mapCaptor;

    /**
     * Set up Authentication API clients to test.
     * 

     * @throws ApiException if it fails to setup the necessary clients/APIs
     */
    @Before
    public void setUpClients() throws ApiException {
        api = new AuthenticationApi();
        badAuthApi = new AuthenticationApi(nonAuthClient);

        // Setup Alice API
        ApiClient aliceClient = getApiClient("alice");
        aliceClient.setPassword("alice");
        aliceApi = new AuthenticationApi(aliceClient);

        // Setup mock API + response
        mockClient = Mockito.mock(ApiClient.class);
        mockApi = new AuthenticationApi(mockClient);
        ApiResponse<Object> mockResponse = new ApiResponse<Object>(200, null, "Success!");

        Mockito.when(mockClient.escapeString(Mockito.anyString())).thenCallRealMethod();
        Mockito.when(mockClient.parameterToString(Mockito.anyString())).thenCallRealMethod();
        Mockito.when(mockClient.selectHeaderAccept(Mockito.any(String[].class)))
            .thenCallRealMethod();
        Mockito.when(mockClient.selectHeaderContentType(Mockito.any(String[].class)))
            .thenCallRealMethod();
        Mockito.when(mockClient.execute((okhttp3.Call) Mockito.any(), (Type) Mockito.any()))
            .thenReturn(mockResponse);

        // Create a mechanism to capture map param values in mock API calls
        mapCaptor = ArgumentCaptor.forClass(Map.class);
    }


    /**
     * Test enabling a new authenticator via the API.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void enableAuthenticatorTest204() throws ApiException {
        ApiResponse<?> response = api.enableAuthenticatorWithHttpInfo(
                ServiceAuthenticators.fromValue("authn-gcp"), account,
                "test", true);
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    /**
     * Test 401 response when enabling an authenticator.
     */
    @Test
    public void enableAuthenticatorTest401() {
        try {
            api.enableAuthenticatorWithHttpInfo(
                    ServiceAuthenticators.fromValue("authn-oidc"), account, "test", true);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 401);
        }
    }

    /**
     * Test ApiException thrown when a null Authenticator is passed.
     */
    @Test
    public void enableAuthenticatorNullAuthenticatorTest() {
        try {
            api.enableAuthenticatorWithHttpInfo(null, account, "test", true);
        } catch (ApiException e) {
            Assert.assertTrue(e.getMessage().contains(
                    "Missing the required parameter \'authenticator\'"));
        }
    }

    /**
     * Test ApiException thrown when a null account is passed.
     */
    @Test
    public void enableAuthenticatorNullAccountTest() {
        try {
            api.enableAuthenticatorWithHttpInfo(
                    ServiceAuthenticators.fromValue("authn-gcp"), null, "test", true);
        } catch (ApiException e) {
            Assert.assertTrue(e.getMessage()
                    .contains("Missing the required parameter \'account\'"));
        }
    }

    /**
     * Test the getAccessTokenViaJWT method produces a valid API call.
     *
     * @throws ApiException if the Api call fails
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getAccessTokenViaJwtTest200() throws ApiException {
        mockApi.getAccessTokenViaJWT("testUser", "jwt_service", "test", "bad token");

        Mockito.verify(mockClient, Mockito.times(1)).buildCall(
                Mockito.eq("/authn-jwt/jwt_service/testUser/authenticate"),
                Mockito.eq("POST"), 
                Mockito.any(List.class), 
                Mockito.any(List.class), 
                Mockito.any(), 
                Mockito.any(Map.class),
                Mockito.any(Map.class), 
                mapCaptor.capture(), 
                Mockito.any(String[].class), 
                (ApiCallback<?>) Mockito.any());

        Assert.assertEquals(mapCaptor.getValue().get("jwt"), "bad token");
    }

    /**
     * Test the getAccessTokenViaJWT method produces a valid API call.
     *
     * @throws ApiException if the Api call fails
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getAccessTokenViaJwtLessParamsTest200() throws ApiException {
        mockApi.getAccessTokenViaJWT("testUser", "jwt_service");

        Mockito.verify(mockClient, Mockito.times(1)).buildCall(
                Mockito.eq("/authn-jwt/jwt_service/testUser/authenticate"),
                Mockito.eq("POST"), 
                Mockito.any(List.class), 
                Mockito.any(List.class), 
                Mockito.any(), 
                Mockito.any(Map.class),
                Mockito.any(Map.class), 
                Mockito.any(Map.class), 
                Mockito.any(String[].class), 
                (ApiCallback<?>) Mockito.any());
    }

    /**
     * Test the getAccessTokenViaJWTWithId method produces a valid API call.
     *
     * @throws ApiException if the Api call fails
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getAccessTokenViaJwtWithIdTest200() throws ApiException {
        mockApi.getAccessTokenViaJWTWithId(
                "testUser", "jwt_service", "testId", "test", "bad token");

        Mockito.verify(mockClient, Mockito.times(1)).buildCall(
                Mockito.eq("/authn-jwt/testId/testUser/jwt_service/authenticate"), 
                Mockito.eq("POST"), 
                Mockito.any(List.class),
                Mockito.any(List.class), 
                Mockito.any(), 
                Mockito.any(Map.class), 
                Mockito.any(Map.class), 
                mapCaptor.capture(),
                Mockito.any(String[].class), 
                (ApiCallback<?>) Mockito.any());

        Assert.assertEquals(mapCaptor.getValue().get("jwt"), "bad token");
    }

    /**
     * Test the getAccessTokenViaJWTWithId method produces a valid API call.
     *
     * @throws ApiException if the Api call fails
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getAccessTokenViaJwtWithIdLessParamsTest200() throws ApiException {
        mockApi.getAccessTokenViaJWTWithId("testUser", "jwt_service", "testId");

        Mockito.verify(mockClient, Mockito.times(1)).buildCall(
                Mockito.eq("/authn-jwt/testId/testUser/jwt_service/authenticate"), 
                Mockito.eq("POST"), 
                Mockito.any(List.class),
                Mockito.any(List.class), 
                Mockito.any(), 
                Mockito.any(Map.class), 
                Mockito.any(Map.class), 
                Mockito.any(Map.class),
                Mockito.any(String[].class), 
                (ApiCallback<?>) Mockito.any());

    }

    /**
     * Test 400 response when getting an access token via JWT.
     */
    @Test
    public void getAccessTokenViaJwtTest400() {
        try {
            api.getAccessTokenViaJWT("\00", "jwt_service", "test", "bad token");
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 400);
        }
    }

    /**
     * Test 401 response when getting an access token via JWT.
     */
    @Test
    public void getAccessTokenViaJwtTest401() {
        try {
            api.getAccessTokenViaJWT(account, "jwt_service", "test", "bad token");
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 401);
        }
    }

    /**
     * Test ApiException thrown when a null account is passed.
     */
    @Test
    public void getAccessTokenViaJwtNullAccountTest() {
        try {
            api.getAccessTokenViaJWT(null, "jwt_service", "test", "bad token");
        } catch (ApiException e) {
            Assert.assertTrue(e.getMessage()
                    .contains("Missing the required parameter \'account\'"));
        }
    }

    /**
     * Test ApiException thrown when a null serviceId is passed.
     */
    @Test
    public void getAccessTokenViaJwtNullServiceIdTest() {
        try {
            api.getAccessTokenViaJWT(account, null, "test", "bad token");
        } catch (ApiException e) {
            Assert.assertTrue(e.getMessage()
                    .contains("Missing the required parameter \'serviceId\'"));
        }
    }

    /**
     * Test ApiException thrown when a null account is passed.
     */
    @Test
    public void getAccessTokenViaJwtWithIdNullAccountTest() {
        try {
            api.getAccessTokenViaJWTWithId(null, "jwt_service", "test", "testId", "bad token");
        } catch (ApiException e) {
            Assert.assertTrue(e.getMessage()
                    .contains("Missing the required parameter \'account\'"));
        }
    }

    /**
     * Test ApiException thrown when a null serviceId is passed.
     */
    @Test
    public void getAccessTokenViaJwtWithIdNullServiceIdTest() {
        try {
            api.getAccessTokenViaJWTWithId(account, "testId", null, "test", "bad token");
        } catch (ApiException e) {
            Assert.assertTrue(e.getMessage()
                    .contains("Missing the required parameter \'serviceId\'"));
        }
    }

    /**
     * Test ApiException thrown when a null id is passed.
     */
    @Test
    public void getAccessTokenViaJwtWithIdNullIdTest() {
        try {
            api.getAccessTokenViaJWTWithId(account, null, "jwt_service", "test", "bad token");
        } catch (ApiException e) {
            Assert.assertTrue(e.getMessage()
                    .contains("Missing the required parameter \'id\'"));
        }
    }

    /**
     * Test getting an API Key via the authn-ldap endpoint.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getApiKeyViaLdapTest200() throws ApiException {
        String response = aliceApi.getAPIKeyViaLDAP("test", account);
        Assert.assertNotNull(response);
    }

    /**
     * Test 400 response when getting an API key via LDAP.
     */
    @Test
    public void getApiKeyViaLdapTest400() {
        try {
            badAuthApi.getAPIKeyViaLDAP("\00", account);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 400);
        }
    }

    /**
     * Test 401 response when getting an API key via LDAP.
     */
    @Test
    public void getApiKeyViaLdapTest401() {
        try {
            badAuthApi.getAPIKeyViaLDAP("aws", account, "admin");
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 401);
        }
    }

    /**
     * Test getting an access token via the authn-oidc endpoint.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAccessTokenViaOidcTest200() throws ApiException, IOException {
        ConfiguredTest.setupOidcWebservice();
        String idToken = getOidcIdToken();
        String response = api.getAccessTokenViaOIDC("test", account, "test", idToken);
        Assert.assertNotNull(response);
    }

    /**
     * Test 401 response when getting an API key via OIDC.
     *
     * @throws IOException if getting the OIDC token fails
     */
    @Test
    public void getAccessTokenViaOidcTest401() throws IOException {
        try {
            String idToken = getOidcIdToken();
            badAuthApi.getAccessTokenViaOIDC("aws", account, "admin", idToken);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 401);
        }
    }

}
