package io.unipayment.sdk.model;

import lombok.Setter;

import java.util.TreeMap;

@Setter
public class TokenRequest extends TreeMap<String, String> {
    private String grantType = "client_credentials";

    public TokenRequest(String clientId, String clientSecret) {
        put("grant_type", grantType);
        put("client_id", clientId);
        put("client_secret", clientSecret);
    }
}
