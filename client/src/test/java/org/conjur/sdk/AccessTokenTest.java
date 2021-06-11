package org.conjur.sdk;

import static org.mockito.Mockito.*;

import java.util.*;
import org.conjur.sdk.*;
import org.junit.*;

/**
 * Test methods for the AccessToken class.
 */
public class AccessTokenTest {
    public static final String ENCODED_TOKEN = "eyJwcm90ZWN0ZWQiOiJleUpoYkdjaU9pSmpiMjVxZFhJdWIzS"
        + "m5MM05zYjNOcGJHOHZkaklpTENKcmFXUWlPaUkyTXpka05HWTFZMlU1WVdJd05ESTVOR0ZpWkRNNFptTmhPV00zW"
        + "W1Nek5qWTVaak16TWprNU5UUXdZamhsTm1ZeU5tRTBNVGM1T0RFeE1HSm1aRGcwSW4wPSIsInBheWxvYWQiOiJle"
        + "Up6ZFdJaU9pSmhaRzFwYmlJc0ltbGhkQ0k2TVRVNU9EYzJPVFUwTUgwPSIsInNpZ25hdHVyZSI6Ik5ya25FQTc2M"
        + "noweC1GVmRRakZHZVRUbkJzeXFBdlBHSWEyZUxZV3IyYVVGZDU5dHk0aGMxSlRsVGptdmpGNWNtVDNMUnFGbDhYY"
        + "zNwMDhabEhjbVc0cTdiVnFtM21odmZEdVNVaE13RzhKUk4yRFZQVHZKbkFiT1NPX0JGdWhKdmk2OGJEVGxZSFFmU"
        + "F81WHY1VWtuWHlLUDR2dGNoSjloMHJuVXN0T0F1YWlkM0RyQW5RV1c2dDRaMzRQajJhT2JrTkZ1TlMxNDBsamNwZ"
        + "1A1dHdfU19ISzB6d1dlSXF4cjh6eUpTbk5aNjJ1WlhZV25zU051WGZtSWdtVVo2cTJFeVZWWUJ1Zk5SZTNVUmFkU"
        + "09OYjRIcnFyX21UaGctWHUzMjA2N1h3QmNWZ3lWQ0JrcWtybktuRW1vRzlMRWs2ZjdNQVpDX1BXZnA4NXQ1VFFhV"
        + "m1iZFlqT2lDTW9GMFoxYkhyZGN2MC1LRnpNRGxHa0pCS1Jxb0xYYkFGakhjMCJ9";
    public static final String PROTECTED = "eyJhbGciOiJjb25qdXIub3JnL3Nsb3NpbG8vdjIiLCJraWQiOiI2M"
        + "zdkNGY1Y2U5YWIwNDI5NGFiZDM4ZmNhOWM3YmMzNjY5ZjMzMjk5NTQwYjhlNmYyNmE0MTc5ODExMGJmZDg0In0=";
    public static final String PAYLOAD = "eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5ODc2OTU0MH0=";
    public static final String SIGNATURE = "NrknEA762z0x-FVdQjFGeTTnBsyqAvPGIa2eLYWr2aUFd59ty4hc1"
        + "JTlTjmvjF5cmT3LRqFl8Xc3p08ZlHcmW4q7bVqm3mhvfDuSUhMwG8JRN2DVPTvJnAbOSO_BFuhJvi68bDTlYHQfP"
        + "_5Xv5UknXyKP4vtchJ9h0rnUstOAuaid3DrAnQWW6t4Z34Pj2aObkNFuNS140ljcpgP5tw_S_HK0zwWeIqxr8zyJ"
        + "SnNZ62uZXYWnsSNuXfmIgmUZ6q2EyVVYBufNRe3URadSONb4Hrqr_mThg-Xu32067XwBcVgyVCBkqkrnKnEmoG9L"
        + "Ek6f7MAZC_PWfp85t5TQaVmbdYjOiCMoF0Z1bHrdcv0-KFzMDlGkJBKRqoLXbAFjHc0";
    public AccessToken token;
    public AccessToken emptyToken;

    @Before
    public void setUpTests() {
        token = AccessToken.fromEncodedToken(ENCODED_TOKEN);
        emptyToken = new AccessToken();
    }

    @Test
    public void testBadlyEncodedToken() {
        String badlyEncodedToken = ENCODED_TOKEN.substring(2);
        boolean errorThrown = false;
        try {
            AccessToken badToken = AccessToken.fromEncodedToken(badlyEncodedToken);
        } catch (com.google.gson.JsonSyntaxException e) {
            errorThrown = true;
        }
        // Make sure an error was thrown/nothing got assigned to the token
        Assert.assertTrue(errorThrown);
    }

    @Test
    public void fromEncodedTokenTest() {
        // Test values for properly encoded token
        Assert.assertEquals(PROTECTED, token.getProtected());
        Assert.assertEquals(PAYLOAD, token.getPayload());
        Assert.assertEquals(SIGNATURE, token.getSignature());

        // Test proper values returned for empty token
        Assert.assertNull(emptyToken.getProtected());
        Assert.assertNull(emptyToken.getPayload());
        Assert.assertNull(emptyToken.getSignature());          
    }

    @Test
    public void getExpiryTest() {
        Long expires = token.getExpiry();

        // Fixed expiry from encoded token above
        Assert.assertEquals((long) 1598770020, (long) expires);

        expires = null;
        boolean errorThrown = false;
        // For empty token expiry should be null
        try {
            Assert.assertNull(emptyToken.getExpiry());
        } catch (NullPointerException e) {
            errorThrown = true;
        }
        Assert.assertTrue(errorThrown);
    }

    @Test
    public void needsRefreshTest() {
        // The hard coded token above is expired
        Assert.assertTrue(token.needsRefresh());

        // Mock a future-expiring token to get a "false" response
        AccessToken mockedToken = spy(AccessToken.fromEncodedToken(ENCODED_TOKEN));
        when(mockedToken.getExpiry()).thenReturn(((new Date()).getTime() / 1000) + 100);
        Assert.assertFalse(mockedToken.needsRefresh());
    }

    @Test
    public void getHeaderValueTest() {
        String headerValue = token.getHeaderValue();
        Assert.assertEquals(String.format("token=\"%s\"", ENCODED_TOKEN), headerValue);

        boolean errorThrown = false;
        try {
            emptyToken.getHeaderValue();
        } catch (NullPointerException e) {
            errorThrown = true;
        }

        Assert.assertTrue(errorThrown);
    }
}
