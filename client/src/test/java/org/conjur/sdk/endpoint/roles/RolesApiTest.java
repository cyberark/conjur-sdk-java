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

package org.conjur.sdk.endpoint.roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.conjur.sdk.*;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.endpoint.*;
import org.conjur.sdk.endpoint.roles.RoleTest;
import org.conjur.sdk.model.*;
import org.junit.*;

/**
 * API tests for RolesApi.
 */
public class RolesApiTest extends RoleTest {
    /**
     * Update or modify an existing role membership. Test case for 204 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addMemberToRoleTest204() throws ApiException {
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
     * Update or modify an existing role membership. Test case for 401 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addMemberToRoleTest401() throws ApiException {
        String member = BOB_ID;
        try {
            badAuthApi.addMemberToRole(
                account,
                "group",
                "userGroup",
                "",
                member
            );
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Update or modify an existing role membership. Test case for 403 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addMemberToRoleTest403() throws ApiException {
        String member = BOB_ID;
        try {
            aliceApi.addMemberToRole(
                account,
                "group",
                "userGroup",
                "",
                member
            );
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Update or modify an existing role membership. Test case for 404 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addMemberToRoleTest404() throws ApiException {
        try {
            api.addMemberToRole(
                account,
                "group",
                "userGroup",
                "",
                "nonexist"
            );
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }

    /**
     * Deletes an existing role membership. Test case for 204 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeMemberFromRoleTest204() throws ApiException {
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
     * Deletes an existing role membership. Test case for 401 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeMemberFromRoleTest401() throws ApiException {
        String kind = "group";
        String identifier = "userGroup";
        String members = "";
        String member = BOB_ID;
        try {
            badAuthApi.removeMemberFromRole(
                account,
                kind,
                identifier,
                members,
                member
            );
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Deletes an existing role membership. Test case for 403 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeMemberFromRoleTest403() throws ApiException {
        String kind = "group";
        String identifier = "userGroup";
        String members = "";
        String member = BOB_ID;
        try {
            aliceApi.removeMemberFromRole(
                account,
                kind,
                identifier,
                members,
                member
            );
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Deletes an existing role membership. Test case for 404 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeMemberFromRoleTest404() throws ApiException {
        String kind = "group";
        String identifier = "userGroup";
        String members = "";
        String member = "nonexist";
        try {
            api.removeMemberFromRole(
                account,
                kind,
                identifier,
                members,
                member
            );
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }

    /**
     * Get role information. Test case for 200 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void showRoleTest200() throws ApiException {
        String kind = "user";
        String identifier = "admin";

        ApiResponse<Object> response = api.showRoleWithHttpInfo(
            account,
            kind,
            identifier
        );

        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Get role information. Test case for 401 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void showRoleTest401() throws ApiException {
        String kind = "user";
        String identifier = "admin";

        try {
            badAuthApi.showRole(
                account,
                kind,
                identifier
            );
        } catch (ApiException e) {
            Assert.assertEquals(401, e.getCode());
        }
    }

    /**
     * Get role information. Test case for 403 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void showRoleTest403() throws ApiException {
        String kind = "user";
        String identifier = "admin";

        try {
            aliceApi.showRole(
                account,
                kind,
                identifier
            );
        } catch (ApiException e) {
            Assert.assertEquals(403, e.getCode());
        }
    }

    /**
     * Get role information. Test case for 404 response code.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void showRoleTest404() throws ApiException {
        String kind = "user";
        String identifier = "nonexist";

        try {
            api.showRole(
                account,
                kind,
                identifier
            );
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }
    }
}
