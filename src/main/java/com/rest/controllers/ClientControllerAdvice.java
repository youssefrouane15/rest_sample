package com.rest.controllers;

import com.rest.exception.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientControllerAdvice  {
    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String ClientNotFoundException(ClientException ex) {
        return ex.getMessage();
    }

}
