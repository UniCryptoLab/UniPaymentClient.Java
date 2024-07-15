package io.unipayment.sdk.model;

import java.util.TreeMap;

public class QueryPaymentRequest extends TreeMap<String, Object> {
    private int pageNo = 1;
    private int pageSize = 10;
    private Boolean isAsc;

    public QueryPaymentRequest() {
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

    public Boolean isAsc() {
        return isAsc;
    }

    public void setIsAsc(Boolean asc) {
        isAsc = asc;
        put("is_asc", isAsc);
    }
}
