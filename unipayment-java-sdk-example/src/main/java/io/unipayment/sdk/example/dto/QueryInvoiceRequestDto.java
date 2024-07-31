package io.unipayment.sdk.example.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryInvoiceRequestDto extends BaseDto {
    private String invoiceId;
    private String orderId;
    private String status;
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Boolean isAsc;
    private String start;
    private String end;
}
