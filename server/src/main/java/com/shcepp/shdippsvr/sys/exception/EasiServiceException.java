package com.shcepp.shdippsvr.sys.exception;

/**
 * Created by Merjiezo on 2017/7/10.
 */
public class EasiServiceException extends RuntimeException {

    /**
     * <p>
     * Field code: 异常代码
     * </p>
     */
    private String status;

    private String message;

    public EasiServiceException() {
    }

    /**
     * 默认构造函数
     *
     * @param code    异常代码
     * @param message 异常信息
     */
    public EasiServiceException(String code, String message) {
        super(message);
        this.status = code;
    }

    public EasiServiceException(String code) {
        this.status = code;
    }

    public EasiServiceException(String code, String message, Throwable throwble) {
        super(message, throwble);
        this.status = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String code) {
        this.status = code;
    }

    public String getMessage() {
        return null == message ? super.getMessage() : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
