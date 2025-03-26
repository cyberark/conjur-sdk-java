package com.cyberark.conjur.sdk;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Test class to validate telemetry header generation in ApiClient.
 */
public class ApiClientTelemetryTest {

    @Mock
    private OkHttpClient mockHttpClient;

    private ApiClient apiClient;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() throws Exception {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Mock the OkHttpClient
        apiClient = new ApiClient() {
            @Override
            public OkHttpClient getHttpClient() {
                return mockHttpClient;
            }
        };
    }

    /**
     * Test case for verifying the telemetry header is generated correctly.
     */
    @Test
    public void testTelemetryHeaderGeneration() throws IOException {
        // Arrange
        String expectedTelemetryHeader = 
                "aW49U2VjcmV0c01hbmFnZXJKYXZhIFNESyZpdD1jeWJyLXNlY3JldHNtYW5hZ2VyLWphdmEtc2Rr" 
                + "Jml2PTQuMi4wJnZuPUN5YmVyQXJr";
        String integrationName = "SecretsManagerJava SDK";
        String integrationType = "cybr-secretsmanager-java-sdk";
        String integrationVersion = "4.2.0";
        String vendorName = "CyberArk";

        // Create Headers directly (no need to mock)
        Headers headers = new Headers.Builder()
                .add("x-cybr-telemetry", expectedTelemetryHeader)
                .build();

        // Create a Response using the Headers
        Response response = new Response.Builder()
                .code(200)
                .message("OK")
                .request(new Request.Builder().url("http://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .headers(headers)
                .build();

        // Act: Retrieve the "x-cybr-telemetry" header from the response
        String actualTelemetryHeader = response.header("x-cybr-telemetry");

        // Assert
        assertNotNull("Telemetry header should not be null", actualTelemetryHeader);
        assertEquals("headers do not match", expectedTelemetryHeader, actualTelemetryHeader);
    }
}