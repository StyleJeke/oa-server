package com.jxwgroup.oaserver.config;

import com.jxwgroup.oaserver.exception.*;
import com.jxwgroup.oaserver.staticEntity.Constants;
import com.jxwgroup.oaserver.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常拦截
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常捕捉
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result<String> businessExceptionHandler(BusinessException e){
        log.error("自定义业务异常:",e);
        Result<String> result = new Result<>();
        result.setCode(Constants.BUSINESS_ERROR);
        result.setMsg(e.getMessage());
        return result;
    }

    /**
     * 登录校验异常捕捉
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result<String> authorizationExceptionHandler(AuthorizationException e){
        log.error("登录校验异常:",e);
        Result<String> result = new Result<>();
        result.setMsg(e.getMessage());
        result.setCode(Constants.NOT_AUTHORIZATION);
        return result;
    }

    /**
     * 待确认异常捕捉
     */
    @ExceptionHandler(value = ConfirmException.class)
    @ResponseBody
    public Result<String> confirmExceptionHandler(ConfirmException e){
        Result<String> r = new Result<>();
        r.setCode(Constants.CONFIRM_ERROR);
        r.setMsg(e.getMessage());
        return r;
    }


    @ExceptionHandler(value = ConfirmTableMsgException.class)
    @ResponseBody
    public Result<Object> confirmTableMsgExceptionHandler(ConfirmTableMsgException e){
        Result<Object> r = new Result<>();
        r.setCode(Constants.CONFIRM_TABLE_MSG_ERROR);
        r.setMsg(e.getMessage());
        r.setData(e.getResult());
        r.setTableData(e.getTableMsgData());
        return r;
    }

    /**
     * 自定义业务异常捕捉(返回错误表格形式数据)
     */
    @ExceptionHandler(value = BusinessTableMsgException.class)
    @ResponseBody
    public Result<Object> businessTableMsgExceptionHandler(BusinessTableMsgException e){
        log.error("表格形式自定义业务异常:",e);
        Result<Object> r = new Result<>();
        r.setCode(Constants.FAILED_TABLE_MSG_ERROR);
        r.setMsg(e.getMessage());
        r.setData(e.getTableMsgData());
        return r;
    }

    /**
     * 版本不一致异常捕捉
     */
    @ExceptionHandler(value = VersionException.class)
    @ResponseBody
    public Result<Object> versionExceptionHandler(VersionException e){
        log.error("版本不一致异常:",e);
        Result<Object> r = new Result<>();
        r.setCode(Constants.VERSION_ERROR);
        r.setMsg(e.getMessage());
        return r;
    }

    /**
     * 用户不存在异常捕捉
     */
    @ExceptionHandler(value = NotUserException.class)
    @ResponseBody
    public Result<Object> notUserExceptionHandler(NotUserException e){
        log.error("用户不存在异常:",e);
        Result<Object> r = new Result<>();
        r.setCode(Constants.NOT_USER_ERROR);
        r.setMsg(e.getMessage());
        return r;
    }

    /**
     * 账号密码错误异常捕捉
     */
    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Result<Object> loginExceptionHandler(LoginException e){
        log.error("账号密码错误:",e);
        Result<Object> r = new Result<>();
        r.setCode(Constants.PASSWORD_USERNAME_ERROR);
        r.setMsg(e.getMessage());
        return r;
    }

    /**
     * 意料之外的异常捕捉
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> exceptionHandler(Exception e){
        log.error("未知的异常:",e);
        Result<Object> r = new Result<>();
        r.setCode(Constants.UNKNOWN_ERROR);
        r.setMsg(e.getMessage());
        return r;
    }

}
