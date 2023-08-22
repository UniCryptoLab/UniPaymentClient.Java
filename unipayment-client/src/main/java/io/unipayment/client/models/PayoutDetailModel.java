package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.client.models.enums.PayoutStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PayoutDetailModel {
    @JsonProperty("payout_id")
    private String payoutId;
    @JsonProperty("network")
    private String network;
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("status")
    private PayoutStatus Status;
    @JsonProperty("create_time")
    private Date createTime;
    @JsonProperty("update_time")
    private Date updateTime;
    @JsonProperty("items")
    private List<PayoutItem> items;
}
