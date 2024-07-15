package io.unipayment.sdk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CryptoPaymentMethodDetail extends PaymentMethodDetail {
    private String network;
    private String address;
}
