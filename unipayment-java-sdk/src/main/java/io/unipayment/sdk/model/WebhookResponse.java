package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class WebhookResponse {
    private String notifyUrl;
    private String secretKey;
}
