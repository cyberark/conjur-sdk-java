/*
 * Conjur
 * This is an API definition for CyberArk Conjur Open Source. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.3.1
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



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicKeysApi {
    private ApiClient localVarApiClient;

    public PublicKeysApi() {
        this(Configuration.getDefaultApiClient());
    }

    public PublicKeysApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for showPublicKeys
     * @param account Organization account name (required)
     * @param kind Type of resource (required)
     * @param identifier ID of the resource for which to get the information about (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Empty string if the resource does not exist </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Malfromed request, rejected by the server </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call showPublicKeysCall(String account, String kind, String identifier, String xRequestId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/public_keys/{account}/{kind}/{identifier}"
            .replaceAll("\\{" + "account" + "\\}", localVarApiClient.escapeString(account.toString()))
            .replaceAll("\\{" + "kind" + "\\}", localVarApiClient.escapeString(kind.toString()))
            .replaceAll("\\{" + "identifier" + "\\}", localVarApiClient.escapeString(identifier.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xRequestId != null) {
            localVarHeaderParams.put("X-Request-Id", localVarApiClient.parameterToString(xRequestId));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "text/plain"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basicAuth", "conjurAuth", "conjurKubernetesMutualTls" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call showPublicKeysValidateBeforeCall(String account, String kind, String identifier, String xRequestId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'account' is set
        if (account == null) {
            throw new ApiException("Missing the required parameter 'account' when calling showPublicKeys(Async)");
        }
        
        // verify the required parameter 'kind' is set
        if (kind == null) {
            throw new ApiException("Missing the required parameter 'kind' when calling showPublicKeys(Async)");
        }
        
        // verify the required parameter 'identifier' is set
        if (identifier == null) {
            throw new ApiException("Missing the required parameter 'identifier' when calling showPublicKeys(Async)");
        }
        

        okhttp3.Call localVarCall = showPublicKeysCall(account, kind, identifier, xRequestId, _callback);
        return localVarCall;

    }

    /**
     * Shows all public keys for a resource.
     * Shows all public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Returns an empty string if the resource does not exist, to prevent attackers from determining whether a resource exists. 
     * @param account Organization account name (required)
     * @param kind Type of resource (required)
     * @param identifier ID of the resource for which to get the information about (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Empty string if the resource does not exist </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Malfromed request, rejected by the server </td><td>  -  </td></tr>
     </table>
     */
    public String showPublicKeys(String account, String kind, String identifier, String xRequestId) throws ApiException {
        ApiResponse<String> localVarResp = showPublicKeysWithHttpInfo(account, kind, identifier, xRequestId);
        return localVarResp.getData();
    }


    /**
     * Shows all public keys for a resource.
     * Shows all public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Returns an empty string if the resource does not exist, to prevent attackers from determining whether a resource exists. 
     * @param account Organization account name
     * @param kind Type of resource
     * @param identifier ID of the resource for which to get the information about
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Empty string if the resource does not exist </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Malfromed request, rejected by the server </td><td>  -  </td></tr>
     </table>
    */
    public String showPublicKeys(String account, String kind, String identifier) throws ApiException {
        ApiResponse<String> localVarResp = showPublicKeysWithHttpInfo(account, kind, identifier, null);
        return localVarResp.getData();
    }

    /**
     * Shows all public keys for a resource.
     * Shows all public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Returns an empty string if the resource does not exist, to prevent attackers from determining whether a resource exists. 
     * @param account Organization account name (required)
     * @param kind Type of resource (required)
     * @param identifier ID of the resource for which to get the information about (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Empty string if the resource does not exist </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Malfromed request, rejected by the server </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<String> showPublicKeysWithHttpInfo(String account, String kind, String identifier, String xRequestId) throws ApiException {
        okhttp3.Call localVarCall = showPublicKeysValidateBeforeCall(account, kind, identifier, xRequestId, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Shows all public keys for a resource.
     * Shows all public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Returns an empty string if the resource does not exist, to prevent attackers from determining whether a resource exists. 
     * @param account Organization account name 
     * @param kind Type of resource 
     * @param identifier ID of the resource for which to get the information about 
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Empty string if the resource does not exist </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Malfromed request, rejected by the server </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<String> showPublicKeysWithHttpInfo(String account, String kind, String identifier) throws ApiException {
        okhttp3.Call localVarCall = showPublicKeysValidateBeforeCall(account, kind, identifier, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Shows all public keys for a resource. (asynchronously)
     * Shows all public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Returns an empty string if the resource does not exist, to prevent attackers from determining whether a resource exists. 
     * @param account Organization account name (required)
     * @param kind Type of resource (required)
     * @param identifier ID of the resource for which to get the information about (required)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Empty string if the resource does not exist </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> A request parameter was either missing or invalid. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Malfromed request, rejected by the server </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call showPublicKeysAsync(String account, String kind, String identifier, String xRequestId, final ApiCallback<String> _callback) throws ApiException {

        okhttp3.Call localVarCall = showPublicKeysValidateBeforeCall(account, kind, identifier, xRequestId, _callback);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
