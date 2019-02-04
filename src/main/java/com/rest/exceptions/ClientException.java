package com.rest.exceptions;

public class ClientException extends RuntimeException{
    public ClientException(long id) {
        super("Client Not Found with id :" + id);
    }
    public ClientException(String  code) {
        super("Client Not Found with code  :" + code);
    }
    public ClientException(long id, Exception e ) {
        super(e.getCause()+"Not Found Adress for client with id"+id);
    }

}
