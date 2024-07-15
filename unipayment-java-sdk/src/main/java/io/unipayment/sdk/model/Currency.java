package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {
    private String code;
    private String name;
    @JsonProperty("is_fiat")
    private Boolean isFiat;
    private Integer divisibility;
}
