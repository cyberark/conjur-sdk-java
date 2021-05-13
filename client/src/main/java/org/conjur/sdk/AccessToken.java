
package org.conjur.sdk;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("protected")
    private String protect = null;
    private String payload = null;
    private String signature = null;
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Gson gson = new Gson();

    private String encodedToken;

    private class Payload {
        private final int TOKEN_LIFETIME_MINUTES = 8;
        private Long iat = null;
        private Long exp = null;
        private String sub = null;
        private String[] cidr = null;

        public long getExpiry() {
            if (exp != null)
                return exp;
            return iat + (TOKEN_LIFETIME_MINUTES * 60);
        }
    }

    public AccessToken() {}

    public static AccessToken fromEncodedToken(String encodedToken) {
        String decodedToken = new String(decoder.decode(encodedToken));
        AccessToken token = gson.fromJson(decodedToken, AccessToken.class);
        token.encodedToken = encodedToken;
        return token;
    }

    public String getHeaderValue() {
        return String.format("token=\"%s\"", encodedToken);
    }

    private long getExpiry() {
        String payloadJSON = new String(decoder.decode(payload));
        Payload payload = gson.fromJson(payloadJSON, Payload.class);
        return payload.getExpiry();
    }

    public boolean needsRefresh() {
        return payload == null || (getExpiry() - 10) <= ((new Date()).getTime() / 1000);
    }

    public String toString() {
        return String.format("{\n\tprotected: %s\n\tpayload: %s\n\tsignature: %s\n}", protect, payload, signature);
    }
}
