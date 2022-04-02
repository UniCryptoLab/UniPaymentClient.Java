package io.unipayment.client.models;

import lombok.Data;

import java.util.List;

@Data
public class TransactionList {
    private List<InvoiceTransactionModel> transactions;
}
