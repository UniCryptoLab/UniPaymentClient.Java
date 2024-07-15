package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class ExchangeRate {
    private String from;
    private String to;
    private Double rate;
    private Double ask;
    private Double bid;
}
