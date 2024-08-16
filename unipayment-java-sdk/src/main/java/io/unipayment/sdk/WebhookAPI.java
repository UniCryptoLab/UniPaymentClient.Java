package io.unipayment.sdk;

import feign.Headers;
import feign.RequestLine;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.ApiResponse;
import io.unipayment.sdk.model.UpdateNotifyURLRequest;
import io.unipayment.sdk.model.UpdateSecretKeyRequest;
import io.unipayment.sdk.model.WebhookResponse;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface WebhookAPI {

    /**
     * Update Notify URL
     */
    @RequestLine("POST /webhook/notify-url")
    ApiResponse<WebhookResponse> updateNotifyUrl(UpdateNotifyURLRequest updateNotifyURLRequest) throws UnipaymentSdkException;

    /**
     * Update Secret Key
     */
    @RequestLine("POST /webhook/secret-key")
    ApiResponse<WebhookResponse> updateSecretKey(UpdateSecretKeyRequest updateSecretKeyRequest) throws UnipaymentSdkException;

    /**
     * Create Default Client
     *
     * @return Instance of Webhook API
     */
    static WebhookAPI getInstance() {
        return new UnipaymentDefaultClient<WebhookAPI>().getDefaultClient(WebhookAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Webhook API
     */
    static WebhookAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<WebhookAPI>().getClient(WebhookAPI.class, configuration);
    }
}
