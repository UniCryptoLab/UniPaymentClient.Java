package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.sdk.model.enums.RefundStatus;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceRefund {
    @JsonProperty("refund_id")
    private String refundId;
    @JsonProperty("invoice_id")
    private String invoiceId;
    @JsonProperty("price_currency")
    private String priceCurrency;
    @JsonProperty("refund_price_amount")
    private Double refundPriceAmount;
    @JsonProperty("fee_payer")
    private String feePayer;
    @JsonProperty("fee")
    private float fee;
    @JsonProperty("status")
    private RefundStatus status;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("create_time")
    private Date createTime;
}
