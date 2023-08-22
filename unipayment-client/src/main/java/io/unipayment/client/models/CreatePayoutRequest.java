package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePayoutRequest {
    @JsonProperty("network")
    private String network;
    @JsonProperty("asset_type")
    private String assetType;
    private List<PayoutRequestItem> items;
}
