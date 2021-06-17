# PublicKeysApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**showPublicKeys**](PublicKeysApi.md#showPublicKeys) | **GET** /public_keys/{account}/{kind}/{identifier} | Shows all public keys for a resource.


<a name="showPublicKeys"></a>
# **showPublicKeys**
> String showPublicKeys(account, kind, identifier, xRequestId)

Shows all public keys for a resource.

Shows all public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Returns an empty string if the resource does not exist, to prevent attackers from determining whether a resource exists. 

### Example
```java
// Import classes:
import com.cyberark.conjur.sdk.ApiClient;
import com.cyberark.conjur.sdk.ApiException;
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.auth.*;
import com.cyberark.conjur.sdk.models.*;
import com.cyberark.conjur.sdk.endpoint.PublicKeysApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");


    PublicKeysApi apiInstance = new PublicKeysApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String kind = user; // String | Type of resource
    String identifier = admin; // String | ID of the resource for which to get the information about
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      String result = apiInstance.showPublicKeys(account, kind, identifier, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicKeysApi#showPublicKeys");
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
 **kind** | **String**| Type of resource |
 **identifier** | **String**| ID of the resource for which to get the information about |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Public keys for a resource as newline delimited string for compatibility with the authorized_keys SSH format. Empty string if the resource does not exist |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |
**500** | Malfromed request, rejected by the server |  -  |

