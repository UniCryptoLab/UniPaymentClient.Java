package io.unipayment.sdk;

import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.core.config.PropertyConfiguration;
import io.unipayment.sdk.model.TokenRequest;
import io.unipayment.sdk.model.TokenResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseAPITest {

    protected static final Configuration configuration = new PropertyConfiguration();
    private static String accessToken;

    protected static String getAccessToken() {
        if (accessToken == null) {
            OauthTokenAPI oauthTokenAPI = OauthTokenAPI.getInstance(configuration);
            TokenResponse tokenResponse = oauthTokenAPI.getToken(new TokenRequest(configuration.getClientId(), configuration.getClientSecret()));
            accessToken = tokenResponse.getAccessToken();
        }
        return accessToken;
    }
}
