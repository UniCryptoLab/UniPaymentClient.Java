package io.unipayment.client.models;

import io.unipayment.client.Utils;

import java.util.TreeMap;

public class QueryInvoiceRequest extends TreeMap<String, Object> {
    private String invoiceId;
    private String orderId;
    private String status;
    private int pageNo = 1;
    private int pageSize = 10;
    private Boolean isAsc;
    private String start;
    private String end;

    public QueryInvoiceRequest() {
        put("page_no", pageNo);
        put("page_size", pageSize);
        put("rd", Utils.getCurrentUtcTime());
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
        put("invoice_id", invoiceId);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        put("order_id", orderId);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        put("status", status);
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        put("page_no", pageNo);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        put("page_size", pageSize);
    }

    public Boolean isAsc() {
        return isAsc;
    }

    public void setIsAsc(Boolean asc) {
        isAsc = asc;
        put("is_asc", isAsc);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
        put("start", start);
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
        put("end", end);
    }
}
