package com.shcepp.shdippsvr.business.bean.pojo;

/**
 * 文件上传结果的bean
 *
 * @author BrunE
 * @date 2019-07-18 18:38
 **/
public class FileUploadResPojo extends BasePojo {

    private static final long serialVersionUID = -5341750282032372646L;

    private static final String SHOW = "1";
    private static final String HIDDEN = "0";
    //生成好的文件ID
    private String fileId;
    //生成好的文件ID
    private String url;
    //文件类型，视频/图片
    private String type;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
