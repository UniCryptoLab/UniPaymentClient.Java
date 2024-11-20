package io.unipayment.sdk.model;

import io.unipayment.sdk.model.enums.RefundStatus;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

@Getter
public class QueryInvoiceRefundsRequest extends TreeMap<String, Object> {
    private int pageNo = 1;
    private int pageSize = 10;
    private Boolean isAsc;
    private String invoiceId;
    private RefundStatus status;
    private String start;
    private String end;

    public QueryInvoiceRefundsRequest() {
        put("page_no", pageNo);
        put("page_size", pageSize);
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

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
        if (StringUtils.isNotBlank(this.invoiceId)) {
            put("invoice_id", this.invoiceId);
        }
    }

    public void setStatus(RefundStatus status) {
        this.status = status;
        if (status != null) {
            put("status", this.status.name());
        }
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
