# AuthenticationApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**changePassword**](AuthenticationApi.md#changePassword) | **PUT** /authn/{account}/password | Changes a user’s password.
[**enableAuthenticatorInstance**](AuthenticationApi.md#enableAuthenticatorInstance) | **PATCH** /{authenticator}/{service_id}/{account} | Enables or disables authenticator service instances.
[**getAPIKey**](AuthenticationApi.md#getAPIKey) | **GET** /authn/{account}/login | Gets the API key of a user given the username and password via HTTP Basic Authentication. 
[**getAPIKeyViaLDAP**](AuthenticationApi.md#getAPIKeyViaLDAP) | **GET** /authn-ldap/{service_id}/{account}/login | Gets the Conjur API key of a user given the LDAP username and password via HTTP Basic Authentication. 
[**getAccessToken**](AuthenticationApi.md#getAccessToken) | **POST** /authn/{account}/{login}/authenticate | Gets a short-lived access token, which is required in the header of most subsequent API requests. 
[**getAccessTokenViaAWS**](AuthenticationApi.md#getAccessTokenViaAWS) | **POST** /authn-iam/{service_id}/{account}/{login}/authenticate | Get a short-lived access token for applications running in AWS.
[**getAccessTokenViaAzure**](AuthenticationApi.md#getAccessTokenViaAzure) | **POST** /authn-azure/{service_id}/{account}/{login}/authenticate | Gets a short-lived access token for applications running in Azure.
[**getAccessTokenViaGCP**](AuthenticationApi.md#getAccessTokenViaGCP) | **POST** /authn-gcp/{account}/authenticate | Gets a short-lived access token for applications running in Google Cloud Platform. 
[**getAccessTokenViaKubernetes**](AuthenticationApi.md#getAccessTokenViaKubernetes) | **POST** /authn-k8s/{service_id}/{account}/{login}/authenticate | Gets a short-lived access token for applications running in Kubernetes.
[**getAccessTokenViaLDAP**](AuthenticationApi.md#getAccessTokenViaLDAP) | **POST** /authn-ldap/{service_id}/{account}/{login}/authenticate | Gets a short-lived access token for users and hosts using their LDAP identity to access the Conjur API. 
[**getAccessTokenViaOIDC**](AuthenticationApi.md#getAccessTokenViaOIDC) | **POST** /authn-oidc/{service_id}/{account}/authenticate | Gets a short-lived access token for applications using OpenID Connect (OIDC) to access the Conjur API. 
[**k8sInjectClientCert**](AuthenticationApi.md#k8sInjectClientCert) | **POST** /authn-k8s/{service_id}/inject_client_cert | For applications running in Kubernetes; sends Conjur a certificate signing request (CSR) and requests a client certificate injected into the application&#39;s Kubernetes pod. 
[**rotateApiKey**](AuthenticationApi.md#rotateApiKey) | **PUT** /authn/{account}/api_key | Rotates a role&#39;s API key.


<a name="changePassword"></a>
# **changePassword**
> changePassword(account, body, xRequestId)

Changes a user’s password.

You must provide the login name and current password or API key of the user whose password is to be updated in an HTTP Basic Authentication header. Also replaces the user’s API key with a new securely generated random value. You can fetch the new API key using the Login method.  The Basic authentication-compliant header is formed by: 1. Concatenating the role&#39;s name, a literal colon character &#39;:&#39;,    and the password or API key to create the authentication string. 2. Base64-encoding the authentication string. 3. Prefixing the authentication string with the scheme: &#x60;Basic &#x60;    (note the required space). 4. Providing the result as the value of the &#x60;Authorization&#x60; HTTP header:    &#x60;Authorization: Basic &lt;authentication string&gt;&#x60;.  Your HTTP/REST client probably provides HTTP basic authentication support. For example, &#x60;curl&#x60; and all of the Conjur client libraries provide this.  Note that machine roles (Hosts) do not have passwords. They authenticate using their API keys, while passwords are only used by human users. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String body = "body_example"; // String | New password
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      apiInstance.changePassword(account, body, xRequestId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#changePassword");
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
 **body** | **String**| New password |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

null (empty response body)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | The password has been changed |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**422** | A request parameter was either missing or invalid. |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="enableAuthenticatorInstance"></a>
# **enableAuthenticatorInstance**
> enableAuthenticatorInstance(authenticator, serviceId, account, xRequestId, enabled)

Enables or disables authenticator service instances.

Allows you to either enable or disable a given authenticator service instance.  When you enable or disable an authenticator service instance via this endpoint, the status of the authenticator service instance is stored in the Conjur database. The enablement status of the authenticator service instance may be overridden by setting the &#x60;CONJUR_AUTHENTICATORS&#x60; environment variable on the Conjur server; in the case where this environment variable is set, the database record of whether the authenticator service instance is enabled will be ignored.  **This endpoint is part of an early implementation of support for enabling Conjur authenticators via the API, and is currently available at the Community (or early alpha) level. This endpoint is still subject to breaking changes in the future.** 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    ServiceAuthenticators authenticator = authn-oidc; // ServiceAuthenticators | The authenticator to update
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = dev; // String | Organization account name
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    Boolean enabled = true; // Boolean | 
    try {
      apiInstance.enableAuthenticatorInstance(authenticator, serviceId, account, xRequestId, enabled);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#enableAuthenticatorInstance");
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
 **authenticator** | [**ServiceAuthenticators**](.md)| The authenticator to update | [enum: authn-iam, authn-oidc, authn-ldap, authn-k8s, authn-gcp, authn-azure]
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| Organization account name |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]
 **enabled** | **Boolean**|  | [optional]

### Return type

null (empty response body)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | The config was updated properly |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAPIKey"></a>
# **getAPIKey**
> String getAPIKey(account, xRequestId)

Gets the API key of a user given the username and password via HTTP Basic Authentication. 

Passwords are stored in the Conjur database using &#x60;bcrypt&#x60; with a work factor of 12. Therefore, login is a fairly expensive operation. However, once the API key is obtained, it may be used to inexpensively obtain access tokens by calling the Authenticate method. An access token is required to use most other parts of the Conjur API.  The Basic authentication-compliant header is formed by: 1. Concatenating the role&#39;s name, a literal colon character &#39;:&#39;,    and the password or API key to create the authentication string. 2. Base64-encoding the authentication string. 3. Prefixing the authentication string with the scheme: &#x60;Basic &#x60;    (note the required space). 4. Providing the result as the value of the &#x60;Authorization&#x60; HTTP header:    &#x60;Authorization: Basic &lt;authentication string&gt;&#x60;.  Your HTTP/REST client probably provides HTTP basic authentication support. For example, &#x60;curl&#x60; and all of the Conjur client libraries provide this.  Note that machine roles (Hosts) do not have passwords and do not need to use this endpoint. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      String result = apiInstance.getAPIKey(account, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAPIKey");
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
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body is the API key |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAPIKeyViaLDAP"></a>
# **getAPIKeyViaLDAP**
> String getAPIKeyViaLDAP(serviceId, account, xRequestId)

Gets the Conjur API key of a user given the LDAP username and password via HTTP Basic Authentication. 

Exchange your LDAP credentials for a Conjur API key. Once the API key is obtained, it may be used to inexpensively obtain access tokens by calling the Authenticate method. An access token is required to use most other parts of the Conjur API.  The Basic authentication-compliant header is formed by: 1. Concatenating the LDAP username, a literal colon character &#39;:&#39;,    and the password to create the authentication string. 2. Base64-encoding the authentication string. 3. Prefixing the authentication string with the scheme: &#x60;Basic &#x60;    (note the required space). 4. Providing the result as the value of the &#x60;Authorization&#x60; HTTP header:    &#x60;Authorization: Basic &lt;authentication string&gt;&#x60;.  Your HTTP/REST client probably provides HTTP basic authentication support. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = "account_example"; // String | Organization account name
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      String result = apiInstance.getAPIKeyViaLDAP(serviceId, account, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAPIKeyViaLDAP");
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
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| Organization account name |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body is the API key |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAccessToken"></a>
# **getAccessToken**
> String getAccessToken(account, login, body, acceptEncoding, xRequestId)

Gets a short-lived access token, which is required in the header of most subsequent API requests. 

A client can obtain an access token by presenting a valid login name and API key.  The access token is used to communicate to the REST API that the bearer of the token has been authorized to access the API and perform specific actions specified by the scope that was granted during authorization.  The &#x60;login&#x60; must be URL encoded. For example, &#x60;alice@devops&#x60; must be encoded as &#x60;alice%40devops&#x60;.  The &#x60;service_id&#x60;, if given, must be URL encoded. For example, &#x60;prod/gke&#x60; must be encoded as &#x60;prod%2Fgke&#x60;.  For host authentication, the &#x60;login&#x60; is the host ID with the prefix &#x60;host/&#x60;. For example, the host webserver would login as &#x60;host/webserver&#x60;, and would be encoded as &#x60;host%2Fwebserver&#x60;.  For API usage, the base64-encoded access token is ordinarily passed as an HTTP Authorization header as &#x60;Authorization: Token token&#x3D;&lt;base64-encoded token&gt;&#x60;.  This is the default authentication endpoint only. See other endpoints for details on authenticating to Conjur using another method, e.g. for applications running in Azure or Kubernetes. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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


    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String login = "login_example"; // String | URL-encoded login name. For users, it’s the user ID. For hosts, the login name is `host/<host-id>`
    String body = "body_example"; // String | API Key
    String acceptEncoding = "application/json"; // String | Setting the Accept-Encoding header to base64 will return a pre-encoded access token
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      String result = apiInstance.getAccessToken(account, login, body, acceptEncoding, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAccessToken");
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
 **login** | **String**| URL-encoded login name. For users, it’s the user ID. For hosts, the login name is &#x60;host/&lt;host-id&gt;&#x60; |
 **body** | **String**| API Key |
 **acceptEncoding** | **String**| Setting the Accept-Encoding header to base64 will return a pre-encoded access token | [optional] [default to application/json] [enum: application/json, base64]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response is an access token for conjur |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAccessTokenViaAWS"></a>
# **getAccessTokenViaAWS**
> String getAccessTokenViaAWS(serviceId, account, login, body, acceptEncoding, xRequestId)

Get a short-lived access token for applications running in AWS.

The access token is used to communicate to the REST API that the bearer of the token has been authorized to access the API and perform specific actions specified by the scope that was granted during authorization.  For API usage, the base64-encoded access token is ordinarily passed as an HTTP Authorization header as &#x60;Authorization: Token token&#x3D;&lt;base64-encoded token&gt;&#x60;.  The &#x60;login&#x60; must be URL encoded and the host ID must have the prefix &#x60;host/&#x60;. For example, the host webserver would login as &#x60;host/webserver&#x60;, and would be encoded as &#x60;host%2Fwebserver&#x60;.  The &#x60;service_id&#x60;, if given, must be URL encoded. For example, &#x60;prod/gke&#x60; must be encoded as &#x60;prod%2Fgke&#x60;.  For detailed instructions on authenticating to Conjur using this endpoint, reference the documentation: [AWS IAM Authenticator](https://docs.conjur.org/Latest/en/Content/Operations/Services/AWS_IAM_Authenticator.htm) (&#x60;authn-iam&#x60;). 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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


    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = "account_example"; // String | Organization account name
    String login = "login_example"; // String | URL-encoded login name. For hosts, the login name is `host/<host-id>`
    String body = "body_example"; // String | AWS Signature header
    String acceptEncoding = "application/json"; // String | Setting the Accept-Encoding header to base64 will return a pre-encoded access token
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      String result = apiInstance.getAccessTokenViaAWS(serviceId, account, login, body, acceptEncoding, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAccessTokenViaAWS");
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
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| Organization account name |
 **login** | **String**| URL-encoded login name. For hosts, the login name is &#x60;host/&lt;host-id&gt;&#x60; |
 **body** | **String**| AWS Signature header |
 **acceptEncoding** | **String**| Setting the Accept-Encoding header to base64 will return a pre-encoded access token | [optional] [default to application/json] [enum: application/json, base64]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response is an access token for conjur |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAccessTokenViaAzure"></a>
# **getAccessTokenViaAzure**
> String getAccessTokenViaAzure(serviceId, account, login, acceptEncoding, xRequestId, jwt)

Gets a short-lived access token for applications running in Azure.

The access token is used to communicate to the REST API that the bearer of the token has been authorized to access the API and perform specific actions specified by the scope that was granted during authorization.  For API usage, the base64-encoded access token is ordinarily passed as an HTTP Authorization header as &#x60;Authorization: Token token&#x3D;&lt;base64-encoded token&gt;&#x60;.  The &#x60;login&#x60; must be URL encoded and the host ID must have the prefix &#x60;host/&#x60;. For example, the host webserver would login as &#x60;host/webserver&#x60;, and would be encoded as &#x60;host%2Fwebserver&#x60;.  The &#x60;service_id&#x60;, if given, must be URL encoded. For example, &#x60;prod/gke&#x60; must be encoded as &#x60;prod%2Fgke&#x60;.  To authenticate to Conjur using this endpoint, reference the detailed documentation: [Azure Authenticator](https://docs.conjur.org/Latest/en/Content/Operations/Services/azure_authn.htm) (&#x60;authn-azure&#x60;). 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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


    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = "account_example"; // String | Organization account name
    String login = "login_example"; // String | URL-encoded login name. For users, it’s the user ID. For hosts, the login name is `host/<host-id>`
    String acceptEncoding = "application/json"; // String | Setting the Accept-Encoding header to base64 will return a pre-encoded access token
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    String jwt = "jwt_example"; // String | 
    try {
      String result = apiInstance.getAccessTokenViaAzure(serviceId, account, login, acceptEncoding, xRequestId, jwt);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAccessTokenViaAzure");
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
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| Organization account name |
 **login** | **String**| URL-encoded login name. For users, it’s the user ID. For hosts, the login name is &#x60;host/&lt;host-id&gt;&#x60; |
 **acceptEncoding** | **String**| Setting the Accept-Encoding header to base64 will return a pre-encoded access token | [optional] [default to application/json] [enum: application/json, base64]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]
 **jwt** | **String**|  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response is an access token for conjur |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAccessTokenViaGCP"></a>
# **getAccessTokenViaGCP**
> String getAccessTokenViaGCP(account, acceptEncoding, xRequestId, jwt)

Gets a short-lived access token for applications running in Google Cloud Platform. 

Use the GCP Authenticator API to send an authentication request from a Google Cloud service to Conjur.  For more information, see [the documentation](https://docs.conjur.org/Latest/en/Content/Operations/Services/cjr-gcp-authn.htm). 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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


    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String account = dev; // String | Organization account name
    String acceptEncoding = "acceptEncoding_example"; // String | Setting the Accept-Encoding header to base64 will return a pre-encoded access token
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    String jwt = "jwt_example"; // String | 
    try {
      String result = apiInstance.getAccessTokenViaGCP(account, acceptEncoding, xRequestId, jwt);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAccessTokenViaGCP");
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
 **acceptEncoding** | **String**| Setting the Accept-Encoding header to base64 will return a pre-encoded access token | [optional] [enum: base64]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]
 **jwt** | **String**|  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body is the API key |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAccessTokenViaKubernetes"></a>
# **getAccessTokenViaKubernetes**
> String getAccessTokenViaKubernetes(serviceId, account, login, acceptEncoding, xRequestId)

Gets a short-lived access token for applications running in Kubernetes.

The access token is used to communicate to the REST API that the bearer of the token has been authorized to access the API and perform specific actions specified by the scope that was granted during authorization.  For API usage, the base64-encoded access token is ordinarily passed as an HTTP Authorization header as &#x60;Authorization: Token token&#x3D;&lt;base64-encoded token&gt;&#x60;.  The &#x60;login&#x60; must be URL encoded and the host ID must have the prefix &#x60;host/&#x60;. For example, the host webserver would login as &#x60;host/webserver&#x60;, and would be encoded as &#x60;host%2Fwebserver&#x60;.  The &#x60;service_id&#x60;, if given, must be URL encoded. For example, &#x60;prod/gke&#x60; must be encoded as &#x60;prod%2Fgke&#x60;.  To authenticate to Conjur using this endpoint, reference the detailed documentation: [Kubernetes Authenticator](https://docs.conjur.org/Latest/en/Content/Operations/Services/k8s_auth.htm) (&#x60;authn-k8s&#x60;). 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = "account_example"; // String | Organization account name
    String login = "login_example"; // String | URL-encoded login name. For users, it’s the user ID. For hosts, the login name is `host/<host-id>`
    String acceptEncoding = "application/json"; // String | Setting the Accept-Encoding header to base64 will return a pre-encoded access token
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      String result = apiInstance.getAccessTokenViaKubernetes(serviceId, account, login, acceptEncoding, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAccessTokenViaKubernetes");
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
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| Organization account name |
 **login** | **String**| URL-encoded login name. For users, it’s the user ID. For hosts, the login name is &#x60;host/&lt;host-id&gt;&#x60; |
 **acceptEncoding** | **String**| Setting the Accept-Encoding header to base64 will return a pre-encoded access token | [optional] [default to application/json] [enum: application/json, base64]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**String**

### Authorization

[conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response is an access token for conjur |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAccessTokenViaLDAP"></a>
# **getAccessTokenViaLDAP**
> String getAccessTokenViaLDAP(serviceId, account, login, acceptEncoding, xRequestId, body)

Gets a short-lived access token for users and hosts using their LDAP identity to access the Conjur API. 

The access token is used to communicate to the REST API that the bearer of the token has been authorized to access the API and perform specific actions specified by the scope that was granted during authorization.  For API usage, the base64-encoded access token is ordinarily passed as an HTTP Authorization header as &#x60;Authorization: Token token&#x3D;&lt;base64-encoded token&gt;&#x60;.  The &#x60;login&#x60; must be URL encoded. For example, &#x60;alice@devops&#x60; must be encoded as &#x60;alice%40devops&#x60;.  The &#x60;service_id&#x60;, if given, must be URL encoded. For example, &#x60;prod/gke&#x60; must be encoded as &#x60;prod%2Fgke&#x60;.  For host authentication, the &#x60;login&#x60; is the host ID with the prefix &#x60;host/&#x60;. For example, the host webserver would login as &#x60;host/webserver&#x60;, and would be encoded as &#x60;host%2Fwebserver&#x60;.  To authenticate to Conjur using a LDAP, reference the detailed documentation: [LDAP Authenticator](https://docs.conjur.org/Latest/en/Content/Integrations/ldap/ldap_authenticator.html) (&#x60;authn-ldap&#x60;). 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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


    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = "account_example"; // String | Organization account name
    String login = "login_example"; // String | URL-encoded login name. For users, it’s the user ID. For hosts, the login name is `host/<host-id>`
    String acceptEncoding = "application/json"; // String | Setting the Accept-Encoding header to base64 will return a pre-encoded access token
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    String body = "body_example"; // String | API key
    try {
      String result = apiInstance.getAccessTokenViaLDAP(serviceId, account, login, acceptEncoding, xRequestId, body);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAccessTokenViaLDAP");
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
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| Organization account name |
 **login** | **String**| URL-encoded login name. For users, it’s the user ID. For hosts, the login name is &#x60;host/&lt;host-id&gt;&#x60; |
 **acceptEncoding** | **String**| Setting the Accept-Encoding header to base64 will return a pre-encoded access token | [optional] [default to application/json] [enum: application/json, base64]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]
 **body** | **String**| API key | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response is an access token for conjur |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**500** | Malfromed request, rejected by the server |  -  |

<a name="getAccessTokenViaOIDC"></a>
# **getAccessTokenViaOIDC**
> String getAccessTokenViaOIDC(serviceId, account, xRequestId, idToken)

Gets a short-lived access token for applications using OpenID Connect (OIDC) to access the Conjur API. 

Use the OIDC Authenticator to leverage the identity layer provided by OIDC to authenticate with Conjur.  For more information see [the documentation](https://docs.conjur.org/Latest/en/Content/OIDC/OIDC.htm). 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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


    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String account = "account_example"; // String | Organization account name
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    String idToken = "idToken_example"; // String | 
    try {
      String result = apiInstance.getAccessTokenViaOIDC(serviceId, account, xRequestId, idToken);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#getAccessTokenViaOIDC");
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
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **account** | **String**| Organization account name |
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]
 **idToken** | **String**|  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response is an access token for conjur |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**502** | Error connecting conjur to the OIDC provider |  -  |

<a name="k8sInjectClientCert"></a>
# **k8sInjectClientCert**
> k8sInjectClientCert(serviceId, body, hostIdPrefix, xRequestId)

For applications running in Kubernetes; sends Conjur a certificate signing request (CSR) and requests a client certificate injected into the application&#39;s Kubernetes pod. 

This request sends a Certificate Signing Request to Conjur, which uses the Kubernetes API to inject a client certificate into the application pod.  This endpoint requires a properly configured Conjur Certificate Authority service alongside a properly configured and enabled Kubernetes authenticator. For detailed instructions, see [the documentation](https://docs.conjur.org/Latest/en/Content/Integrations/kubernetes.htm). 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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


    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String serviceId = prod%2fgke; // String | URL-Encoded authenticator service ID
    String body = "body_example"; // String | Valid certificate signing request that includes the host identity suffix as the CSR common name 
    String hostIdPrefix = host/conjur/authn-k8s/my-authenticator-id/apps; // String | Dot-separated policy tree, prefixed by `host.`, where the application identity is defined
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      apiInstance.k8sInjectClientCert(serviceId, body, hostIdPrefix, xRequestId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#k8sInjectClientCert");
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
 **serviceId** | **String**| URL-Encoded authenticator service ID |
 **body** | **String**| Valid certificate signing request that includes the host identity suffix as the CSR common name  |
 **hostIdPrefix** | **String**| Dot-separated policy tree, prefixed by &#x60;host.&#x60;, where the application identity is defined | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

null (empty response body)

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth), [conjurKubernetesMutualTls](../README.md#conjurKubernetesMutualTls)

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**202** | The injected certificate was accepted. |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |

<a name="rotateApiKey"></a>
# **rotateApiKey**
> String rotateApiKey(account, role, xRequestId)

Rotates a role&#39;s API key.

Any role can rotate its own API key. The name and password (for users) or current API key (for hosts and users) of the role must be provided via HTTP Basic Authorization.  To rotate another role&#39;s API key, you may provide your name and password (for users) or current API key (for hosts and users) via HTTP Basic Authorization with the request. Alternatively, you may provide your Conjur access token via the standard Conjur &#x60;Authorization&#x60; header.  The Basic authentication-compliant header is formed by: 1. Concatenating the role&#39;s name, a literal colon character &#39;:&#39;,    and the password or API key to create the authentication string. 2. Base64-encoding the authentication string. 3. Prefixing the authentication string with the scheme: &#x60;Basic &#x60;    (note the required space). 4. Providing the result as the value of the &#x60;Authorization&#x60; HTTP header:    &#x60;Authorization: Basic &lt;authentication string&gt;&#x60;.  Your HTTP/REST client probably provides HTTP basic authentication support. For example, &#x60;curl&#x60; and all of the Conjur client libraries provide this.  If using the Conjur &#x60;Authorization&#x60; header, its value should be set to &#x60;Token token&#x3D;&lt;base64-encoded access token&gt;&#x60;.  Note that the body of the request must be the empty string. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.endpoint.AuthenticationApi;

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

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String role = "role_example"; // String | (**Optional**) role specifier in `{kind}:{identifier}` format  ##### Permissions required  `update` privilege on the role whose API key is being rotated. 
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      String result = apiInstance.rotateApiKey(account, role, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#rotateApiKey");
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
 **role** | **String**| (**Optional**) role specifier in &#x60;{kind}:{identifier}&#x60; format  ##### Permissions required  &#x60;update&#x60; privilege on the role whose API key is being rotated.  | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**String**

### Authorization

[basicAuth](../README.md#basicAuth), [conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body is the API key |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**422** | A request parameter was either missing or invalid. |  -  |
**500** | Malfromed request, rejected by the server |  -  |

