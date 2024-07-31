package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepositBankAccount {
    private String network;
    @JsonProperty("account_number")
    private String accountNumber;
    private String iban;
    private String bic;
    @JsonProperty("routing_number")
    private String routingNumber;
    private Beneficiary beneficiary;
    private BankInfo bankInfo;
    private String reference;
}
