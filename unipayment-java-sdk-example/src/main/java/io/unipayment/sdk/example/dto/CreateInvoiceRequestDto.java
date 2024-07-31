package io.unipayment.sdk.example.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateInvoiceRequestDto extends BaseDto {
    private String appId;
    private Double priceAmount;
    private String priceCurrency;
    private String payCurrency;
    private String payNetwork;
    private String notifyUrl;
    private String redirectUrl;
    private String orderId;
    private String title;
    private String description;
    private String lang;
    private String extArgs;
    private String confirmSpeed;
}
