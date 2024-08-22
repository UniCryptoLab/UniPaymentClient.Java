package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.unipayment.sdk.model.enums.ConfirmSpeed;
import io.unipayment.sdk.model.enums.InvoiceErrorStatus;
import io.unipayment.sdk.model.enums.InvoiceStatus;
import io.unipayment.sdk.model.enums.PaymentMethodType;
import lombok.Data;

import java.util.Date;
import java.util.LinkedHashMap;

@Data
public class Invoice {

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("invoice_id")
    private String invoiceId;

    @JsonProperty("payment_method_type")
    private PaymentMethodType paymentMethodType;

    @JsonProperty("host_to_host_mode")
    private boolean hostToHostMode;

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

    @JsonProperty("refunded_price_amount")
    private Double refundedPriceAmount;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createTime;

    @JsonProperty("expiration_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date expirationTime;

    @JsonProperty("confirm_speed")
    private ConfirmSpeed confirmSpeed;

    @JsonProperty("status")
    private InvoiceStatus status;

    @JsonProperty("error_status")
    private InvoiceErrorStatus errorStatus;

    @JsonProperty("invoice_url")
    private String invoiceUrl;

    @JsonProperty("host_to_host_data")
    private LinkedHashMap<String, Object> hostToHostData;
}
