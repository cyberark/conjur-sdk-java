/*
 * Conjur
 * This is an API definition for CyberArk Conjur OSS. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.1.1
 * Contact: conj_maintainers@cyberark.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.conjur.sdk.endpoint;

import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.conjur.sdk.*;
import org.conjur.sdk.ApiException;
import org.conjur.sdk.endpoint.*;
import org.conjur.sdk.model.CertificateJson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * API tests for CertificateAuthorityApi.
 */
public class CertificateAuthorityApiTest extends ConfiguredTest {

    private CertificateAuthorityApi caApi;
    private CertificateAuthorityApi badAuthCaApi;
    private SecretsApi secretsApi;

    private final String account = System.getenv("ACCOUNT");

    // Conjur entity IDs
    private static final String CA_SERVICE_ID
        = "signing-service";
    private final String testClientId
        = String.format("%s:host:%s/test-client", account, CA_SERVICE_ID);
    private final String cnTestClientId
        = String.format("%s:host:%s/cn-test-client", account, CA_SERVICE_ID);
    private final String noSignClientId
        = String.format("%s:host:%s/no-sign-client", account, CA_SERVICE_ID);

    // CA config filepaths
    private static final Path CA_POLICY_PATH = Paths.get("/config/cert_auth_policy.yml");
    private static final Path CERT_CHAIN_PATH = Paths.get("/https/ca.crt");
    private static final Path UNENCRYPTED_KEY_PATH = Paths.get("/https/intermediate.key");
    private static final Path ENCRYPTED_KEY_PATH = Paths.get("/https/intermediate_encrypted.key");
    private static final Path KEY_PASSWORD_PATH = Paths.get("/https/intermediate_key_password.txt");
    private static final Path CSR_PATH = Paths.get("/https/test-java.csr");

    // CA config file strings
    private String generatedCsr;
    private String caPolicy;
    private String caChain;
    private String privateKey;
    private String keyPassword;
    private String encryptedKey;

    private String ttl = "P1D";


    /**
     * Set up each test case by reading files into strings, and creating and
     * configuring the Conjur CA.
     *
     * @throws ApiException
     *          if the Api call fails
     * @throws IOException
     *          if policy file is malformed or I/O error occurs
     */
    @Before
    public void setUpCa() throws ApiException, IOException {
        // read files
        generatedCsr = String.join(System.lineSeparator(), Files.readAllLines(CSR_PATH));
        caPolicy = String.join(System.lineSeparator(), Files.readAllLines(CA_POLICY_PATH));
        caChain = String.join(System.lineSeparator(), Files.readAllLines(CERT_CHAIN_PATH));
        privateKey = String.join(System.lineSeparator(), Files.readAllLines(UNENCRYPTED_KEY_PATH));
        keyPassword = String.join(System.lineSeparator(), Files.readAllLines(KEY_PASSWORD_PATH));
        encryptedKey = String.join(System.lineSeparator(), Files.readAllLines(ENCRYPTED_KEY_PATH));

        // load CA policy as root
        // creates a CA webservice, a group with sign privilege,
        // and host clients with varying privilege
        PoliciesApi policies = new PoliciesApi();
        policies.replacePolicy(account, "root", caPolicy);

        ApiClient hostClient = getApiClient("signing-service/test-client", "host");
        caApi = new CertificateAuthorityApi(hostClient);
        badAuthCaApi = new CertificateAuthorityApi(nonAuthClient);

        secretsApi = new SecretsApi();
        updateCaChain(caChain);
        updateCaPrivateKey(privateKey);
    }

    /**
     * Tear down tests by loading the default policy.
     * Removes the Conjur CA  so it can be reestablished for each test.
     *
     * @throws ApiException
     *          if an Api call fails
     * @throws IOException
     *          if policy file is malformed or I/O error occurs
     */
    @After
    public void tearDownCa() throws ApiException, IOException {
        loadDefaultPolicy();
    }

    /**
     * Update the CA certificate chain secret.
     *
     * @param caChain the new certificate chain to store
     * @throws ApiException
     *          if the Api call fails
     */
    private void updateCaChain(String caChain) throws ApiException {
        secretsApi.createSecret(
            account,
            "variable",
            String.format("conjur/%s/ca/cert-chain", CA_SERVICE_ID),
            null,
            null,
            caChain
        );
    }

