package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class TransactionBeneficiary {
    private String beneficiaryCity;
    private String beneficiaryName;
    private String beneficiaryState;
    private String beneficiaryTitle;
    private String beneficiaryAddress;
    private String beneficiaryCountry;
    private String beneficiaryZipCode;
}
