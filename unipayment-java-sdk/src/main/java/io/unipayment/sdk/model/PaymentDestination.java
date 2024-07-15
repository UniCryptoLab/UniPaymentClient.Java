package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class PaymentDestination {
    private String network;
    private String address;
}
