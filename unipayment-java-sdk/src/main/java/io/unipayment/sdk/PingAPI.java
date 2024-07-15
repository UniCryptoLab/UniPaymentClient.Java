package io.unipayment.sdk;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.PingResponse;

@Headers({"Content-Type: application/json", "Accept: application/json", "Authorization: Bearer {accessToken}"})
public interface PingAPI {

    /**
     * Ping API
     */
    @RequestLine("GET /ping")
    PingResponse get(@Param("accessToken") String accessToken) throws UnipaymentSdkException;

    /**
     * Ping API
     */
    @RequestLine("POST /ping")
    PingResponse post(@Param("accessToken") String accessToken) throws UnipaymentSdkException;

    /**
     * Create Default Client
     *
     * @return Instance of Ping API
     */
    static PingAPI getInstance() {
        return new UnipaymentDefaultClient<PingAPI>().getDefaultClient(PingAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Ping API
     */
    static PingAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<PingAPI>().getClient(PingAPI.class, configuration);
    }
}
