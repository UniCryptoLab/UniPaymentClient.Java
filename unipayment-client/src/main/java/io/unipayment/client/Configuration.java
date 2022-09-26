package io.unipayment.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Configuration {
    private String clientId;
    private String clientSecret;
    private String baseUrl;
    private Boolean debug;
    private String apiVersion="1.0";
}
