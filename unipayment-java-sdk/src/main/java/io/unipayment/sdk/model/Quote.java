package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Quote {
    @JsonProperty("quote_id")
    private String quoteId;
    @JsonProperty("from_currency")
    private String fromCurrency;
    @JsonProperty("to_currency")
    private String toCurrency;
    @JsonProperty("requested_amount")
    private Double requestedAmount;
    @JsonProperty("exchange_rate")
    private Double exchangeRate;
    @JsonProperty("gross_amount")
    private Double grossAmount;
    @JsonProperty("net_amount")
    private Double netAmount;
    @JsonProperty("fee_currency")
    private String feeCurrency;
    private Double fee;
    @JsonProperty("valid_until")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date validUtil;
}
