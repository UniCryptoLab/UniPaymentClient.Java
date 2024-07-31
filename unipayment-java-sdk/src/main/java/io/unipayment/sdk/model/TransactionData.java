package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class TransactionData {
    private String id;
    private double fee;
    private String version;
    private double toAmount;
    private double amount;
    private String customerId;
    private double fromAmount;
    private String toCurrency;
    private double exchangeRate;
    private String fromCurrency;
    private String note;
    private TransactionDetail detail;
    private TransactionTarget target;
    private String network;
    private String assetType;
    private String createTime;
    private String transferMethod;
    private String type;
}
