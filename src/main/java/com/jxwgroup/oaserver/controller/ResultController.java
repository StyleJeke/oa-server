package com.jxwgroup.oaserver.controller;

import com.jxwgroup.oaserver.staticEntity.Constants;
import com.jxwgroup.oaserver.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ResultController {

    @Autowired
    HttpServletRequest request;

    /**
     * 统一返回结果
     * @param msg 需要返回的提示语
     * @param data 需要返回的数据
     * @return 返回结果
     * @param <T> 自定义类型
     */
    public <T> Result<T> setSuccess(String msg, T data){
        Result<T> result = new Result<>();
        result.setCode(Constants.SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
