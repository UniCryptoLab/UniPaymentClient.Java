package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyerInfo {
    private String name;
    private String email;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    @JsonProperty("zip_code")
    private String zipCode;
}
