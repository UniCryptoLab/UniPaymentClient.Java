package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.client.models.enums.ConfirmSpeed;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateInvoiceRequest {

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("price_amount")
    private Double priceAmount;

    @JsonProperty("price_currency")
    private String priceCurrency;

    @JsonProperty("pay_currency")
    private String payCurrency;

    @JsonProperty("network")
    private String payNetwork;

    @JsonProperty("notify_url")
    private String notifyURL;

    @JsonProperty("redirect_url")
    private String redirectURL;

    @JsonProperty("order_id")
    private String orderId;

    private String title;
    private String description;
    private String lang;

    @JsonProperty("ext_args")
    private String extArgs;

    @JsonProperty("confirm_speed")
    private ConfirmSpeed confirmSpeed;

}
