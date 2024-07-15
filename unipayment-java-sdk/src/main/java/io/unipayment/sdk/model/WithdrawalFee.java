package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WithdrawalFee {
    @JsonProperty("asset_type")
    private String assetType;
    private String network;
    @JsonProperty("fee_type")
    private String feeType;
    private Double fee;
    @JsonProperty("min_txn_fee")
    private Double minTransactionFee;
}
