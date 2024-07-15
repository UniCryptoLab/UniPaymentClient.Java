package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.unipayment.sdk.client.decoder.PaymentDetailJsonDeserializer;
import io.unipayment.sdk.model.enums.TransferMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {
    private String id;
    @JsonProperty("beneficiary_id")
    private String beneficiaryId;
    @NotBlank
    private String title;
    @JsonProperty("transfer_method")
    @NotNull
    private TransferMethod transferMethod;
    @JsonDeserialize(using = PaymentDetailJsonDeserializer.class)
    private PaymentMethodDetail detail;
}
