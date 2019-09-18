package com.shcepp.shdippsvr.business.bean.pojo;

import com.shcepp.shdippsvr.business.bean.BaseBean;

/**
 * 基础的交互类
 *
 * @author BrunE
 * @date 2019-08-19 15:18
 **/
public class BaseReqPojo extends BaseBean {

    private static final long serialVersionUID = 6266590663993647982L;

    /**
     * 站点编号
     */
    private String dominCode;

    /**
     * 语言编码
     */
    private String lanCode;

    /**
     * 请求头
     */
    private String baseUrl;

    public String getDominCode() {
        return dominCode;
    }

    public void setDominCode(String dominCode) {
        this.dominCode = dominCode;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
