# StatusApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAuthenticators**](StatusApi.md#getAuthenticators) | **GET** /authenticators | Details about which authenticators are on the Conjur Server
[**getGCPAuthenticatorStatus**](StatusApi.md#getGCPAuthenticatorStatus) | **GET** /authn-gcp/{account}/status | Details whether an authentication service has been configured properly
[**getServiceAuthenticatorStatus**](StatusApi.md#getServiceAuthenticatorStatus) | **GET** /{authenticator}/{service_id}/{account}/status | Details whether an authentication service has been configured properly
[**whoAmI**](StatusApi.md#whoAmI) | **GET** /whoami | Provides information about the client making an API request.


<a name="getAuthenticators"></a>
# **getAuthenticators**
> AuthenticatorsResponse getAuthenticators(xRequestId)

Details about which authenticators are on the Conjur Server

Response contains three members: installed, configured, and enabled.  installed: The authenticator is implemented in Conjur and is available for configuration configured: The authenticator has a webservice in the DB that was loaded by policy enabled: The authenticator is enabled (in the DB or in the ENV) and is ready for authentication 

### Example
```java
// Import classes:
import com.cyberark.conjur.sdk.ApiClient;
import com.cyberark.conjur.sdk.ApiException;
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.auth.*;
import com.cyberark.conjur.sdk.models.*;
import com.cyberark.conjur.sdk.endpoint.StatusApi;

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


    StatusApi apiInstance = new StatusApi(defaultClient);
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      AuthenticatorsResponse result = apiInstance.getAuthenticators(xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatusApi#getAuthenticators");
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
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**AuthenticatorsResponse**](AuthenticatorsResponse.md)

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Details about authenticators for this Conjur server |  -  |

<a name="getGCPAuthenticatorStatus"></a>
# **getGCPAuthenticatorStatus**
> AuthenticatorStatus getGCPAuthenticatorStatus(account, xRequestId)

Details whether an authentication service has been configured properly

Once the status webservice has been properly configured and the relevant user groups have been given permissions to access the status webservice, the users in those groups can check the status of the authenticator.  This operation only supports the GCP authenticator  See [Conjur Documentation](https://docs.conjur.org/Latest/en/Content/Integrations/Authn-status.htm) for details on setting up the authenticator status webservice. 

### Example
```java
// Import classes:
import com.cyberark.conjur.sdk.ApiClient;
import com.cyberark.conjur.sdk.ApiException;
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.auth.*;
import com.cyberark.conjur.sdk.models.*;
import com.cyberark.conjur.sdk.endpoint.StatusApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    StatusApi apiInstance = new StatusApi(defaultClient);
    String account = dev; // String | The organization account name
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      AuthenticatorStatus result = apiInstance.getGCPAuthenticatorStatus(account, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatusApi#getGCPAuthenticatorStatus");
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
 **account** | **String**| The organization account name |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**AuthenticatorStatus**](AuthenticatorStatus.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response contains info about the result |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The service was not found |  -  |
**500** | The response contains info about the result |  -  |
**501** | The response contains info about the result |  -  |

<a name="getServiceAuthenticatorStatus"></a>
# **getServiceAuthenticatorStatus**
> AuthenticatorStatus getServiceAuthenticatorStatus(authenticator, serviceId, account, xRequestId)

Details whether an authentication service has been configured properly

Once the status webservice has been properly configured and the relevant user groups have been given permissions to access the status webservice, the users in those groups can check the status of the authenticator.  Supported Authenticators:   - Azure   - OIDC  Not Supported:   - AWS IAM   - Kubernetes   - LDAP  See [Conjur Documentation](https://docs.conjur.org/Latest/en/Content/Integrations/Authn-status.htm) for details on setting up the authenticator status webservice. 

### Example
```java
// Import classes:
import com.cyberark.conjur.sdk.ApiClient;
import com.cyberark.conjur.sdk.ApiException;
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.auth.*;
import com.cyberark.conjur.sdk.models.*;
import com.cyberark.conjur.sdk.endpoint.StatusApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    StatusApi apiInstance = new StatusApi(defaultClient);
    String authenticator = authn-oidc; // String | The type of authenticator
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = dev; // String | The organization account name
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      AuthenticatorStatus result = apiInstance.getServiceAuthenticatorStatus(authenticator, serviceId, account, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatusApi#getServiceAuthenticatorStatus");
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
 **authenticator** | **String**| The type of authenticator |
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| The organization account name |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**AuthenticatorStatus**](AuthenticatorStatus.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response contains info about the result |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The service was not found |  -  |
**500** | The response contains info about the result |  -  |
**501** | The response contains info about the result |  -  |

<a name="whoAmI"></a>
# **whoAmI**
> WhoAmI whoAmI(xRequestId)

Provides information about the client making an API request.

WhoAmI provides information about the client making an API request. It can be used to help troubleshoot configuration by verifying authentication and the client IP address for audit and network access restrictions. For more information, see Host Attributes. 

### Example
```java
// Import classes:
import com.cyberark.conjur.sdk.ApiClient;
import com.cyberark.conjur.sdk.ApiException;
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.auth.*;
import com.cyberark.conjur.sdk.models.*;
import com.cyberark.conjur.sdk.endpoint.StatusApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    StatusApi apiInstance = new StatusApi(defaultClient);
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      WhoAmI result = apiInstance.whoAmI(xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatusApi#whoAmI");
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
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**WhoAmI**](WhoAmI.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Details about the client making the request |  -  |
**401** | Authentication information is missing or invalid |  -  |

