package io.unipayment.sdk.client.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.unipayment.sdk.core.Constants;

public final class OauthRequestInterceptor implements RequestInterceptor {

    private final String accessToken;

    public OauthRequestInterceptor(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(Constants.AUTHORIZATON, String.format("Bearer %s", accessToken));
    }
}
