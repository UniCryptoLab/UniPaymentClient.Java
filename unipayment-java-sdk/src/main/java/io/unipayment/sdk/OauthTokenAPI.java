package io.unipayment.sdk;

import feign.Headers;
import feign.RequestLine;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import io.unipayment.sdk.client.encoder.FormEncoder;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.TokenRequest;
import io.unipayment.sdk.model.TokenResponse;

@Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
public interface OauthTokenAPI {

    /**
     * Get Token
     */
    @RequestLine("POST /connect/token")
    TokenResponse getToken(TokenRequest tokenRequest) throws UnipaymentSdkException;

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Oauth Token API
     */
    static OauthTokenAPI getInstance(Configuration configuration) {
        Encoder encoder = new FormEncoder();
        Decoder decoder = new JacksonDecoder();
        ErrorDecoder errorDecoder = new ErrorDecoder.Default();
        return new UnipaymentDefaultClient<OauthTokenAPI>().getClient(OauthTokenAPI.class, encoder, decoder,
                errorDecoder, new OkHttpClient(), configuration, true, false);
    }
}
