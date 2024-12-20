
package com.cyberark.conjur.sdk;

import java.util.*;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.nio.charset.StandardCharsets;

public class AccessToken {
	
    private static final Logger LOGGER = Logger.getLogger(AccessToken.class.getName());


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
    
    // Constructor to initialize with a decoded token received from cloud
    public AccessToken(byte[] rawTokenBytes) {
        if (rawTokenBytes == null) {
        throw new IllegalArgumentException("rawTokenBytes cannot be null");
    }
        this.encodedToken = new String(rawTokenBytes, StandardCharsets.UTF_8);
        // If rawToken is Base64-encoded, decode it
        if (rawTokenBytes != null && isBase64(rawTokenBytes)) {
            // Decode the Base64 byte array
            byte[] decodedBytes = decoder.decode(rawTokenBytes);
            //the decoded byte array is being converted to a String and passed to Gson for parsing into a JSON object. 
            AccessToken token = gson.fromJson(new String(decodedBytes, StandardCharsets.UTF_8), AccessToken.class);
            this.protect = token.protect;
            this.payload = token.payload;
            this.signature = token.signature;
            
            // Clear the decoded byte array to ensure it is not lingering in memory
            Arrays.fill(decodedBytes, (byte) 0);
        } else {
            // If the rawTokenBytes are already in JSON format, parse it directly
            AccessToken token = gson.fromJson(new String(rawTokenBytes, StandardCharsets.UTF_8), AccessToken.class);
            this.protect = token.protect;
            this.payload = token.payload;
            this.signature = token.signature;
        }

        // Clear the original raw token byte array after processing
        Arrays.fill(rawTokenBytes, (byte) 0);
    }
    
    // Utility method to check if a byte array is Base64-encoded
    private static boolean isBase64(byte[] bytes) {
        try {
            //Trying to decode the byte array. If it's Base64
            decoder.decode(bytes);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static AccessToken fromEncodedToken(String encodedToken) {
        String decodedToken = new String(decoder.decode(encodedToken));
        AccessToken token = gson.fromJson(decodedToken, AccessToken.class);
        token.encodedToken = encodedToken;
        return token;
    }

    public String getHeaderValue() {
        if (encodedToken == null) {
            throw new NullPointerException("AccessToken must be initialized properly before use.");
        }
        return String.format("token=\"%s\"", encodedToken);
    }

    public long getExpiry() {
        String payloadJSON = new String(decoder.decode(getPayload()));
        Payload payload = gson.fromJson(payloadJSON, Payload.class);
        return payload.getExpiry();
    }

    public boolean needsRefresh() {
        return getPayload() == null || (getExpiry() - 10) <= ((new Date()).getTime() / 1000);
    }

    public String toString() {
        return String.format("{\n\tprotected: %s\n\tpayload: %s\n\tsignature: %s\n}", protect, payload, signature);
    }

    public String getProtected() {
        return this.protect;
    }

    public String getPayload() {
        return this.payload;
    }

    public String getSignature() {
        return this.signature;
    }
}