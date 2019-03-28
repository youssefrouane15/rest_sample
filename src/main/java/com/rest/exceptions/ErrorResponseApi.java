package com.rest.exceptions;


public class ErrorResponseApi {
    private int status;
    private String message;
    public ErrorResponseApi(int status, String message) {
        this.status = status;
        this.message = message;

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
