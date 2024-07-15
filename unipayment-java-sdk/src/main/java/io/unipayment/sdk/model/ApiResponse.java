package io.unipayment.sdk.model;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String msg;
    private String code;
    private T data;
}
