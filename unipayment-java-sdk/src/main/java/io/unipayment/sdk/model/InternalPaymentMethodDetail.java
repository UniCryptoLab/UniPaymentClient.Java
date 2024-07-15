package io.unipayment.sdk.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InternalPaymentMethodDetail extends PaymentMethodDetail {
    @NotBlank
    private String uid;
}
