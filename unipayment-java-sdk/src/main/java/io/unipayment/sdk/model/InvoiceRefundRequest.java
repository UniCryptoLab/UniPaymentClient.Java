package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.sdk.model.enums.FeePayer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceRefundRequest {
    @JsonProperty("refund_price_amount")
    private Double refundPriceAmount;
    @JsonProperty("price_currency")
    private String priceCurrency;
    @JsonProperty("fee_payer")
    private FeePayer feePayer;
    @JsonProperty("reason")
    private String reason;
}
