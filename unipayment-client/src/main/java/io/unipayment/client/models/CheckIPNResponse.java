package io.unipayment.client.models;

import lombok.Data;

@Data
public class CheckIPNResponse {
    private String code;
    private String msg;
}
