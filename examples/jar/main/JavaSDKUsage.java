package main;

import java.util.logging.Logger;
import com.cyberark.conjur.sdk.*;
import com.cyberark.conjur.sdk.endpoint.*;
import com.cyberark.conjur.sdk.model.*;
import com.cyberark.conjur.sdk.auth.*;

import com.google.gson.internal.LinkedTreeMap;

public class JavaSDKUsage {
    
    private static final Logger LOGGER = Logger.getLogger(JavaSDKUsage.class.getName());

    public static String account = System.getenv().getOrDefault("CONJUR_ACCOUNT", null);
    public static String username = System.getenv().getOrDefault("CONJUR_AUTHN_LOGIN", null);
    public static String authToken = System.getenv().getOrDefault("CONJUR_AUTHN_TOKEN", null);

    public static String secretId = "testSecret";
    public static String resourceType = "variable";

    public static void main(String[] args) throws ApiException {
        loadTestPolicy();
        setAndRetrieveSecret();
        retrieveResource();
        retrieveRole();
    }

    /**
     * Utility method to get the secret ID based on the authentication token
     */
    private static String getSecretId(String secretId) {
        return (authToken != null) ? "data/" + secretId : secretId;
    }

    /**
     * Load a policy into Conjur for testing.
     */
    public static void loadTestPolicy() throws ApiException {
        try {
            PoliciesApi policiesApi = new PoliciesApi();
            String testPolicy = "- !variable testSecret\n";

            if (authToken != null) {
                // When authToken is provided, load the policy under "data" for cloud
                policiesApi.loadPolicy(account, "data", testPolicy);
            } else {
                // When no authToken is provided, load the policy under "root" for OSS/EP
                policiesApi.loadPolicy(account, "root", testPolicy);
            }
        } catch (ApiException e) {
            LOGGER.info("Error loading policy: " + e.getCode() + " - " + e.getMessage());
        }
    }

    public static void setAndRetrieveSecret() throws ApiException {
        try {
            String secretValue = "some secret data";
            SecretsApi secretsApi = new SecretsApi();
            
            // Get the secretId based on the authentication status
            String secretIdToUse = getSecretId(secretId);

                secretsApi.createSecret(account, resourceType, secretIdToUse, null, null, secretValue);
                String retrievedValue = secretsApi.getSecret(account, resourceType, secretIdToUse);
        } catch (ApiException e) {
            LOGGER.info("Error setting and retrieving secret: " + e.getCode() + " - " + e.getMessage());
        }
    }

    public static void retrieveResource() throws ApiException {
        try {
            ResourcesApi resourcesApi = new ResourcesApi();
            
            // Get the secretId based on the authentication status
            String secretIdToUse = getSecretId(secretId);

            Resource resource = resourcesApi.showResource(account, resourceType, secretIdToUse);
        } catch (ApiException e) {
            LOGGER.info("Error retrieving resource: " + e.getCode() + " - " + e.getMessage());
        }
    }

    public static void retrieveRole() throws ApiException {
        try {
            RolesApi rolesApi = new RolesApi();
            String role = "user";
            String login = (authToken != null) ? username : "admin";

            LinkedTreeMap createdRole = (LinkedTreeMap) rolesApi.showRole(account, role, login);
        } catch (ApiException e) {
            LOGGER.info("Error retrieving role: " + e.getCode() + " - " + e.getMessage());
        }
    }
}
