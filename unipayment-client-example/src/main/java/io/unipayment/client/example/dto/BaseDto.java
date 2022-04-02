package io.unipayment.client.example.dto;

import lombok.Data;

@Data
public abstract class BaseDto {
    private String appId;
    private String apiKey;
    private String apiHost;
}
