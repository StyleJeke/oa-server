package com.jxwgroup.oaserver.exception;

public class LoginException extends RuntimeException {
    private static final long serialVersionSID = 1L;

    public LoginException(String message){
        super(message);
    }

}
