package com.shcepp.shdippsvr.business.exception;

/**
 * Created by apple on 2017/9/10.
 */
public class ErrorException extends RuntimeException {
    private String serial;
    private String errorCode;
    private String errorMsg;
    public ErrorException(String serial, String errorCode, String errorMsg) {
        super(errorMsg);
        this.serial = serial;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorException( String errorCode, String errorMsg) {
        super(errorMsg);
        this.serial = serial;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getSerial() {
        return serial;
    }
    public void setSerial(String serial) {
        this.serial = serial;
    }
}
