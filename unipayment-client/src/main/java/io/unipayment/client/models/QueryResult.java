package io.unipayment.client.models;

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
}
