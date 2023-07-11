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


import com.cyberark.conjur.sdk.model.CertificateJson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CertificateAuthorityApi {
    private ApiClient localVarApiClient;

    public CertificateAuthorityApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CertificateAuthorityApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for sign
     * @param account Organization account name (required)
     * @param serviceId Name of the Certificate Authority service (required)
     * @param csr  (required)
     * @param ttl  (required)
     * @param accept Setting the Accept header to &#x60;application/x-pem-file&#x60; allows Conjur to respond with a formatted certificate (optional)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body is the newly signed certificate </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Either           - The authenticated role is not a Host role, - The authenticated Host does not have &#x60;sign&#x60; privilege for the CA service, or - The authenticated Host ID does not match the of the CSR Common Name (CN).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> CA Service with the given ID does not exist </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call signCall(String account, String serviceId, String csr, String ttl, String accept, String xRequestId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/ca/{account}/{service_id}/sign"
            .replaceAll("\\{" + "account" + "\\}", localVarApiClient.escapeString(account.toString()))
            .replaceAll("\\{" + "service_id" + "\\}", localVarApiClient.escapeString(serviceId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (accept != null) {
            localVarHeaderParams.put("Accept", localVarApiClient.parameterToString(accept));
        }

        if (xRequestId != null) {
            localVarHeaderParams.put("X-Request-Id", localVarApiClient.parameterToString(xRequestId));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (csr != null) {
            localVarFormParams.put("csr", csr);
        }

        if (ttl != null) {
            localVarFormParams.put("ttl", ttl);
        }

        final String[] localVarAccepts = {
            "application/json", "application/x-pem-file"
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
    private okhttp3.Call signValidateBeforeCall(String account, String serviceId, String csr, String ttl, String accept, String xRequestId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'account' is set
        if (account == null) {
            throw new ApiException("Missing the required parameter 'account' when calling sign(Async)");
        }
        
        // verify the required parameter 'serviceId' is set
        if (serviceId == null) {
            throw new ApiException("Missing the required parameter 'serviceId' when calling sign(Async)");
        }
        
        // verify the required parameter 'csr' is set
        if (csr == null) {
            throw new ApiException("Missing the required parameter 'csr' when calling sign(Async)");
        }
        
        // verify the required parameter 'ttl' is set
        if (ttl == null) {
            throw new ApiException("Missing the required parameter 'ttl' when calling sign(Async)");
        }
        

        okhttp3.Call localVarCall = signCall(account, serviceId, csr, ttl, accept, xRequestId, _callback);
        return localVarCall;

    }

    /**
     * Gets a signed certificate from the configured Certificate Authority service.
     * Gets a signed certificate from the configured Certificate Authority service.  The request must include a valid Certificate Signing Request, and a desired TTL in ISO 8601 format.  *** IMPORTANT *** This endpoint is part of an early implementation of support for using Conjur as a certificate authority, and is currently available at the Community (or early alpha) level. This endpoint is still subject to breaking changes in the future. 
     * @param account Organization account name (required)
     * @param serviceId Name of the Certificate Authority service (required)
     * @param csr  (required)
     * @param ttl  (required)
     * @param accept Setting the Accept header to &#x60;application/x-pem-file&#x60; allows Conjur to respond with a formatted certificate (optional)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @return CertificateJson
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body is the newly signed certificate </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Either           - The authenticated role is not a Host role, - The authenticated Host does not have &#x60;sign&#x60; privilege for the CA service, or - The authenticated Host ID does not match the of the CSR Common Name (CN).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> CA Service with the given ID does not exist </td><td>  -  </td></tr>
     </table>
     */
    public CertificateJson sign(String account, String serviceId, String csr, String ttl, String accept, String xRequestId) throws ApiException {
        ApiResponse<CertificateJson> localVarResp = signWithHttpInfo(account, serviceId, csr, ttl, accept, xRequestId);
        return localVarResp.getData();
    }


    /**
     * Gets a signed certificate from the configured Certificate Authority service.
     * Gets a signed certificate from the configured Certificate Authority service.  The request must include a valid Certificate Signing Request, and a desired TTL in ISO 8601 format.  *** IMPORTANT *** This endpoint is part of an early implementation of support for using Conjur as a certificate authority, and is currently available at the Community (or early alpha) level. This endpoint is still subject to breaking changes in the future. 
     * @param account Organization account name
     * @param serviceId Name of the Certificate Authority service
     * @param csr 
     * @param ttl 
     * @return CertificateJson
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body is the newly signed certificate </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Either           - The authenticated role is not a Host role, - The authenticated Host does not have &#x60;sign&#x60; privilege for the CA service, or - The authenticated Host ID does not match the of the CSR Common Name (CN).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> CA Service with the given ID does not exist </td><td>  -  </td></tr>
     </table>
    */
    public CertificateJson sign(String account, String serviceId, String csr, String ttl) throws ApiException {
        ApiResponse<CertificateJson> localVarResp = signWithHttpInfo(account, serviceId, csr, ttl, null, null);
        return localVarResp.getData();
    }

    /**
     * Gets a signed certificate from the configured Certificate Authority service.
     * Gets a signed certificate from the configured Certificate Authority service.  The request must include a valid Certificate Signing Request, and a desired TTL in ISO 8601 format.  *** IMPORTANT *** This endpoint is part of an early implementation of support for using Conjur as a certificate authority, and is currently available at the Community (or early alpha) level. This endpoint is still subject to breaking changes in the future. 
     * @param account Organization account name (required)
     * @param serviceId Name of the Certificate Authority service (required)
     * @param csr  (required)
     * @param ttl  (required)
     * @param accept Setting the Accept header to &#x60;application/x-pem-file&#x60; allows Conjur to respond with a formatted certificate (optional)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @return ApiResponse&lt;CertificateJson&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body is the newly signed certificate </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Either           - The authenticated role is not a Host role, - The authenticated Host does not have &#x60;sign&#x60; privilege for the CA service, or - The authenticated Host ID does not match the of the CSR Common Name (CN).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> CA Service with the given ID does not exist </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CertificateJson> signWithHttpInfo(String account, String serviceId, String csr, String ttl, String accept, String xRequestId) throws ApiException {
        okhttp3.Call localVarCall = signValidateBeforeCall(account, serviceId, csr, ttl, accept, xRequestId, null);
        Type localVarReturnType = new TypeToken<CertificateJson>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Gets a signed certificate from the configured Certificate Authority service.
     * Gets a signed certificate from the configured Certificate Authority service.  The request must include a valid Certificate Signing Request, and a desired TTL in ISO 8601 format.  *** IMPORTANT *** This endpoint is part of an early implementation of support for using Conjur as a certificate authority, and is currently available at the Community (or early alpha) level. This endpoint is still subject to breaking changes in the future. 
     * @param account Organization account name 
     * @param serviceId Name of the Certificate Authority service 
     * @param csr  
     * @param ttl  
     * @return ApiResponse&lt;CertificateJson&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body is the newly signed certificate </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Either           - The authenticated role is not a Host role, - The authenticated Host does not have &#x60;sign&#x60; privilege for the CA service, or - The authenticated Host ID does not match the of the CSR Common Name (CN).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> CA Service with the given ID does not exist </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<CertificateJson> signWithHttpInfo(String account, String serviceId, String csr, String ttl) throws ApiException {
        okhttp3.Call localVarCall = signValidateBeforeCall(account, serviceId, csr, ttl, null, null, null);
        Type localVarReturnType = new TypeToken<CertificateJson>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Gets a signed certificate from the configured Certificate Authority service. (asynchronously)
     * Gets a signed certificate from the configured Certificate Authority service.  The request must include a valid Certificate Signing Request, and a desired TTL in ISO 8601 format.  *** IMPORTANT *** This endpoint is part of an early implementation of support for using Conjur as a certificate authority, and is currently available at the Community (or early alpha) level. This endpoint is still subject to breaking changes in the future. 
     * @param account Organization account name (required)
     * @param serviceId Name of the Certificate Authority service (required)
     * @param csr  (required)
     * @param ttl  (required)
     * @param accept Setting the Accept header to &#x60;application/x-pem-file&#x60; allows Conjur to respond with a formatted certificate (optional)
     * @param xRequestId Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The response body is the newly signed certificate </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The server cannot process the request due to malformed request syntax </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Either           - The authenticated role is not a Host role, - The authenticated Host does not have &#x60;sign&#x60; privilege for the CA service, or - The authenticated Host ID does not match the of the CSR Common Name (CN).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> CA Service with the given ID does not exist </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call signAsync(String account, String serviceId, String csr, String ttl, String accept, String xRequestId, final ApiCallback<CertificateJson> _callback) throws ApiException {

        okhttp3.Call localVarCall = signValidateBeforeCall(account, serviceId, csr, ttl, accept, xRequestId, _callback);
        Type localVarReturnType = new TypeToken<CertificateJson>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
