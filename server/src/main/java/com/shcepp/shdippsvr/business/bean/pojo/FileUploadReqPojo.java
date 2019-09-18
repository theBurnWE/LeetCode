package com.shcepp.shdippsvr.business.bean.pojo;

/**
 * 文件上传的请求bean
 *
 * @author BrunE
 * @date 2019-07-18 18:38
 **/
public class FileUploadReqPojo extends BaseReqPojo {

    private static final long serialVersionUID = -5341750282032372646L;

    private static final String SHOW = "1";
    private static final String HIDDEN = "0";

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 本次查询的模块
     */
    private String businessType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
