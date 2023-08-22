package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayoutItem {
    @JsonProperty("address")
    private String address;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("hash")
    private String Hash;
}
