package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.sdk.model.enums.AccountType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WalletAccount extends WalletBalance {
    private String id;
    private AccountType type;
    @JsonProperty("friendly_name")
    private String friendlyName;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    private String status;
}
