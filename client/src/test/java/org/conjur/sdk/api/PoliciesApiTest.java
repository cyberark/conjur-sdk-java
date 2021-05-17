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

import org.conjur.sdk.*;
import org.conjur.sdk.endpoint.*;

import org.conjur.sdk.ApiException;
import org.conjur.sdk.model.LoadedPolicy;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * API tests for PoliciesApi
 */
public class PoliciesApiTest extends ConfiguredTest {

    private String NEW_VARIABLE = "policy/test";
    private String TEST_POLICY = String.format(
            "- !variable one/password\n- !variable testSecret\n- !variable %s",
            NEW_VARIABLE
        );

    private String MODIFY_POLICY = String.format(
            "- !delete\n    record: !variable %s",
            NEW_VARIABLE
        );

    private String UPDATE_POLICY = String.format(
            "- !variable %s",
            NEW_VARIABLE
        );

    private final PoliciesApi api = new PoliciesApi();

    /**
     * Adds data to the existing Conjur policy.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void loadPolicyTest() throws ApiException {
        ApiResponse<LoadedPolicy> response = api.loadPolicyWithHttpInfo(account, "root", TEST_POLICY);

        Assert.assertEquals(201, response.getStatusCode());
    }

    /**
     * Loads or replaces a Conjur policy document.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void replacePolicyTest() throws ApiException {
        ApiResponse<LoadedPolicy> response = api.replacePolicyWithHttpInfo(account, "root", MODIFY_POLICY);

        Assert.assertEquals(201, response.getStatusCode());
    }

    /**
     * Modifies an existing Conjur policy.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updatePolicyTest() throws ApiException {
        ApiResponse<LoadedPolicy> response = api.updatePolicyWithHttpInfo(account, "root", UPDATE_POLICY);

        Assert.assertEquals(201, response.getStatusCode());
    }
}
