package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentDestination {
    private String network;
    private String address;
    private String id;
    @JsonProperty("owner_id")
    private String ownerId;
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("friendly_name")
    private String friendlyName;
    @JsonProperty("beneficiary_name")
    private String beneficiaryName;
    @JsonProperty("beneficiary_address")
    private String beneficiaryAddress;
    @JsonProperty("beneficiary_city")
    private String beneficiaryCity;
    @JsonProperty("beneficiary_state")
    private String beneficiaryState;
    @JsonProperty("beneficiary_country")
    private String beneficiaryCountry;
    @JsonProperty("beneficiary_zipcode")
    private String beneficiaryZipcode;
    private String iban;
    @JsonProperty("routing_number")
    private String routing_number;
    private String bic;
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
