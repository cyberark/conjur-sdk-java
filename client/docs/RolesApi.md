# RolesApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addMemberToRole**](RolesApi.md#addMemberToRole) | **POST** /roles/{account}/{kind}/{identifier} | Update or modify an existing role membership
[**removeMemberFromRole**](RolesApi.md#removeMemberFromRole) | **DELETE** /roles/{account}/{kind}/{identifier} | Deletes an existing role membership
[**showRole**](RolesApi.md#showRole) | **GET** /roles/{account}/{kind}/{identifier} | Get role information


<a name="addMemberToRole"></a>
# **addMemberToRole**
> addMemberToRole(account, kind, identifier, members, member, xRequestId)

Update or modify an existing role membership

Updates or modifies an existing role membership.  If a role A is granted to a role B, then role A is said to have role B as a member. These relationships are described in the “members” portion of the returned JSON.  When the &#x60;members&#x60; query parameter is provided, you will get the members of a role.  When the &#x60;members&#x60; and &#x60;member&#x60; query parameters are provided, the role specfified by &#x60;member&#x60; will be added as a member of the role specified in the endpoint URI. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.api.RolesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    RolesApi apiInstance = new RolesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String kind = user; // String | Type of resource
    String identifier = admin; // String | ID of the role for which to get the information about
    String members = "members_example"; // String | Returns a list of the Role's members.
    String member = "member_example"; // String | The identifier of the Role to be added as a member.
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      apiInstance.addMemberToRole(account, kind, identifier, members, member, xRequestId);
    } catch (ApiException e) {
      System.err.println("Exception when calling RolesApi#addMemberToRole");
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
 **identifier** | **String**| ID of the role for which to get the information about |
 **members** | **String**| Returns a list of the Role&#39;s members. |
 **member** | **String**| The identifier of the Role to be added as a member. |
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
**204** | Member was added to role successfully |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="removeMemberFromRole"></a>
# **removeMemberFromRole**
> removeMemberFromRole(account, kind, identifier, members, member, xRequestId)

Deletes an existing role membership

Deletes an existing role membership.  If a role A is granted to a role B, then role A is said to have role B as a member. These relationships are described in the “members” portion of the returned JSON.  When the &#x60;members&#x60; query parameter is provided, you will get the members of a role.  When the &#x60;members&#x60; and &#x60;member&#x60; query parameters are provided, the role specfified by &#x60;member&#x60; will be removed as a member of the role specified in the endpoint URI. 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.api.RolesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    RolesApi apiInstance = new RolesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String kind = user; // String | Type of resource
    String identifier = admin; // String | ID of the role for which to get the information about
    String members = "members_example"; // String | Returns a list of the Role's members.
    String member = "member_example"; // String | The identifier of the Role to be added as a member.
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      apiInstance.removeMemberFromRole(account, kind, identifier, members, member, xRequestId);
    } catch (ApiException e) {
      System.err.println("Exception when calling RolesApi#removeMemberFromRole");
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
 **identifier** | **String**| ID of the role for which to get the information about |
 **members** | **String**| Returns a list of the Role&#39;s members. |
 **member** | **String**| The identifier of the Role to be added as a member. |
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
**204** | Member was deleted from role successfully |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

<a name="showRole"></a>
# **showRole**
> Object showRole(account, kind, identifier, all, memberships, members, offset, limit, count, search, graph, xRequestId)

Get role information

Gets detailed information about a specific role, including the role members.  If a role A is granted to a role B, then role A is said to have role B as a member. These relationships are described in the “members” portion of the returned JSON.  ##### Listing members  If &#x60;members&#x60; is provided, you will get the members of a role.  If a &#x60;kind&#x60; query parameter is given, narrows results to only resources of that kind.  If a &#x60;limit&#x60; is given, returns no more than that number of results. Providing an &#x60;offset&#x60; skips a number of resources before returning the rest. In addition, providing an &#x60;offset&#x60; will give limit a default value of 10 if none other is provided. These two parameters can be combined to page through results.  If the parameter &#x60;count&#x60; is true, returns only the number of items in the list.  ##### Text search  If the search parameter is provided, narrows results to those pertaining to the search query. Search works across resource IDs and the values of annotations. It weights results so that those with matching id or a matching value of an annotation called name appear first, then those with another matching annotation value, and finally those with a matching kind.  ##### Parameter Priority  If Conjur is given any combination of optional parameters, it responds with ONLY results for the parameter of the highest priority.  1. &#x60;graph&#x60; 2. &#x60;all&#x60; 3. &#x60;memberships&#x60; 4. &#x60;members&#x60; 

### Example
```java
// Import classes:
import org.conjur.sdk.ApiClient;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.Configuration;
import org.conjur.sdk.auth.*;
import org.conjur.sdk.models.*;
import org.conjur.sdk.api.RolesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: conjurAuth
    ApiKeyAuth conjurAuth = (ApiKeyAuth) defaultClient.getAuthentication("conjurAuth");
    conjurAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //conjurAuth.setApiKeyPrefix("Token");

    RolesApi apiInstance = new RolesApi(defaultClient);
    String account = "account_example"; // String | Organization account name
    String kind = user; // String | Type of resource
    String identifier = admin; // String | ID of the role for which to get the information about
    String all = "all_example"; // String | Returns an array of Role IDs representing all role memberships, expanded recursively.
    String memberships = "memberships_example"; // String | Returns all direct role memberships (members not expanded recursively).
    String members = "members_example"; // String | Returns a list of the Role's members.
    Integer offset = 56; // Integer | When listing members, start at this item number.
    Integer limit = 56; // Integer | When listing members, return up to this many results.
    Boolean count = true; // Boolean | When listing members, if `true`, return only the count of members.
    String search = user; // String | When listing members, the results will be narrowed to only those matching the provided string
    String graph = ; // String | If included in the query returns a graph view of the role
    String xRequestId = test-id; // String | Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one. 
    try {
      Object result = apiInstance.showRole(account, kind, identifier, all, memberships, members, offset, limit, count, search, graph, xRequestId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RolesApi#showRole");
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
 **identifier** | **String**| ID of the role for which to get the information about |
 **all** | **String**| Returns an array of Role IDs representing all role memberships, expanded recursively. | [optional]
 **memberships** | **String**| Returns all direct role memberships (members not expanded recursively). | [optional]
 **members** | **String**| Returns a list of the Role&#39;s members. | [optional]
 **offset** | **Integer**| When listing members, start at this item number. | [optional]
 **limit** | **Integer**| When listing members, return up to this many results. | [optional]
 **count** | **Boolean**| When listing members, if &#x60;true&#x60;, return only the count of members. | [optional]
 **search** | **String**| When listing members, the results will be narrowed to only those matching the provided string | [optional]
 **graph** | **String**| If included in the query returns a graph view of the role | [optional]
 **xRequestId** | **String**| Add an ID to the request being made so it can be tracked in Conjur. If not provided the server will automatically generate one.  | [optional]

### Return type

**Object**

### Authorization

[conjurAuth](../README.md#conjurAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The response body contains the requested role(s)/member(s) |  -  |
**400** | The server cannot process the request due to malformed request syntax |  -  |
**401** | Authentication information is missing or invalid |  -  |
**403** | The authenticated user lacks the necessary privileges |  -  |
**404** | The requested resource does not exist, the authenticated user lacks the required privileges to enumerate this resource, or its value has not been set |  -  |
**422** | A request parameter was either missing or invalid. |  -  |

