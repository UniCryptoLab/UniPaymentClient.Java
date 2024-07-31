package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Payment {
    private String id;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("transfer_method")
    private String transferMethod;
    private String network;
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("from_account_id")
    private String fromAccountId;
    private PaymentDestination destination;
    private Double amount;
    private Double fee;
    @JsonProperty("total_amount")
    private Double totalAmount;
    private String reference;
    private String reason;
    private String note;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("create_time")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("update_time")
    private Date updateTime;
}
