package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.sdk.model.enums.BeneficiaryType;
import io.unipayment.sdk.model.enums.Relationship;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotNull
    private BeneficiaryType type;
    @NotNull
    private Relationship relationship;
    private String address;
    private String city;
    private String state;
    private String country;
    @JsonProperty("zip_code")
    private String zipcode;
}
