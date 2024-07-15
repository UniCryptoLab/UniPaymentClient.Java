package io.unipayment.sdk.example;

import io.unipayment.sdk.core.config.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniPaymentSdkExampleApp {

    @Value("${unipayment.api.clientId}")
    protected String clientId;

    @Value("${unipayment.api.clientSecret}")
    protected String clientSecret;

    @Value("${unipayment.api.host}")
    protected String apiHost;

    @Value("${unipayment.api.version}")
    protected String apiVersion;

    @Value("${unipayment.api.appId}")
    protected String appId;

    @Value("${unipayment.api.debug}")
    protected Boolean debug;

    @Bean
    public Configuration configuration() {
        return Configuration.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .host(apiHost)
                .apiVersion(apiVersion)
                .appId(appId)
                .debug(debug)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(UniPaymentSdkExampleApp.class, args);
    }
}
