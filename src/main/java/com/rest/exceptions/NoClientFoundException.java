package com.rest.exceptions;

public class NoClientFoundException extends RuntimeException{
    public NoClientFoundException(long id) {
        super("Client Not Found with id :" + id);
    }
    public NoClientFoundException(String  code) {
        super("Client Not Found with code  :" + code);
    }
    public NoClientFoundException(long id, Exception e ) {
        super(e.getCause()+"Not Found Adress for client with id"+id);
    }

}
