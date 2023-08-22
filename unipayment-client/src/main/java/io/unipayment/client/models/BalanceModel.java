package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BalanceModel {
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("balance")
    private Double balance;
    @JsonProperty("frozen_balance")
    private Double frozenBalance;
    @JsonProperty("available")
    private Double available;
}
