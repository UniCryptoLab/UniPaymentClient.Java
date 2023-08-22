package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.client.models.enums.WithdrawStatus;
import lombok.Data;

import java.util.Date;

@Data
public class WithdrawalModel {
    @JsonProperty("id")
    private String id;
    @JsonProperty("network")
    private String network;
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("address")
    private String address;
    @JsonProperty("fee")
    private Double fee;
    @JsonProperty("status")
    private WithdrawStatus status;
    @JsonProperty("txn_hash")
    private String txnHash;
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createTime;
}
