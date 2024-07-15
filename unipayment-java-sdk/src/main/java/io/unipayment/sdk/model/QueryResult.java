package io.unipayment.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QueryResult<T> {
    private List<T> models;
    @JsonProperty("page_no")
    private Integer pageNo;
    private Integer total;
    @JsonProperty("page_count")
    private Integer pageCount;
    @JsonProperty("page_size")
    private Integer pageSize;
}
