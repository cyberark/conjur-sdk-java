/*
 * Conjur
 * This is an API definition for CyberArk Conjur Open Source. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.3.0
 * Contact: conj_maintainers@cyberark.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.cyberark.conjur.sdk.endpoint;

import com.cyberark.conjur.sdk.ApiCallback;
import com.cyberark.conjur.sdk.ApiClient;
import com.cyberark.conjur.sdk.ApiException;
import com.cyberark.conjur.sdk.ApiResponse;
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.Pair;
import com.cyberark.conjur.sdk.ProgressRequestBody;
import com.cyberark.conjur.sdk.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.cyberark.conjur.sdk.model.CreateHost;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostFactoryApi {
    private ApiClient localVarApiClient;

    public HostFactoryApi() {
        this(Configuration.getDefaultApiClient());
    }

    public HostFactoryApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for createHost
     * @param id Identifier of the host to be created. It will be created within the account of the host factory. (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param annotations Annotations to apply to the new host (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body contains the newly-created host </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createHostCall(String id, String xRequestId, Object annotations, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/host_factories/hosts";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xRequestId != null) {
            localVarHeaderParams.put("X-Request-Id", localVarApiClient.parameterToString(xRequestId));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (annotations != null) {
            localVarFormParams.put("annotations", annotations);
        }

        if (id != null) {
            localVarFormParams.put("id", id);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/x-www-form-urlencoded"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "conjurAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createHostValidateBeforeCall(String id, String xRequestId, Object annotations, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling createHost(Async)");
        }
        

        okhttp3.Call localVarCall = createHostCall(id, xRequestId, annotations, _callback);
        return localVarCall;

    }

    /**
     * Creates a Host using the Host Factory.
     * Creates a Host using the Host Factory and returns a JSON description of it.  Requires a host factory token, which can be created using the create tokens API. In practice, this token is usually provided automatically as part of Conjur integration with your host provisioning infrastructure.  Note: If the token was created with a CIDR restriction, you must make this API request from a whitelisted address. 
     * @param id Identifier of the host to be created. It will be created within the account of the host factory. (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param annotations Annotations to apply to the new host (optional)
     * @return CreateHost
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body contains the newly-created host </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public CreateHost createHost(String id, String xRequestId, Object annotations) throws ApiException {
        ApiResponse<CreateHost> localVarResp = createHostWithHttpInfo(id, xRequestId, annotations);
        return localVarResp.getData();
    }


    /**
     * Creates a Host using the Host Factory.
     * Creates a Host using the Host Factory and returns a JSON description of it.  Requires a host factory token, which can be created using the create tokens API. In practice, this token is usually provided automatically as part of Conjur integration with your host provisioning infrastructure.  Note: If the token was created with a CIDR restriction, you must make this API request from a whitelisted address. 
     * @param id Identifier of the host to be created. It will be created within the account of the host factory.
     * @return CreateHost
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body contains the newly-created host </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
    */
    public CreateHost createHost(String id) throws ApiException {
        ApiResponse<CreateHost> localVarResp = createHostWithHttpInfo(id, null, null);
        return localVarResp.getData();
    }

    /**
     * Creates a Host using the Host Factory.
     * Creates a Host using the Host Factory and returns a JSON description of it.  Requires a host factory token, which can be created using the create tokens API. In practice, this token is usually provided automatically as part of Conjur integration with your host provisioning infrastructure.  Note: If the token was created with a CIDR restriction, you must make this API request from a whitelisted address. 
     * @param id Identifier of the host to be created. It will be created within the account of the host factory. (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param annotations Annotations to apply to the new host (optional)
     * @return ApiResponse&lt;CreateHost&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body contains the newly-created host </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CreateHost> createHostWithHttpInfo(String id, String xRequestId, Object annotations) throws ApiException {
        okhttp3.Call localVarCall = createHostValidateBeforeCall(id, xRequestId, annotations, null);
        Type localVarReturnType = new TypeToken<CreateHost>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Creates a Host using the Host Factory.
     * Creates a Host using the Host Factory and returns a JSON description of it.  Requires a host factory token, which can be created using the create tokens API. In practice, this token is usually provided automatically as part of Conjur integration with your host provisioning infrastructure.  Note: If the token was created with a CIDR restriction, you must make this API request from a whitelisted address. 
     * @param id Identifier of the host to be created. It will be created within the account of the host factory. 
     * @return ApiResponse&lt;CreateHost&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body contains the newly-created host </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CreateHost> createHostWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = createHostValidateBeforeCall(id, null, null, null);
        Type localVarReturnType = new TypeToken<CreateHost>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Creates a Host using the Host Factory. (asynchronously)
     * Creates a Host using the Host Factory and returns a JSON description of it.  Requires a host factory token, which can be created using the create tokens API. In practice, this token is usually provided automatically as part of Conjur integration with your host provisioning infrastructure.  Note: If the token was created with a CIDR restriction, you must make this API request from a whitelisted address. 
     * @param id Identifier of the host to be created. It will be created within the account of the host factory. (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param annotations Annotations to apply to the new host (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body contains the newly-created host </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createHostAsync(String id, String xRequestId, Object annotations, final ApiCallback<CreateHost> _callback) throws ApiException {

        okhttp3.Call localVarCall = createHostValidateBeforeCall(id, xRequestId, annotations, _callback);
        Type localVarReturnType = new TypeToken<CreateHost>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for createToken
     * @param expiration &#x60;ISO 8601 datetime&#x60; denoting a requested expiration time. (required)
     * @param hostFactory Fully qualified host factory ID (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param cidr Number of host tokens to create (optional)
     * @param count Number of host tokens to create (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Zero or more tokens were created and delivered in the response body </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The authenticated user lacks the necessary privileges </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createTokenCall(String expiration, String hostFactory, String xRequestId, List<String> cidr, Integer count, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/host_factory_tokens";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xRequestId != null) {
            localVarHeaderParams.put("X-Request-Id", localVarApiClient.parameterToString(xRequestId));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (cidr != null) {
            localVarFormParams.put("cidr", cidr);
        }

        if (count != null) {
            localVarFormParams.put("count", count);
        }

        if (expiration != null) {
            localVarFormParams.put("expiration", expiration);
        }

        if (hostFactory != null) {
            localVarFormParams.put("host_factory", hostFactory);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/x-www-form-urlencoded"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "conjurAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createTokenValidateBeforeCall(String expiration, String hostFactory, String xRequestId, List<String> cidr, Integer count, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'expiration' is set
        if (expiration == null) {
            throw new ApiException("Missing the required parameter 'expiration' when calling createToken(Async)");
        }
        
        // verify the required parameter 'hostFactory' is set
        if (hostFactory == null) {
            throw new ApiException("Missing the required parameter 'hostFactory' when calling createToken(Async)");
        }
        

        okhttp3.Call localVarCall = createTokenCall(expiration, hostFactory, xRequestId, cidr, count, _callback);
        return localVarCall;

    }

    /**
     * Creates one or more host identity tokens.
     * Creates one or more tokens which can be used to bootstrap host identity. Responds with a JSON document containing the tokens and their restrictions.  If the tokens are created with a CIDR restriction, Conjur will only accept them from the whitelisted IP ranges.  ##### Permissions required # &#x60;execute&#x60; privilege on the Host Factory.\&quot; 
     * @param expiration &#x60;ISO 8601 datetime&#x60; denoting a requested expiration time. (required)
     * @param hostFactory Fully qualified host factory ID (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param cidr Number of host tokens to create (optional)
     * @param count Number of host tokens to create (optional)
     * @return List&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Zero or more tokens were created and delivered in the response body </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The authenticated user lacks the necessary privileges </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public List<Object> createToken(String expiration, String hostFactory, String xRequestId, List<String> cidr, Integer count) throws ApiException {
        ApiResponse<List<Object>> localVarResp = createTokenWithHttpInfo(expiration, hostFactory, xRequestId, cidr, count);
        return localVarResp.getData();
    }


    /**
     * Creates one or more host identity tokens.
     * Creates one or more tokens which can be used to bootstrap host identity. Responds with a JSON document containing the tokens and their restrictions.  If the tokens are created with a CIDR restriction, Conjur will only accept them from the whitelisted IP ranges.  ##### Permissions required # &#x60;execute&#x60; privilege on the Host Factory.\&quot; 
     * @param expiration &#x60;ISO 8601 datetime&#x60; denoting a requested expiration time.
     * @param hostFactory Fully qualified host factory ID
     * @return List&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Zero or more tokens were created and delivered in the response body </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The authenticated user lacks the necessary privileges </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
    */
    public List<Object> createToken(String expiration, String hostFactory) throws ApiException {
        ApiResponse<List<Object>> localVarResp = createTokenWithHttpInfo(expiration, hostFactory, null, null, null);
        return localVarResp.getData();
    }

    /**
     * Creates one or more host identity tokens.
     * Creates one or more tokens which can be used to bootstrap host identity. Responds with a JSON document containing the tokens and their restrictions.  If the tokens are created with a CIDR restriction, Conjur will only accept them from the whitelisted IP ranges.  ##### Permissions required # &#x60;execute&#x60; privilege on the Host Factory.\&quot; 
     * @param expiration &#x60;ISO 8601 datetime&#x60; denoting a requested expiration time. (required)
     * @param hostFactory Fully qualified host factory ID (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param cidr Number of host tokens to create (optional)
     * @param count Number of host tokens to create (optional)
     * @return ApiResponse&lt;List&lt;Object&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Zero or more tokens were created and delivered in the response body </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The authenticated user lacks the necessary privileges </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Object>> createTokenWithHttpInfo(String expiration, String hostFactory, String xRequestId, List<String> cidr, Integer count) throws ApiException {
        okhttp3.Call localVarCall = createTokenValidateBeforeCall(expiration, hostFactory, xRequestId, cidr, count, null);
        Type localVarReturnType = new TypeToken<List<Object>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Creates one or more host identity tokens.
     * Creates one or more tokens which can be used to bootstrap host identity. Responds with a JSON document containing the tokens and their restrictions.  If the tokens are created with a CIDR restriction, Conjur will only accept them from the whitelisted IP ranges.  ##### Permissions required # &#x60;execute&#x60; privilege on the Host Factory.\&quot; 
     * @param expiration &#x60;ISO 8601 datetime&#x60; denoting a requested expiration time. 
     * @param hostFactory Fully qualified host factory ID 
     * @return ApiResponse&lt;List&lt;Object&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Zero or more tokens were created and delivered in the response body </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The authenticated user lacks the necessary privileges </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Object>> createTokenWithHttpInfo(String expiration, String hostFactory) throws ApiException {
        okhttp3.Call localVarCall = createTokenValidateBeforeCall(expiration, hostFactory, null, null, null, null);
        Type localVarReturnType = new TypeToken<List<Object>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Creates one or more host identity tokens. (asynchronously)
     * Creates one or more tokens which can be used to bootstrap host identity. Responds with a JSON document containing the tokens and their restrictions.  If the tokens are created with a CIDR restriction, Conjur will only accept them from the whitelisted IP ranges.  ##### Permissions required # &#x60;execute&#x60; privilege on the Host Factory.\&quot; 
     * @param expiration &#x60;ISO 8601 datetime&#x60; denoting a requested expiration time. (required)
     * @param hostFactory Fully qualified host factory ID (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param cidr Number of host tokens to create (optional)
     * @param count Number of host tokens to create (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Zero or more tokens were created and delivered in the response body </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The authenticated user lacks the necessary privileges </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createTokenAsync(String expiration, String hostFactory, String xRequestId, List<String> cidr, Integer count, final ApiCallback<List<Object>> _callback) throws ApiException {

        okhttp3.Call localVarCall = createTokenValidateBeforeCall(expiration, hostFactory, xRequestId, cidr, count, _callback);
        Type localVarReturnType = new TypeToken<List<Object>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for revokeToken
     * @param token The host factory token to revoke (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Token was successfully revoked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call revokeTokenCall(String token, String xRequestId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/host_factory_tokens/{token}"
            .replaceAll("\\{" + "token" + "\\}", localVarApiClient.escapeString(token.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xRequestId != null) {
            localVarHeaderParams.put("X-Request-Id", localVarApiClient.parameterToString(xRequestId));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "conjurAuth" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call revokeTokenValidateBeforeCall(String token, String xRequestId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'token' is set
        if (token == null) {
            throw new ApiException("Missing the required parameter 'token' when calling revokeToken(Async)");
        }
        

        okhttp3.Call localVarCall = revokeTokenCall(token, xRequestId, _callback);
        return localVarCall;

    }

    /**
     * Revokes a token, immediately disabling it.
     * Revokes a token, immediately disabling it.  ##### Permissions required  &#x60;update&#x60; privilege on the host factory.\&quot; 
     * @param token The host factory token to revoke (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Token was successfully revoked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
     </table>
     */
    public void revokeToken(String token, String xRequestId) throws ApiException {
        revokeTokenWithHttpInfo(token, xRequestId);
    }


    /**
     * Revokes a token, immediately disabling it.
     * Revokes a token, immediately disabling it.  ##### Permissions required  &#x60;update&#x60; privilege on the host factory.\&quot; 
     * @param token The host factory token to revoke
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Token was successfully revoked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
     </table>
    */
    public void revokeToken(String token) throws ApiException {
        revokeTokenWithHttpInfo(token, null);
    }

    /**
     * Revokes a token, immediately disabling it.
     * Revokes a token, immediately disabling it.  ##### Permissions required  &#x60;update&#x60; privilege on the host factory.\&quot; 
     * @param token The host factory token to revoke (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Token was successfully revoked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> revokeTokenWithHttpInfo(String token, String xRequestId) throws ApiException {
        okhttp3.Call localVarCall = revokeTokenValidateBeforeCall(token, xRequestId, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Revokes a token, immediately disabling it.
     * Revokes a token, immediately disabling it.  ##### Permissions required  &#x60;update&#x60; privilege on the host factory.\&quot; 
     * @param token The host factory token to revoke 
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Token was successfully revoked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> revokeTokenWithHttpInfo(String token) throws ApiException {
        okhttp3.Call localVarCall = revokeTokenValidateBeforeCall(token, null, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Revokes a token, immediately disabling it. (asynchronously)
     * Revokes a token, immediately disabling it.  ##### Permissions required  &#x60;update&#x60; privilege on the host factory.\&quot; 
     * @param token The host factory token to revoke (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Token was successfully revoked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call revokeTokenAsync(String token, String xRequestId, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = revokeTokenValidateBeforeCall(token, xRequestId, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
