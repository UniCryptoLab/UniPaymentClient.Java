package io.unipayment.sdk.model;

import lombok.Getter;

import java.util.TreeMap;

@Getter
public class QuoteRequest extends TreeMap<String, Object> {
    private String fromCurrency;
    private String toCurrency;
    private Double exchangeAmount;

    public void setFromCurrency(String fromCurrency) {
        put("from_currency", fromCurrency);
        this.fromCurrency = fromCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
        put("to_currency", toCurrency);
    }

    public void setExchangeAmount(Double exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
        put("exchange_amount", exchangeAmount);
    }
}
