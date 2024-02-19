package com.jxwgroup.oaserver.exception;


public class NotUserException extends RuntimeException{

    private static final long serialVersionSID = 1L;

    public NotUserException(String error){
        super(error);
    }
}
