package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class TransactionTarget {
    private String bic;
    private BankDetail bank;
    private String iban;
    private String network;
    private String reference;
    private TransactionBeneficiary beneficiary;
    private String accountNumber;
    private String routingNumber;
    private String intermediaryBank;
}
