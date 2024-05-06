package com.backend.Backend_supermarket.responses;

import lombok.Getter;

@Getter
public class ResponseData<T> {
    private int status;
    private String message;
    private T data;

    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
