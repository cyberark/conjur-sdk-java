package com.cyberark.conjur.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ApiClient} URL building behavior.
 */
public class ApiClientTest {

    private ApiClient apiClient;

    @Before
    public void setUp() {
        apiClient = new ApiClient();
    }

    /**
     * Test buildUrl with basePath ending with "/" and path starting with "/".
     */
    @Test
    public void shouldNotContainDoubleSlashesWhenBasePathHasTrailingSlashAndPathHasLeadingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com/");
        String path = "/authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL should not contain double slashes",
                "https://conjur.example.com/authn/myaccount/login", result);
        assertFalse("URL should not contain //authn", result.contains("//authn"));
    }

    /**
     * Test buildUrl with basePath without trailing slash and path starting with "/".
     */
    @Test
    public void shouldBuildCorrectUrlWhenBasePathHasNoTrailingSlashAndPathHasLeadingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com");
        String path = "/authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL should be correctly formed",
                "https://conjur.example.com/authn/myaccount/login", result);
    }

    /**
     * Test buildUrl with basePath ending with "/" and path NOT starting with "/".
     */
    @Test
    public void shouldPreserveTrailingSlashWhenBasePathHasTrailingSlashAndPathHasNoLeadingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com/");
        String path = "authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL should preserve trailing slash from basePath",
                "https://conjur.example.com/authn/myaccount/login", result);
    }

    /**
     * Test buildUrl with basePath containing port and trailing slash.
     */
    @Test
    public void shouldNotHaveDoubleSlashesWhenBasePathContainsPortAndTrailingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com:8443/");
        String path = "/authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL with port should not have double slashes",
                "https://conjur.example.com:8443/authn/myaccount/login", result);
        assertFalse("URL should not contain double slashes after port", result.contains(":8443//"));
    }

    /**
     * Test buildUrl with basePath containing subpath and trailing slash.
     */
    @Test
    public void shouldNotHaveDoubleSlashesWhenBasePathContainsSubpathAndTrailingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com/api/v1/");
        String path = "/authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL with subpath should not have double slashes",
                "https://conjur.example.com/api/v1/authn/myaccount/login", result);
        assertFalse("URL should not contain double slashes", result.contains("//authn"));
    }

    /**
     * Test buildUrl with basePath NOT ending with "/" and path NOT starting with "/".
     * This tests the new condition that adds a slash when both basePath and path
     * don't have slashes.
     */
    @Test
    public void shouldAddSlashWhenBasePathHasNoTrailingSlashAndPathHasNoLeadingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com");
        String path = "authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL should have slash added between basePath and path",
                "https://conjur.example.com/authn/myaccount/login", result);
    }

    /**
     * Test buildUrl with basePath containing subpath, no trailing slash,
     * and path with no leading slash.
     */
    @Test
    public void shouldAddSlashWhenBasePathNoTrailingSlashAndPathHasNoLeadingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com/api/v1");
        String path = "authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL should have slash added between subpath and path",
                "https://conjur.example.com/api/v1/authn/myaccount/login", result);
    }

    /**
     * Test buildUrl with basePath containing port, no trailing slash,
     * and path with no leading slash.
     */
    @Test
    public void shouldAddSlashWhenBasePathContainsPortNoTrailingSlashAndPathHasNoLeadingSlash() {
        // Given
        apiClient.setBasePath("https://conjur.example.com:8443");
        String path = "authn/myaccount/login";
        List<Pair> queryParams = new ArrayList<>();
        List<Pair> collectionQueryParams = new ArrayList<>();

        // When
        String result = apiClient.buildUrl(path, queryParams, collectionQueryParams);

        // Then
        assertEquals("URL should have slash added after port",
                "https://conjur.example.com:8443/authn/myaccount/login", result);
    }
}