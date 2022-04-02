package io.unipayment.client;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import io.unipayment.client.auth.UniPaymentRequestInterceptor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * HTTP Client
 *
 * @param <T>
 */
public final class Client<T> {

    /**
     * Get Default HTTP Client
     *
     * @param type Type of API
     * @return
     */
    public T getDefaultClient(Class<T> type) {
        return null;
    }

    public T getClient(Class<T> type, Configuration configuration) {
        return getClient(type, configuration, null);
    }

    /**
     * Get CLient
     *
     * @param type          Type of API
     * @param configuration {@link Configuration}
     * @param client        {@link feign.Client}
     * @return
     */
    public T getClient(Class<T> type, Configuration configuration, feign.Client client) {
        Encoder encoder = new JacksonEncoder();
        Decoder decoder = new JacksonDecoder();
        ErrorDecoder errorDecoder = new ErrorDecoder.Default();
        return getClient(type, configuration, client, encoder, decoder, errorDecoder);
    }

    public T getClient(Class<T> type, Configuration configuration, feign.Client client, Encoder encoder, Decoder decoder, ErrorDecoder errorDecoder) {
        assert type != null : "type is required";
        assert configuration != null : "configuration is required";
        assert encoder != null : "encoder is required";
        assert decoder != null : "decoder is required";
        assert StringUtils.isNotBlank(configuration.getBaseUrl()) : "baseUrl is required";
        assert StringUtils.isNotBlank(configuration.getApiVersion()) : "api version is required";
        assert StringUtils.isNotBlank(configuration.getAppId()) : "app id is required";
        assert StringUtils.isNotBlank(configuration.getApiKey()) : "api key is required";

        Feign.Builder builder = Feign.builder().encoder(encoder).decoder(decoder).contract(new Contract.Default());
        if (client != null) {
            builder.client(client);
        }
        if (errorDecoder != null) {
            builder.errorDecoder(errorDecoder);
        }
        if (Boolean.TRUE.equals(configuration.getDebug())) {
            builder.logger(new Slf4jLogger(type)).logLevel(Logger.Level.FULL);
        }
        List<RequestInterceptor> requestInterceptors = new ArrayList<>();
        requestInterceptors.add(new UniPaymentRequestInterceptor(configuration.getBaseUrl(), configuration.getAppId(), configuration.getApiKey()));

        builder.requestInterceptors(requestInterceptors);
        return builder.target(type, configuration.getBaseUrl());
    }
}
