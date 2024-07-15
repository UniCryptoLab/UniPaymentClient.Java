package io.unipayment.sdk.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentNote {
    private String note;
}
