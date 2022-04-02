package io.unipayment.client.models;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDetailModel {
    private List<InvoiceTransactionModel> transactions;
}
