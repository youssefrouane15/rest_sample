package com.rest.controllers;

import com.rest.exceptions.ClientException;
import com.rest.exceptions.ErrorResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestControllerAdvice
public class ClientControllerAdvice  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseApi ClientNotFoundException(ClientException ex) {
        return new ErrorResponseApi(404,ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    public ErrorResponseApi ClientNonAuthoritativeException(ClientException ex) {
        return new ErrorResponseApi(203,ex.getMessage());
    }

}
