package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentFee {
    @JsonProperty("asset_type")
    private String assetType;
    private String network;
    @JsonProperty("fee_type")
    private String feeType;
    @JsonProperty("fee_rate")
    private Double feeRate;
    @JsonProperty("flat_rate")
    private Double flatRate;
    @JsonProperty("min_txn_fee")
    private Double minTransactionFee;
    @JsonProperty("max_txn_fee")
    private Double maxTransactionFee;
}
