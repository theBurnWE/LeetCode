package com.shcepp.shdippsvr.sys.exception;

/**
 * 无oauth，让用户重新登录
 * Created by Merjiezo on 2017/5/31.
 */
public class NoOauthException extends Exception {
    /**
     * <p>
     * Field code: 异常代码
     * </p>
     */
    private Integer status;

    private String message;

    public NoOauthException() {
    }

    /**
     * 默认构造函数
     *
     * @param code    异常代码
     * @param message 异常信息
     */
    public NoOauthException(Integer code, String message) {
        super(message);
        this.status = code;
    }

    public NoOauthException(String message) {
        super(message);
        this.message = message;
    }

    public NoOauthException(Integer code, String message, Throwable throwble) {
        super(message, throwble);
        this.status = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = code;
    }

    public String getMessage() {
        return null == message ? super.getMessage() : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
