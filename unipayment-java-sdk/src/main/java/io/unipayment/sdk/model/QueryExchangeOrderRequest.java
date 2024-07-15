package io.unipayment.sdk.model;

import lombok.Getter;

import java.util.TreeMap;

@Getter
public class QueryExchangeOrderRequest extends TreeMap<String, Object> {
    private String fromCurrency;
    private String toCurrency;
    private int pageNo = 1;
    private int pageSize = 10;
    private Boolean isAsc;

    public QueryExchangeOrderRequest() {
        put("page_no", pageNo);
        put("page_size", pageSize);
    }

    public void setFromCurrency(String fromCurrency) {
        put("from_currency", fromCurrency);
        this.fromCurrency = fromCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
        put("to_currency", toCurrency);
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        put("page_no", pageNo);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        put("page_size", pageSize);
    }

    public void setIsAsc(Boolean asc) {
        isAsc = asc;
        put("is_asc", isAsc);
    }
}
