package com.rest.domains;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private HttpStatus errorCode;
    private String message;

    public ErrorMessage(HttpStatus errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
