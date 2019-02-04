package com.rest.controllers;

import com.rest.exceptions.ClientException;
import com.rest.exceptions.ErrorResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@RestControllerAdvice
public class ClientControllerAdvice  extends ResponseEntityExceptionHandler {
    //private static final Logger LOG = Logger.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseApi ClientNotFoundException(ClientException ex) {
        return new ErrorResponseApi(404,ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    public ErrorResponseApi ClientNonAuthoritativeException(ClientException ex) {
        return new ErrorResponseApi(203,ex.getMessage());
    }
    private ResponseEntity<ErrorResponseApi> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<ErrorResponseApi>(new ErrorResponseApi(httpStatus.value(), message), httpStatus);
    }

}
