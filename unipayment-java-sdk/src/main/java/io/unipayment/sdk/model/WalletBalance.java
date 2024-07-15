package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WalletBalance {
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("balance")
    private Double balance;
    @JsonProperty("frozen_balance")
    private Double frozenBalance;
    @JsonProperty("reversed_balance")
    private Double reversedBalance;
    @JsonProperty("available")
    private Double available;
}
