package com.jxwgroup.oaserver.staticEntity;

/**
 * 异常状态码
 */
public class Constants {

    /**
     * 成功
     */
    public static final Integer SUCCESS = 100;

    /**
     * 登录失效
     */
    public static final Integer NOT_AUTHORIZATION = 10050;
    /**
     * 常见错误
     */
    public static final Integer BUSINESS_ERROR = 10000;

    /**
     * 未知异常
     */
    public static final Integer UNKNOWN_ERROR = 9999;

    /**
     * 需要确认的异常
     */
    public static final Integer CONFIRM_ERROR = 10100;

    /**
     * 需要确认的多个异常
     */
    public static final Integer CONFIRM_TABLE_MSG_ERROR = 10150;

    /**
     * 返回错误表格形式数据
     */
    public static final Integer FAILED_TABLE_MSG_ERROR = 10200;

    /**
     * 版本不一致
     */
    public static final Integer VERSION_ERROR = 9998;

    /**
     * 用户不存在
     */
    public static final Integer NOT_USER_ERROR = 9997;

    /**
     * 账号或密码错误
     */
    public static final Integer PASSWORD_USERNAME_ERROR = 9996;
}
