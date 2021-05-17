# PoliciesApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**loadPolicy**](PoliciesApi.md#loadPolicy) | **POST** /policies/{account}/policy/{identifier} | Adds data to the existing Conjur policy.
[**replacePolicy**](PoliciesApi.md#replacePolicy) | **PUT** /policies/{account}/policy/{identifier} | Loads or replaces a Conjur policy document.
[**updatePolicy**](PoliciesApi.md#updatePolicy) | **PATCH** /policies/{account}/policy/{identifier} | Modifies an existing Conjur policy.


<a name="loadPolicy"></a>
# **loadPolicy**
> LoadedPolicy loadPolicy(account, identifier, body, xRequestId)

Adds data to the existing Conjur policy.

Adds data to the existing Conjur policy. Deletions are not allowed. Any policy objects that exist on the server but are omitted from the policy file will not be deleted and any explicit deletions in the policy file will result in an error.  ##### Permissions required  &#x60;create&#x60; privilege on the policy.\&quot; 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.PoliciesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    PoliciesApi apiInstance = new PoliciesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String identifier = root; // String | ID of the policy to update
    String body = "body_example"; // String | Policy
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      LoadedPolicy result = apiInstance.loadPolicy(account, identifier, body, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PoliciesApi#loadPolicy");
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
 **identifier** | **String**| ID of the policy to update |
 **body** | **String**| Policy |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**LoadedPolicy**](LoadedPolicy.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: application/x-yaml, text/plain, text/x-yaml, text/yaml
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Decsribes new data created by a successful policy load |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**409** | Similar operation already in progress, retry after a delay |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="replacePolicy"></a>
# **replacePolicy**
> LoadedPolicy replacePolicy(account, identifier, body, xRequestId)

Loads or replaces a Conjur policy document.

Loads or replaces a Conjur policy document.  **Any policy data which already exists on the server but is not explicitly specified in the new policy file will be deleted!**. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.PoliciesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    PoliciesApi apiInstance = new PoliciesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String identifier = root; // String | ID of the policy to load (root if no root policy has been loaded yet)
    String body = "body_example"; // String | Policy
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      LoadedPolicy result = apiInstance.replacePolicy(account, identifier, body, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PoliciesApi#replacePolicy");
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
 **identifier** | **String**| ID of the policy to load (root if no root policy has been loaded yet) |
 **body** | **String**| Policy |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**LoadedPolicy**](LoadedPolicy.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: application/x-yaml, text/plain, text/x-yaml, text/yaml
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Decsribes new data created by a successful policy load |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**409** | Similar operation already in progress, retry after a delay |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="updatePolicy"></a>
# **updatePolicy**
> LoadedPolicy updatePolicy(account, identifier, body, xRequestId)

Modifies an existing Conjur policy.

Modifies an existing Conjur policy. Data may be explicitly deleted using the &#x60;!delete&#x60;, &#x60;!revoke&#x60;, and &#x60;!deny&#x60; statements. Unlike &#x60;replace&#x60; mode, no data is ever implicitly deleted.  ##### Permissions required 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.PoliciesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    PoliciesApi apiInstance = new PoliciesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String identifier = root; // String | ID of the policy to update
    String body = "body_example"; // String | Policy
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      LoadedPolicy result = apiInstance.updatePolicy(account, identifier, body, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PoliciesApi#updatePolicy");
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
 **identifier** | **String**| ID of the policy to update |
 **body** | **String**| Policy |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**LoadedPolicy**](LoadedPolicy.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: application/x-yaml, text/plain, text/x-yaml, text/yaml
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Decsribes new data created by a successful policy load |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**409** | Similar operation already in progress, retry after a delay |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

