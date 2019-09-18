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
public class MessageException extends RuntimeException {

    /**
     * @author yangzhou
     */
    private static final long serialVersionUID = 1L;

    private String msgCode;

    private String msgInfo;

    public MessageException() {
        super();
    }

    public MessageException(String msg) {
        super(msg);
    }

    public MessageException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }

    public MessageException(String msgCode, String msgInfo) {
        super(msgInfo);
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
