package io.unipayment.sdk.model;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

@Getter
public class QueryInvoiceRequest extends TreeMap<String, Object> {
    private String appId;
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
    }

    public void setAppId(String appId) {
        this.appId = appId;
        if (StringUtils.isNotBlank(this.appId)) {
            put("app_id", this.appId);
        }
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
        if (StringUtils.isNotBlank(this.invoiceId)) {
            put("invoice_id", this.invoiceId);
        }
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        if (StringUtils.isNotBlank(this.orderId)) {
            put("order_id", this.orderId);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        if (StringUtils.isNotBlank(this.status)) {
            put("status", this.status);
        }
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        put("page_no", pageNo);
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

    public void setStart(String start) {
        this.start = start;
        if (StringUtils.isNotBlank(this.start)) {
            put("start", this.start);
        }
    }

    public void setEnd(String end) {
        this.end = end;
        if (StringUtils.isNotBlank(this.end)) {
            put("end", this.end);
        }
    }
}
