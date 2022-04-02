package io.unipayment.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Configuration {
    private String appId;
    private String apiKey;
    private String baseUrl;
    private Boolean debug;
    private String apiVersion;
}
