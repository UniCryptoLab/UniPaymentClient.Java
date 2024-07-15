package io.unipayment.sdk.client.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.utils.TokenValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public final class OauthRequestInterceptor implements RequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Collection<String> values = requestTemplate.headers().get("Authorization");
        if (values == null || values.isEmpty()) {
            LOGGER.error("Missing authorization header");
            throw new UnipaymentSdkException("Missing authorization header");
        }
        String authValue = values.iterator().next();
        authValue = authValue.replace("Bearer ", "");
        if (!TokenValidationUtil.isValid(authValue)) {
            LOGGER.error("Invalid authorization header");
            throw new UnipaymentSdkException("Invalid authorization header");
        }
    }
}
