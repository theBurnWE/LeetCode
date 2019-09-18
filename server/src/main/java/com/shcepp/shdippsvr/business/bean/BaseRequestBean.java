package com.shcepp.shdippsvr.business.bean;

/**
 * 请求bean的基础类
 *
 * @author BrunE
 * @date 2018-03-30 15:10
 **/
public class BaseRequestBean extends BaseBean {

    private static final long serialVersionUID = 3928937346020182556L;
    private String serialNumber;
    private String commitTime;
    private String platCode;//本平台代码

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }
}
