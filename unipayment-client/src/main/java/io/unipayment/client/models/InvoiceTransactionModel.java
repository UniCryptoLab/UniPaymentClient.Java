package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InvoiceTransactionModel {
    private String hash;
    private String network;
    private String symbol;
    private String from;
    private String to;
    private Double amount;
    @JsonProperty("confirmation_count")
    private int confirmationCount;
    @JsonProperty("is_confirmed")
    private Boolean isConfirmed;
}
