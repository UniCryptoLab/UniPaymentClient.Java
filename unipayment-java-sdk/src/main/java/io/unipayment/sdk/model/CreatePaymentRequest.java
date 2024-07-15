package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.sdk.model.enums.PaymentReason;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePaymentRequest {
    @JsonProperty("from_account_id")
    @NotEmpty
    private String fromAccountId;
    @JsonProperty("asset_type")
    @NotEmpty
    private String assetType;
    @NotNull
    private Double amount;
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    @JsonProperty("to_account_id")
    private String toAccountId;
    @NotNull
    private PaymentReason reason;
    private String reference;
    private String note;
    @JsonProperty("unique_id")
    private String uniqueId;
}
