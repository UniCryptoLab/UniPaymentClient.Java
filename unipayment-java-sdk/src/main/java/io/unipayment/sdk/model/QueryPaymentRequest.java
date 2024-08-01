package io.unipayment.sdk.model;

import io.unipayment.sdk.model.enums.PaymentStatus;
import io.unipayment.sdk.model.enums.TransferMethod;
import lombok.Getter;

import java.util.TreeMap;

@Getter
public class QueryPaymentRequest extends TreeMap<String, Object> {
    private int pageNo = 1;
    private int pageSize = 10;
    private Boolean isAsc;
    private String fromAccountId;
    private String toAccountId;
    private TransferMethod transferMethod;
    private String paymentMethodId;
    private String cryptoAddress;
    private String bankAccount;
    private String assetType;
    private PaymentStatus status;

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

    public void setIsAsc(Boolean asc) {
        isAsc = asc;
        put("is_asc", isAsc);
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
        put("from_account_id", fromAccountId);
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
        put("to_account_id", toAccountId);
    }

    public void setTransferMethod(TransferMethod transferMethod) {
        this.transferMethod = transferMethod;
        put("transfer_method", transferMethod.name());
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
        put("payment_method_id", paymentMethodId);
    }

    public void setCryptoAddress(String cryptoAddress) {
        this.cryptoAddress = cryptoAddress;
        put("crypto_address", cryptoAddress);
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        put("bank_account", bankAccount);
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
        put("asset_type", assetType);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
        put("status", status.name());
    }
}
