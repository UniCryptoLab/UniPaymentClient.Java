package io.unipayment.sdk.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvoiceDetail extends Invoice {
    private List<Transaction> transactions;

}
