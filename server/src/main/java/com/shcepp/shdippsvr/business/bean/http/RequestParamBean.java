package com.shcepp.shdippsvr.business.bean.http;

import java.io.Serializable;

/**
 * pingpong的请求参数bean
 *
 * @author BrunE
 * @date 2018-06-08 13:47
 **/
public class RequestParamBean implements Serializable {

    private static final long serialVersionUID = 784449271481098982L;
    //平台号
    private String platCode;
    //第三方平台代码
    private String sttCode;
    //交易代号
    private String trxCode;
    //版本号
    private String version;
    //pp加签KEY值
    private String key;
    //汇付加签地址
    private String certKeyUrl;
    //通知地址
    private String notifyUrl;
    //url
    private String url;
    //备注1
    private String spt1;
    //备注1
    private String spt2;
    //被组装的bean
    private Object MagicObject;

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }

    public String getSttCode() {
        return sttCode;
    }

    public void setSttCode(String sttCode) {
        this.sttCode = sttCode;
    }

    public String getTrxCode() {
        return trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCertKeyUrl() {
        return certKeyUrl;
    }

    public void setCertKeyUrl(String certKeyUrl) {
        this.certKeyUrl = certKeyUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpt1() {
        return spt1;
    }

    public void setSpt1(String spt1) {
        this.spt1 = spt1;
    }

    public String getSpt2() {
        return spt2;
    }

    public void setSpt2(String spt2) {
        this.spt2 = spt2;
    }

    public Object getMagicObject() {
        return MagicObject;
    }

    public void setMagicObject(Object magicObject) {
        MagicObject = magicObject;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
