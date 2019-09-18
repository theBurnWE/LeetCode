/**
 * Copyright : www.easipay.net , 2009-2010
 * Project : PEPP
 * $Id$
 * $Revision$
 * Last Changed by $Author$ at $Date$
 * $URL$
 * <p>
 * Change Log
 * Author      Change Date    Comments
 * -------------------------------------------------------------
 * your name     2010-3-4        Initailized
 */

package com.shcepp.shdippsvr.business.exception;

import com.shcepp.shdippsvr.business.bean.BaseResponseBean;
import com.shcepp.shdippsvr.business.bean.BaseResponseBean;

public class BizCheckException extends RuntimeException {

    private static final long serialVersionUID = -8328923232287151738L;
    private String errorCode;
    private Long bizId;

    public BizCheckException() {
        super();
    }

    public BizCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizCheckException(String message) {
        super(message);
    }

    public BizCheckException(BaseResponseBean sr, Throwable cause) {
        super(sr.getStatusInfo(), cause);
        this.setErrorCode(sr.getStatusCode());
    }

    public BizCheckException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.setErrorCode(errorCode);
    }

    public BizCheckException(BaseResponseBean sr) {
        super(sr.getStatusInfo());
        this.setErrorCode(sr.getStatusCode());
    }

    public BizCheckException(String errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public BizCheckException(Throwable cause) {
        super(cause);
    }

    public BizCheckException(Long bizId) {
        super();
        this.setBizId(bizId);
    }

    public BizCheckException(String message, Throwable cause, Long bizId) {
        super(message, cause);
        this.setBizId(bizId);
    }

    public BizCheckException(String message, Long bizId) {
        super(message);
        this.setBizId(bizId);
    }

    public BizCheckException(BaseResponseBean sr, Throwable cause, Long bizId) {
        super(sr.getStatusInfo(), cause);
        this.setErrorCode(sr.getStatusCode());
        this.setBizId(bizId);
    }

    public BizCheckException(String errorCode,
                             String message,
                             Throwable cause,
                             Long bizId) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setBizId(bizId);
    }

    public BizCheckException(BaseResponseBean sr, Long bizId) {
        super(sr.getStatusInfo());
        this.setErrorCode(sr.getStatusCode());
        this.setBizId(bizId);
    }

    public BizCheckException(String errorCode, String message, Long bizId) {
        super(message);
        this.setErrorCode(errorCode);
        this.setBizId(bizId);
    }

    public BizCheckException(Throwable cause, Long bizId) {
        super(cause);
        this.setBizId(bizId);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

}
