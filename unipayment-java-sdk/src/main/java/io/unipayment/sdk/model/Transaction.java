package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.sdk.model.enums.Direction;
import io.unipayment.sdk.model.enums.TransactionType;
import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    private String id;
    private double amount;
    private String assetType;
    private double balance;
    @JsonProperty("txn_type")
    private TransactionType txnType;
    private Direction direction;
    private String note;
    @JsonProperty("ref_id")
    private String referenceId;
    private TransactionData data;
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createTime;
}
