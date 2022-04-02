package io.unipayment.client.example.dto;

import lombok.Data;

@Data
public class QueryInvoiceDto extends BaseDto {
    private String invoiceId;
    private String orderId;
    private String status;
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Boolean isAsc;
    private String start;
    private String end;
}
