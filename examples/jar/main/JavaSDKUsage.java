package main;

import com.cyberark.conjur.sdk.*;
import com.cyberark.conjur.sdk.endpoint.*;
import com.cyberark.conjur.sdk.model.*;
import com.cyberark.conjur.sdk.auth.*;

import com.google.gson.internal.LinkedTreeMap;

public class JavaSDKUsage {
  public static String account = "dev";
  public static String resourceType = "variable";

  public static void main(String[] args) throws ApiException {
    loadTestPolicy();
    setAndRetrieveSecret();
    retrieveResource();
    retrieveRole();
  }

  /**
   * Load a policy into conjur for testing. Read more about Conjur policies
   * here: https://docs.conjur.org/Latest/en/Content/Operations/Policy/policy-syntax.htm?tocpath=Fundamentals%7CPolicy%20Management%7C_____3
   */
  public static void loadTestPolicy() throws ApiException {
    PoliciesApi policiesApi = new PoliciesApi();
    String testPolicy = "- !variable testSecret\n";

    policiesApi.loadPolicy(account, "root", testPolicy);
  }

  public static void setAndRetrieveSecret() throws ApiException {
    String secretValue = "some secret data";
    SecretsApi secretsApi = new SecretsApi();
    String secretId = "testSecret";

    secretsApi.createSecret(account, resourceType, secretId, null, null, secretValue);
    String retrievedValue = secretsApi.getSecret(account, resourceType, secretId);
    System.out.println(String.format("Value retrieved for testSecret: \"%s\"", retrievedValue));
  }

  public static void retrieveResource() throws ApiException {
    ResourcesApi resourcesApi = new ResourcesApi();
    String resourceId = "testSecret";

    Resource resource = resourcesApi.showResource(account, resourceType, resourceId);
    System.out.println(resource);
  }

  public static void retrieveRole() throws ApiException {
    RolesApi rolesApi = new RolesApi();
    String role = "user";
    String login = "admin";

    LinkedTreeMap createdRole = (LinkedTreeMap) rolesApi.showRole(account, role, login);
    System.out.println(String.format("Data for admin role: %s", createdRole));
  }
}
