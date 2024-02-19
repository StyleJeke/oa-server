package com.jxwgroup.oaserver.exception;

public class AuthorizationException extends RuntimeException{

    private static final long serialVersionSID = 1L;

    public AuthorizationException(String error){
        super(error);
    }

}
