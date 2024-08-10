package io.unipayment.sdk;

import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import io.unipayment.sdk.client.auth.OauthRequestInterceptor;
import io.unipayment.sdk.client.encoder.JsonEncoder;
import io.unipayment.sdk.client.header.RequiredHeadersRequestInterceptor;
import io.unipayment.sdk.core.Constants;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.core.config.PropertyConfiguration;
import io.unipayment.sdk.model.TokenRequest;
import io.unipayment.sdk.model.TokenResponse;
import io.unipayment.sdk.utils.TokenCacheUtil;
import io.unipayment.sdk.utils.TokenValidationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create an http client to execute the API request
 *
 * @param <T>
 */
final class UnipaymentDefaultClient<T> {

    /**
     * Create a default Client
     *
     * @param type Type of API
     */
    public T getDefaultClient(Class<T> type) {
        Configuration configuration = new PropertyConfiguration();
        return getClient(type, configuration, new OkHttpClient());
    }

    /**
     * Create a Client
     *
     * @param type          Type of API
     * @param configuration Configuration to be used
     */
    public T getClient(Class<T> type, Configuration configuration) {
        return getClient(type, configuration, new OkHttpClient());
    }

    /**
     * Create a Client
     *
     * @param type          Type of API
     * @param configuration Configuration to be used
     * @param client        Type of http client
     */
    public T getClient(Class<T> type, Configuration configuration, Client client) {
        Encoder encoder = new JsonEncoder();
        Decoder decoder = new JacksonDecoder(Constants.getMapper());
        ErrorDecoder errorDecoder = new ErrorDecoder.Default();
        return getClient(type, encoder, decoder, errorDecoder, client, configuration, false, true);
    }

    /**
     * Create a Client
     *
     * @param type          Type of API
     * @param encoder       Custom {@link Encoder}
     * @param decoder       Custom {@link Decoder}
     * @param errorDecoder  {@link ErrorDecoder}
     * @param client        Type of http client {@link Client}
     * @param configuration Configuration to be used {@link Configuration}
     */
    T getClient(Class<T> type, Encoder encoder, Decoder decoder, ErrorDecoder errorDecoder, Client client, Configuration configuration, boolean withoutVersion, boolean authHeaderRequired) {
        Util.checkNotNull(type, "type");
        Util.checkNotNull(encoder, "encoder");
        Util.checkNotNull(decoder, "decoder");
        Util.checkNotNull(configuration, "configuration");
        Util.checkNotNull(configuration.getHost(), "host");
        Feign.Builder builder = Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder).contract(new Contract.Default());
        if (errorDecoder != null) {
            builder.errorDecoder(errorDecoder);
        }
        if (client != null) {
            builder.client(client);
        }
        if (Boolean.TRUE.equals(configuration.getDebug())) {
            builder.logger(new Slf4jLogger(type)).logLevel(feign.Logger.Level.FULL);
        }
        final List<RequestInterceptor> requestInterceptors = new ArrayList<>();
        requestInterceptors.add(new RequiredHeadersRequestInterceptor());
        if (authHeaderRequired) {
            String accessToken = TokenCacheUtil.getAccessToken();
            if (accessToken == null || !TokenValidationUtil.isValid(accessToken)) {
                TokenResponse tokenResponse = OauthTokenAPI.getInstance(configuration).getToken(new TokenRequest(configuration.getClientId(), configuration.getClientSecret()));
                accessToken = tokenResponse.getAccessToken();
                TokenCacheUtil.setAccessToken(tokenResponse.getAccessToken(), tokenResponse.getExpiresIn());
            }
            requestInterceptors.add(new OauthRequestInterceptor(accessToken));
        }
        builder.requestInterceptors(requestInterceptors);
        String host = configuration.getHost();
        if (!withoutVersion) {
            host = host + "/v" + configuration.getApiVersion();
        }
        return builder.target(type, host);
    }

}
