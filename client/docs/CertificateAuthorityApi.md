# CertificateAuthorityApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**sign**](CertificateAuthorityApi.md#sign) | **POST** /ca/{account}/{service_id}/sign | Gets a signed certificate from the configured Certificate Authority service.


<a name="sign"></a>
# **sign**
> CertificateJson sign(account, serviceId, csr, ttl, accept, xRequestId)

Gets a signed certificate from the configured Certificate Authority service.

Gets a signed certificate from the configured Certificate Authority service.  The request must include a valid Certificate Signing Request, and a desired TTL in ISO 8601 format.  *** IMPORTANT *** This endpoint is part of an early implementation of support for using Conjur as a certificate authority, and is currently available at the Community (or early alpha) level. This endpoint is still subject to breaking changes in the future. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.CertificateAuthorityApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    CertificateAuthorityApi apiInstance = new CertificateAuthorityApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String serviceId = ca-service; // String | Name of the Certificate Authority service
    String csr = "csr_example"; // String | 
    String ttl = "ttl_example"; // String | 
    String accept = application/x-pem-file; // String | Setting the Accept header to `application/x-pem-file` allows Conjur to respond with a formatted certificate
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      CertificateJson result = apiInstance.sign(account, serviceId, csr, ttl, accept, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificateAuthorityApi#sign");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **account** | **String**| Organization account name |
 **serviceId** | **String**| Name of the Certificate Authority service |
 **csr** | **String**|  |
 **ttl** | **String**|  |
 **accept** | **String**| Setting the Accept header to &#x60;application/x-pem-file&#x60; allows Conjur to respond with a formatted certificate | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**CertificateJson**](CertificateJson.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json, application/x-pem-file

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | The response body is the newly signed certificate |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | Either           - The authenticated role is not a Host role, - The authenticated Host does not have &#x60;sign&#x60; privilege for the CA service, or - The authenticated Host ID does not match the of the CSR Common Name (CN).  |  -  |
**404** | CA Service with the given ID does not exist |  -  |

