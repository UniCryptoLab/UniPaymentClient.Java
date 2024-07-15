package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExchangeOrder extends Quote {
    private String id;
    private String status;
    @JsonProperty("exchange_amount")
    private Double exchangeAmount;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("create_time")
    private Date createTime;
}
