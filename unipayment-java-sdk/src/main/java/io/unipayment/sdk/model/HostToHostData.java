package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HostToHostData {
    private String network;
    private String address;
    @JsonProperty("pay_amount")
    private Double payAmount;
    @JsonProperty("pay_currency")
    private String payCurrency;
    private String type;
}
