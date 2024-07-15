package io.unipayment.sdk.core.config;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Configuration {
    private String host;
    private String apiVersion = "1.0";
    private String clientId;
    private String clientSecret;
    private String appId;
    private Boolean debug;
}