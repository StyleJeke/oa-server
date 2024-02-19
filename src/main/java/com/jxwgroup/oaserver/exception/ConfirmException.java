package com.jxwgroup.oaserver.exception;

import lombok.Data;

@Data
public class ConfirmException extends RuntimeException {

    private String confirmKey;

    private String errorMsg;

    /**
     * 需要确认的异常信息
     * @param errorMsg 异常信息
     * @param confirmKey 需要确认的key值
     */
    public ConfirmException(String errorMsg, String confirmKey) {
        this.errorMsg = errorMsg;
        this.confirmKey = confirmKey;
    }

}