    /**
     * Update the CA private key.
     *
     * @param key the new private key to store
     * @throws ApiException
     *          if the Api call fails
     */
    private void updateCaPrivateKey(String key) throws ApiException {
        secretsApi.createSecret(
            account,
            "variable",
            String.format("conjur/%s/ca/private-key", CA_SERVICE_ID),
            null,
            null,
            key
        );
    }

    /**
     * Update the CA private key password, used for encrypted private keys.
     *
     * @param password the new private key password
     * @throws ApiException
     *          if the Api call fails
     */
    private void updateCaKeyPassword(String password) throws ApiException {
        secretsApi.createSecret(
            account,
            "variable",
            String.format("conjur/%s/ca/private-key-password", CA_SERVICE_ID),
            null,
            null,
            password
        );
    }

    /**
     * Gets a signed certificate from the configured Certificate Authority service.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void signTestJson201() throws ApiException {
        ApiResponse<CertificateJson> response
            = caApi.signWithHttpInfo(account, CA_SERVICE_ID, generatedCsr, ttl);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.getData() instanceof CertificateJson);
    }

    /**
     * Gets a signed certificate preformatted in PEM.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Ignore("Java generated client does not support response polymorphism.")
    @Test
    public void signTestPem201() throws ApiException {
        String accept = "application/x-pem-file";
        String requestId = null;

        ApiResponse response
            = caApi.signWithHttpInfo(account, CA_SERVICE_ID, generatedCsr, ttl, accept, requestId);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertFalse(response.getData() instanceof CertificateJson);
    }

    /**
     * Gets a signed certificated from the Certificate Authority service
     * when it is configured with an encrypted private key and private key password.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void signWithEncryptedKey201() throws ApiException {
        updateCaPrivateKey(encryptedKey);
        updateCaKeyPassword(keyPassword);

        ApiResponse<CertificateJson> response
            = caApi.signWithHttpInfo(account, CA_SERVICE_ID, generatedCsr, ttl);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.getData() instanceof CertificateJson);
    }

    /**
     * Sign request returns 500 status when the CA is configured with the wrong
     * password for the encrypted private key.
     *
     * @throws ApiException
     *          if secret update Api call fails
     */
    @Test
    public void signWithEncryptedKey500() throws ApiException {
        updateCaPrivateKey(encryptedKey);
        updateCaKeyPassword("wrong_password");

        try {
            caApi.sign(account, CA_SERVICE_ID, generatedCsr, ttl);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 500);
        }
    }

    /**
     * Bad request for a signed certificate from Conjur CA.
     */
    @Test
    public void signTest400() {
        String serviceId = "\0";

        try {
            caApi.sign(account, serviceId, generatedCsr, ttl);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 400);
        }
    }

    /**
     * Unauthorized request for signed certificate from Conjur CA.
     */
    @Test
    public void signTest401() {
        try {
            badAuthCaApi.sign(account, CA_SERVICE_ID, generatedCsr, ttl);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 401);
        }
    }

    /**
     * Sign request responds with 403 status when the requesting role is not a Host.
     */
    @Test
    public void signTest403a() {
        CertificateAuthorityApi userCaApi = new CertificateAuthorityApi();

        try {
            userCaApi.sign(account, CA_SERVICE_ID, generatedCsr, ttl);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 403);
        }
    }

    /**
     * Sign request responds with 403 status when the requesting host does not
     * have 'sign' privilege on the Conjur CA service.
     *
     * @throws ApiException
     *          if client setup Api calls fails
     */
    @Test
    public void signTest403b() throws ApiException {
        ApiClient noSignClient = getApiClient("signing-service/no-sign-client", "host");
        CertificateAuthorityApi noSignApi = new CertificateAuthorityApi(noSignClient);

        try {
            noSignApi.sign(account, CA_SERVICE_ID, generatedCsr, ttl);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 403);
        }
    }

    /**
     * Sign request responds with 404 status when the CA service does not exist.
     */
    @Test
    public void signTest404() {
        String serviceId = "fakeService";

        try {
            caApi.sign(account, serviceId, generatedCsr, ttl);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 404);
        }
    }

    /**
     * Sign request responds with 422 status when receiving a bad request.
     */
    @Test
    public void signTest422() {
        String serviceId = "\0";

        ApiClient client = caApi.getApiClient();
        client.setBasePath(System.getenv("CONJUR_HTTP_APPLIANCE_URL"));

        try {
            caApi.sign(account, serviceId, generatedCsr, ttl);
        } catch (ApiException e) {
            Assert.assertEquals(e.getCode(), 422);
        }
    }
}
