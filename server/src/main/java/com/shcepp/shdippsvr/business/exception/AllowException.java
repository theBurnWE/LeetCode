/** 
 * <p>Title: MessageException.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright (c) 2012</p> 
 * <p>Company: easipay</p> 
 * @author jjzhao 
 * @date 2013-5-2 
 * @version 1.0 
 */
package com.shcepp.shdippsvr.business.exception;

/**
 * MessageException
 */
public class AllowException extends Exception {

    private static final long serialVersionUID = -3396183419032228621L;
    /**
     * @author Burn~E
     */

    private String msgCode;

    private String msgInfo;

    public AllowException() {
        super();
    }

    public AllowException(String msg) {
        super(msg);
    }

    public AllowException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AllowException(Throwable cause) {
        super(cause);
    }

    public AllowException(String msgCode, String msgInfo) {
        this.msgCode = msgCode;
        this.msgInfo = msgInfo;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

}
