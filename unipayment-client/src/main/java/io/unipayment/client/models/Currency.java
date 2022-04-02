package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {
    private String code;
    private String symbol;
    private String name;
    @JsonProperty("is_fiat")
    private Boolean isFiat;
}
