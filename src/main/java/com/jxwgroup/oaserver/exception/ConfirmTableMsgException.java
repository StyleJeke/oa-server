package com.jxwgroup.oaserver.exception;

import com.jxwgroup.oaserver.util.CommonUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ConfirmTableMsgException extends RuntimeException {

    private static final long serialVersionSID = 1L;

    private Object tableMsgData;

    private Object result;

    private String msg;

    private Boolean showButton;

    public ConfirmTableMsgException(String bindings, Object errList, String message, Object result, Boolean showButton){
        Map<String, Object> tableMsgData = new HashMap<>();
        tableMsgData.put("bindings", CommonUtil.createBindings(bindings));
        tableMsgData.put("data",errList);
        this.msg = message;
        this.tableMsgData = tableMsgData;
        this.showButton = (null != showButton && showButton);
        this.result = result;
    }

}
