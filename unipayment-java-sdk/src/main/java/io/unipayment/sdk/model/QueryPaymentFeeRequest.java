package io.unipayment.sdk.model;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

@Getter
public class QueryPaymentFeeRequest extends TreeMap<String, Object> {
    private String assetType;

    public QueryPaymentFeeRequest() {
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
        if (StringUtils.isNotBlank(this.assetType)) {
            put("asset_type", this.assetType);
        }
    }
}
