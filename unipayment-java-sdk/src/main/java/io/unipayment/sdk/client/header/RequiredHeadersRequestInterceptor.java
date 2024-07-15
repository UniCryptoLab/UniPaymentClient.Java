package io.unipayment.sdk.client.header;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.unipayment.sdk.core.Constants;

import java.util.UUID;

public final class RequiredHeadersRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(Constants.USER_AGENT, Constants.getUserAgent());
        requestTemplate.header(Constants.X_REQUEST_ID, UUID.randomUUID().toString());
    }
}
