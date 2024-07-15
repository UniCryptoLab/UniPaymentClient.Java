package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateWithdrawalRequest {
    @JsonProperty("network")
    private String network;
    @JsonProperty("address")
    private String address;
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("dest_tag")
    private String destTag;
    @JsonProperty("notify_url")
    private String notifyUrl;
    @JsonProperty("note")
    private String note;
    @JsonProperty("auto_confirm")
    private Boolean autoConfirm;
    @JsonProperty("include_fee")
    private Boolean includeFee;
}
