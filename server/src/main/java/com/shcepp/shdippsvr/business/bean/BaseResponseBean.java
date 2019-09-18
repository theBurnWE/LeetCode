package com.shcepp.shdippsvr.business.bean;

/**
 * 返回bean的基础类
 *
 * @author BrunE
 * @date 2018-03-30 15:11
 **/
public class BaseResponseBean implements java.io.Serializable {

    private static final long serialVersionUID = -4556644132766043771L;
    private String statusCode;//状态码
    private String statusInfo;//状态消息
    private String serialNumber;//原交易流水号

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "BaseResponseBean{" +
                "statusCode='" + statusCode + '\'' +
                ", statusInfo='" + statusInfo + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
