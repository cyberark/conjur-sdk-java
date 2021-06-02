/*
 * A helper class for the two roles endpoint tests that does some policy setup
 */

package org.conjur.sdk.endpoint.roles;

import org.conjur.sdk.*;
import org.conjur.sdk.endpoint.*;
import org.conjur.sdk.model.*;
import org.junit.*;

/**
 * A helper class for the two roles endpoint tests that does some policy setup.
 */
public class RoleTest extends ConfiguredTest {
    protected RolesApi badAuthApi;
    protected RolesApi api;
    protected RolesApi aliceApi;

    protected static final String ROLES_POLICY = String.join("\n",
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

    protected static final String BOB_ID = String.format(
        "%s:user:bob", System.getenv("CONJUR_ACCOUNT"));

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
     * Set up the api clients for use in the tests.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Before
    public void setUpApis() throws ApiException {
        badAuthApi = new RolesApi(nonAuthClient);
        api = new RolesApi();

        ApiClient aliceClient = getApiClient("alice");
        aliceApi = new RolesApi(aliceClient);
    }

    /**
     * Adds a specified user to the group used in this subset of tests.
     */
    protected void addUserToGroup(String identifier) throws ApiException {
        String roleId = String.format("%s:user:%s", account, identifier);
        api.addMemberToRole(
                account,
                "group",
                "userGroup",
                "",
                roleId);
    }
}
