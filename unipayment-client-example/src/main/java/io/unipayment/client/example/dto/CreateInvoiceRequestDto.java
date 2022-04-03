package io.unipayment.client.example.dto;

import lombok.Data;

@Data
public class CreateInvoiceRequestDto extends BaseDto {
    private Double priceAmount;
    private String priceCurrency;
    private String payCurrency;
    private String notifyUrl;
    private String redirectUrl;
    private String orderId;
    private String title;
    private String description;
    private String lang;
    private String extArgs;
    private String confirmSpeed;
}
