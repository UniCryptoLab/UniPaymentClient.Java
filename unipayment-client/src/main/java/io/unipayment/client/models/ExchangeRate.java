package io.unipayment.client.models;

import lombok.Data;

@Data
public class ExchangeRate {
    private String from;
    private String to;
    private Double rate;
}
