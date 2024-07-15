package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public abstract class PaymentMethodDetail {
    private String id;
    @JsonProperty("asset_type")
    @NotBlank
    private String assetType;
}
