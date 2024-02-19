package com.jxwgroup.oaserver.exception;

/**
 * 处理业务异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionSID = 1L;

    public BusinessException(String error){
        super(error);
    }

}
