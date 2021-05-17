# HostFactoryApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createHost**](HostFactoryApi.md#createHost) | **POST** /host_factories/hosts | Creates a Host using the Host Factory.
[**createToken**](HostFactoryApi.md#createToken) | **POST** /host_factory_tokens | Creates one or more host identity tokens.
[**revokeToken**](HostFactoryApi.md#revokeToken) | **DELETE** /host_factory_tokens/{token} | Revokes a token, immediately disabling it.


<a name="createHost"></a>
# **createHost**
> CreateHost createHost(id, xRequestId, annotations)

Creates a Host using the Host Factory.

Creates a Host using the Host Factory and returns a JSON description of it.  Requires a host factory token, which can be created using the create tokens API. In practice, this token is usually provided automatically as part of Conjur integration with your host provisioning infrastructure.  Note: If the token was created with a CIDR restriction, you must make this API request from a whitelisted address. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.HostFactoryApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    HostFactoryApi apiInstance = new HostFactoryApi(defaultClient);
    String id = "id_example"; // String | Identifier of the host to be created. It will be created within the account of the host factory.
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    Object annotations = null; // Object | Annotations to apply to the new host
    try {
      CreateHost result = apiInstance.createHost(id, xRequestId, annotations);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HostFactoryApi#createHost");
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
 **id** | **String**| Identifier of the host to be created. It will be created within the account of the host factory. |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]
 **annotations** | [**Object**](Object.md)| Annotations to apply to the new host | [optional]

### Return type

[**CreateHost**](CreateHost.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | The response body contains the newly-created host |  -  |
**401** | Authentication information is missing or invalid |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="createToken"></a>
# **createToken**
> List&lt;Object&gt; createToken(expiration, hostFactory, xRequestId, cidr, count)

Creates one or more host identity tokens.

Creates one or more tokens which can be used to bootstrap host identity. Responds with a JSON document containing the tokens and their restrictions.  If the tokens are created with a CIDR restriction, Conjur will only accept them from the whitelisted IP ranges.  ##### Permissions required # &#x60;execute&#x60; privilege on the Host Factory.\&quot; 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.HostFactoryApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    HostFactoryApi apiInstance = new HostFactoryApi(defaultClient);
    String expiration = "expiration_example"; // String | `ISO 8601 datetime` denoting a requested expiration time.
    String hostFactory = "hostFactory_example"; // String | Fully qualified host factory ID
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    List<String> cidr = "cidr_example"; // List<String> | Number of host tokens to create
    Integer count = 56; // Integer | Number of host tokens to create
    try {
      List<Object> result = apiInstance.createToken(expiration, hostFactory, xRequestId, cidr, count);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HostFactoryApi#createToken");
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
 **expiration** | **String**| &#x60;ISO 8601 datetime&#x60; denoting a requested expiration time. |
 **hostFactory** | **String**| Fully qualified host factory ID |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]
 **cidr** | [**List&lt;String&gt;**](String.md)| Number of host tokens to create | [optional]
 **count** | **Integer**| Number of host tokens to create | [optional]

### Return type

**List&lt;Object&gt;**

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Zero or more tokens were created and delivered in the response body |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="revokeToken"></a>
# **revokeToken**
> revokeToken(token, xRequestId)

Revokes a token, immediately disabling it.

Revokes a token, immediately disabling it.  ##### Permissions required  &#x60;update&#x60; privilege on the host factory.\&quot; 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.HostFactoryApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    HostFactoryApi apiInstance = new HostFactoryApi(defaultClient);
    String token = "token_example"; // String | The host factory token to revoke
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      apiInstance.revokeToken(token, xRequestId);
    } catch (ApiException e) {
      System.err.println("Exception when calling HostFactoryApi#revokeToken");
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
 **token** | **String**| The host factory token to revoke |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

null (empty response body)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Token was successfully revoked |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |

