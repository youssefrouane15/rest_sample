package com.rest.controllers;

import com.rest.exceptions.NoClientFoundException;
import com.rest.exceptions.ErrorResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ClientControllerAdvice  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoClientFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseApi clientNotFoundException(NoClientFoundException ex) {
        return new ErrorResponseApi(404,ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    public ErrorResponseApi clientNonAuthoritativeException(NoClientFoundException ex) {
        return new ErrorResponseApi(203,ex.getMessage());
    }

}
