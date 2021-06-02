/*
 * Conjur
 * This is an API definition for CyberArk Conjur OSS. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.1.0
 * Contact: conj_maintainers@cyberark.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.conjur.sdk.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.conjur.sdk.*;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.endpoint.*;
import org.conjur.sdk.model.*;
import org.junit.*;


/**
 * API tests for RolesApi.
 */
public class RolesApiTest extends ConfiguredTest {

    private static final String ROLES_POLICY = String.join("\n",
        "- !layer testLayer",
        "- !group",
        "  id: userGroup",
        "  annotations:",
        "    editable: true",
        "- !group",
        "  id: anotherGroup",
        "  annotations:",
        "    editable: true",
        "- !user bob",
        "- !user alice"
    );

    private static final String BOB_ID = String.format(
        "%s:user:bob", System.getenv("CONJUR_ACCOUNT"));

    private final RolesApi api = new RolesApi();

    /**
     * Setup roles to test by loading new policy.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @BeforeClass
    public static void setUpClass() throws ApiException {
        ConfiguredTest.setUpClass();
        PoliciesApi api = new PoliciesApi();
        api.replacePolicy(System.getenv("CONJUR_ACCOUNT"), "root", ROLES_POLICY);
    }

    /**
     * Update or modify an existing role membership.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addMemberToRoleTest() throws ApiException {
        String member = BOB_ID;
        ApiResponse<?> response = api.addMemberToRoleWithHttpInfo(
            account,
            "group",
            "userGroup",
            "",
            member
        );

        Assert.assertEquals(204, response.getStatusCode());
    }

    /**
     * Deletes an existing role membership.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeMemberFromRoleTest() throws ApiException {
        String kind = "group";
        String identifier = "userGroup";
        String members = "";
        String member = BOB_ID;
        ApiResponse<?> response = api.removeMemberFromRoleWithHttpInfo(
            account,
            kind,
            identifier,
            members,
            member
        );

        Assert.assertEquals(204, response.getStatusCode());
    }

    /**
     * Get role information.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void showRoleTest() throws ApiException {
        String kind = "user";
        String identifier = "admin";

        ApiResponse<Object> response = api.showRoleWithHttpInfo(
            account,
            kind,
            identifier
        );

        Assert.assertEquals(200, response.getStatusCode());
    }
}