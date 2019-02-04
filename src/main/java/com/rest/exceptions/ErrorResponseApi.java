package com.rest.exceptions;

import java.time.LocalDateTime;

public class ErrorResponseApi {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String debugMessage;
    public ErrorResponseApi(int status, String message) {
        this();
        this.status = status;
        this.message = message;

    }

    public ErrorResponseApi() {
        timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(status)
                .append(message)
                .toString();
    }
}
