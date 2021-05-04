# ResourcesApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**showResource**](ResourcesApi.md#showResource) | **GET** /resources/{account}/{kind}/{identifier} | Shows a description of a single resource.
[**showResourcesForAccount**](ResourcesApi.md#showResourcesForAccount) | **GET** /resources/{account} | Lists resources within an organization account.
[**showResourcesForAllAccounts**](ResourcesApi.md#showResourcesForAllAccounts) | **GET** /resources | Lists resources within an organization account.
[**showResourcesForKind**](ResourcesApi.md#showResourcesForKind) | **GET** /resources/{account}/{kind} | Lists resources of the same kind within an organization account.


<a name="showResource"></a>
# **showResource**
> Resource showResource(account, kind, identifier, permittedRoles, privilege, check, role, xRequestId)

Shows a description of a single resource.

Details about a single resource.  If &#x60;permitted_roles&#x60; and &#x60;privilege&#x60; are given, Conjur lists the roles with the specified privilege on the resource.  If &#x60;check&#x60;, &#x60;privilege&#x60; and &#x60;role&#x60; are given, Conjur checks if the specified role has the privilege on the resource.  If &#x60;permitted_roles&#x60; and &#x60;check&#x60; are both given, Conjur responds to the &#x60;check&#x60; call ONLY.  ##### Permissions Required 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.api.ResourcesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    ResourcesApi apiInstance = new ResourcesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String kind = user; // String | Type of resource
    String identifier = conjur/authn-iam/test; // String | ID of the resource for which to get the information about
    Boolean permittedRoles = true; // Boolean | Lists the roles which have the named privilege on a resource.
    String privilege = "privilege_example"; // String | Level of privilege to filter on. Can only be used in combination with `permitted_roles` or `check` parameter.
    Boolean check = true; // Boolean | Check whether a role has a privilege on a resource.
    String role = myorg:host:host1; // String | Role to check privilege on. Can only be used in combination with `check` parameter.
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      Resource result = apiInstance.showResource(account, kind, identifier, permittedRoles, privilege, check, role, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ResourcesApi#showResource");
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
 **permittedRoles** | **Boolean**| Lists the roles which have the named privilege on a resource. | [optional]
 **privilege** | **String**| Level of privilege to filter on. Can only be used in combination with &#x60;permitted_roles&#x60; or &#x60;check&#x60; parameter. | [optional]
 **check** | **Boolean**| Check whether a role has a privilege on a resource. | [optional]
 **role** | **String**| Role to check privilege on. Can only be used in combination with &#x60;check&#x60; parameter. | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**Resource**](Resource.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body contains the list of role memberships or permitted roles |  -  |
**204** | Permissions check was successful |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="showResourcesForAccount"></a>
# **showResourcesForAccount**
> List&lt;Resource&gt; showResourcesForAccount(account, kind, search, offset, limit, count, role, actingAs, xRequestId)

Lists resources within an organization account.

Lists resources within an organization account.  If a &#x60;kind&#x60; query parameter is given, narrows results to only resources of that kind.  If a &#x60;limit&#x60; is given, returns no more than that number of results. Providing an &#x60;offset&#x60; skips a number of resources before returning the rest. In addition, providing an &#x60;offset&#x60; will give &#x60;limit&#x60; a default value of 10 if none other is provided. These two parameters can be combined to page through results.  If the parameter &#x60;count&#x60; is &#x60;true&#x60;, returns only the number of items in the list.  ##### Text search  If the &#x60;search&#x60; parameter is provided, narrows results to those pertaining to the search query. Search works across resource IDs and the values of annotations. It weighs results so that those with matching id or a matching value of an annotation called &#x60;name&#x60; appear first, then those with another matching annotation value, and finally those with a matching  &#x60;kind&#x60;. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.api.ResourcesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    ResourcesApi apiInstance = new ResourcesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String kind = user; // String | Type of resource
    String search = db; // String | Filter resources based on this value by name
    Integer offset = 56; // Integer | When listing resources, start at this item number.
    Integer limit = 56; // Integer | When listing resources, return up to this many results.
    Boolean count = true; // Boolean | When listing resources, if `true`, return only the count of the results.
    String role = myorg:host:host1; // String | Retrieves the resources list for a different role if the authenticated role has access
    String actingAs = myorg:host:host1; // String | Retrieves the resources list for a different role if the authenticated role has access
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      List<Resource> result = apiInstance.showResourcesForAccount(account, kind, search, offset, limit, count, role, actingAs, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ResourcesApi#showResourcesForAccount");
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
 **kind** | **String**| Type of resource | [optional]
 **search** | **String**| Filter resources based on this value by name | [optional]
 **offset** | **Integer**| When listing resources, start at this item number. | [optional]
 **limit** | **Integer**| When listing resources, return up to this many results. | [optional]
 **count** | **Boolean**| When listing resources, if &#x60;true&#x60;, return only the count of the results. | [optional]
 **role** | **String**| Retrieves the resources list for a different role if the authenticated role has access | [optional]
 **actingAs** | **String**| Retrieves the resources list for a different role if the authenticated role has access | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**List&lt;Resource&gt;**](Resource.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body contains a list of resources |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="showResourcesForAllAccounts"></a>
# **showResourcesForAllAccounts**
> List&lt;Resource&gt; showResourcesForAllAccounts(account, kind, search, offset, limit, count, role, actingAs, xRequestId)

Lists resources within an organization account.

Lists resources within an organization account.  In the absence of an &#x60;account&#x60; query parameter, shows results for the account of the authorization token user.  If an &#x60;account&#x60; query parameter is given, shows results for the specified account.  If a &#x60;kind&#x60; query parameter is given, narrows results to only resources of that kind.  If a &#x60;limit&#x60; is given, returns no more than that number of results. Providing an &#x60;offset&#x60; skips a number of resources before returning the rest. In addition, providing an &#x60;offset&#x60; will give &#x60;limit&#x60; a default value of 10 if none other is provided. These two parameters can be combined to page through results.  If the parameter &#x60;count&#x60; is &#x60;true&#x60;, returns only the number of items in the list.  ##### Text search  If the &#x60;search&#x60; parameter is provided, narrows results to those pertaining to the search query. Search works across resource IDs and the values of annotations. It weighs results so that those with matching id or a matching value of an annotation called &#x60;name&#x60; appear first, then those with another matching annotation value, and finally those with a matching  &#x60;kind&#x60;.\&quot; 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.api.ResourcesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    ResourcesApi apiInstance = new ResourcesApi(defaultClient);
    String account = myorg; // String | Organization account name
    String kind = user; // String | Type of resource
    String search = db; // String | Filter resources based on this value by name
    Integer offset = 56; // Integer | When listing resources, start at this item number.
    Integer limit = 56; // Integer | When listing resources, return up to this many results.
    Boolean count = true; // Boolean | When listing resources, if `true`, return only the count of the results.
    String role = myorg:host:host1; // String | Retrieves the resources list for a different role if the authenticated role has access
    String actingAs = myorg:host:host1; // String | Retrieves the resources list for a different role if the authenticated role has access
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      List<Resource> result = apiInstance.showResourcesForAllAccounts(account, kind, search, offset, limit, count, role, actingAs, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ResourcesApi#showResourcesForAllAccounts");
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
 **account** | **String**| Organization account name | [optional]
 **kind** | **String**| Type of resource | [optional]
 **search** | **String**| Filter resources based on this value by name | [optional]
 **offset** | **Integer**| When listing resources, start at this item number. | [optional]
 **limit** | **Integer**| When listing resources, return up to this many results. | [optional]
 **count** | **Boolean**| When listing resources, if &#x60;true&#x60;, return only the count of the results. | [optional]
 **role** | **String**| Retrieves the resources list for a different role if the authenticated role has access | [optional]
 **actingAs** | **String**| Retrieves the resources list for a different role if the authenticated role has access | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**List&lt;Resource&gt;**](Resource.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body contains a list of resources |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="showResourcesForKind"></a>
# **showResourcesForKind**
> List&lt;Resource&gt; showResourcesForKind(account, kind, search, offset, limit, count, role, actingAs, xRequestId)

Lists resources of the same kind within an organization account.

Lists resources of the same kind within an organization account.  Kinds of resources include: policy, user, host, group, layer, or variable  If a &#x60;limit&#x60; is given, returns no more than that number of results. Providing an &#x60;offset&#x60; skips a number of resources before returning the rest. In addition, providing an &#x60;offset&#x60; will give &#x60;limit&#x60; a default value of 10 if none other is provided. These two parameters can be combined to page through results.  If the parameter &#x60;count&#x60; is &#x60;true&#x60;, returns only the number of items in the list.  ##### Text search  If the &#x60;search&#x60; parameter is provided, narrows results to those pertaining to the search query. Search works across resource IDs and the values of annotations. It weighs results so that those with matching id or a matching value of an annotation called &#x60;name&#x60; appear first, then those with another matching annotation value, and finally those with a matching  &#x60;kind&#x60;. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.api.ResourcesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    ResourcesApi apiInstance = new ResourcesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String kind = user; // String | Type of resource
    String search = db; // String | Filter resources based on this value by name
    Integer offset = 56; // Integer | When listing resources, start at this item number.
    Integer limit = 56; // Integer | When listing resources, return up to this many results.
    Boolean count = true; // Boolean | When listing resources, if `true`, return only the count of the results.
    String role = myorg:host:host1; // String | Retrieves the resources list for a different role if the authenticated role has access
    String actingAs = myorg:host:host1; // String | Retrieves the resources list for a different role if the authenticated role has access
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      List<Resource> result = apiInstance.showResourcesForKind(account, kind, search, offset, limit, count, role, actingAs, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ResourcesApi#showResourcesForKind");
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
 **search** | **String**| Filter resources based on this value by name | [optional]
 **offset** | **Integer**| When listing resources, start at this item number. | [optional]
 **limit** | **Integer**| When listing resources, return up to this many results. | [optional]
 **count** | **Boolean**| When listing resources, if &#x60;true&#x60;, return only the count of the results. | [optional]
 **role** | **String**| Retrieves the resources list for a different role if the authenticated role has access | [optional]
 **actingAs** | **String**| Retrieves the resources list for a different role if the authenticated role has access | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

[**List&lt;Resource&gt;**](Resource.md)

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body contains a list of resources |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

