package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BankAccount {
    @JsonProperty("bank_name")
    private String bankName;
    @JsonProperty("bank_account")
    private String bankAccount;
    @JsonProperty("bank_bic")
    private String bankBic;
}
