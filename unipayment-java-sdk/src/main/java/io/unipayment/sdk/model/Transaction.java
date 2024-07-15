package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Transaction {
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
