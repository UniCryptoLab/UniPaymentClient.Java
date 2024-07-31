package io.unipayment.sdk.model;

import io.unipayment.sdk.model.enums.TransactionType;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

@Getter
public class QueryWalletTransactionsRequest extends TreeMap<String, Object> {
    private String id;
    private TransactionType transactionType;
    private int pageNo = 1;
    private int pageSize = 20;
    private Boolean isAsc;

    public QueryWalletTransactionsRequest() {
        put("page_no", pageNo);
        put("page_size", pageSize);
    }

    public void setId(String id) {
        this.id = id;
        if (StringUtils.isNotBlank(this.id)) {
            put("id", this.id);
        }
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        if (transactionType != null) {
            put("txn_type", this.transactionType.name());
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
}
