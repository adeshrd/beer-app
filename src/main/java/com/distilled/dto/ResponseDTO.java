package com.distilled.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private boolean success = true;
    private String errorMessage;
    private T data;

    public ResponseDTO<T> withData(T data) {
        this.success = true;
        this.data = data;
        return this;
    }

    public ResponseDTO<T> withError(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
        return this;
    }
}
