package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class TransactionDetail {
    private String network;
    private String reference;
    private String senderIBAN;
    private String senderName;
    private String beneficiaryIBAN;
    private String beneficiaryName;
    private String remoteTransactionId;
    private String senderAccountNumber;
    private String beneficiaryAccountNumber;
}
