package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BankPaymentMethodDetail extends PaymentMethodDetail {
    private String network;
    @JsonProperty("account_number")
    private String accountNumber;
    private String iban;
    private String bic;
    @JsonProperty("routing_number")
    private String routingNumber;
    @JsonProperty("bank_identifier")
    private String bankIdentifier;
    private String reference;
    @JsonProperty("bank_name")
    private String bankName;
    @JsonProperty("bank_address")
    private String bankAddress;
    @JsonProperty("bank_country")
    private String bankCountry;
    @JsonProperty("intermediary_bank_name")
    private String intermediaryBankName;
    @JsonProperty("intermediary_account_number")
    private String intermediaryAccountNumber;
    @JsonProperty("intermediary_bic")
    private String intermediaryBic;

}
