package io.unipayment.client.models;

import io.unipayment.client.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

public class QueryWithdrawalRequest extends TreeMap<String, Object> {
    private String network;
    private String assetType;
    private String status;
    private int pageNo = 1;
    private int pageSize = 10;
    private Boolean isAsc;
    private String start;
    private String end;

    public QueryWithdrawalRequest() {
        put("page_no", pageNo);
        put("page_size", pageSize);
        put("rd", Utils.getCurrentUtcTime());
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
        if (StringUtils.isNotBlank(this.network)) {
            put("network", this.network);
        }
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
        if (StringUtils.isNotBlank(this.assetType)) {
            put("asset_type", this.assetType);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if (StringUtils.isNotBlank(this.status)) {
            put("status", this.status);
        }
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
        if (StringUtils.isNotBlank(this.start)) {
            put("start", this.start);
        }
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
        if (StringUtils.isNotBlank(this.end)) {
            put("end", this.end);
        }
    }
}
