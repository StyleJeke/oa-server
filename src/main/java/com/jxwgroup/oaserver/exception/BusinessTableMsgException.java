package com.jxwgroup.oaserver.exception;

import com.jxwgroup.oaserver.util.CommonUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BusinessTableMsgException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Object tableMsgData;

    private String msg;

    public BusinessTableMsgException(String bindings, Object errList, String message){
        Map<String,Object> tableMsgData = new HashMap<>();
        tableMsgData.put("bindings", CommonUtil.createBindings(bindings));
        tableMsgData.put("data",errList);
        this.msg = message;
        this.tableMsgData = tableMsgData;
    }

}
