package io.unipayment.client.example.dto;

import lombok.Data;

@Data
public abstract class BaseDto {
    private String clientId;
    private String clientSecret;
    private String apiHost;
}
