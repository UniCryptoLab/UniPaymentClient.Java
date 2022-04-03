package io.unipayment.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceModel {

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("invoice_id")
    private String invoiceId;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("price_amount")
    private Double priceAmount;

    @JsonProperty("price_currency")
    private String priceCurrency;

    private String network;
    private String address;

    @JsonProperty("pay_amount")
    private Double payAmount;

    @JsonProperty("pay_currency")
    private String payCurrency;

    @JsonProperty("exchange_rate")
    private Double exchangeRate;

    @JsonProperty("paid_amount")
    private Double paidAmount;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createTime;

    @JsonProperty("expiration_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date expirationTime;

    @JsonProperty("confirm_speed")
    private String confirmSpeed;

    @JsonProperty("status")
    private String status;

    @JsonProperty("error_status")
    private String errorStatus;

    @JsonProperty("invoice_url")
    private String invoiceUrl;

}
