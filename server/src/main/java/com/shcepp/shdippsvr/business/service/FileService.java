package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.FileUploadReqPojo;
import com.shcepp.shdippsvr.business.entity.ShdippSysAttachmentEntity;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传/读取的服务
 *
 * @author BrunE
 * @date 2019-07-18 14:06
 **/
public interface FileService extends BaseService {

    /* 交易成功*/
    static final String UPLOAD_SUCCESS = "上传成功";

    /* 交易成功*/
    static final String UPLOAD_FAIL_CODE = "911";

    /* 交易成功*/
    static final String UPLOAD_FAIL = "上传失败";
    /* 资源文件请求的上下文*/
    static final String FILE_RESOURCE_PATTEN = "resources";
    /* 资源文件请求的上下文*/
    static final String FILE_PATTEN = "attachment";

    /* 图片文件的上下文*/
    static final String PIC_FILE_URL_PATTEN = "pic";
    /* 视频文件的上下文*/
    static final String VID_FILE_URL_PATTEN = "vid";
    /* 缩略图的上下文*/
    static final String PIC_FILE_URL_PATTEN_T = "picT";
    /* 缩略图的路径上限文*/
    static final String PIC_FILE_PATTEN_T = "T";
    /* 原图的上下文*/
    static final String PIC_FILE_URL_PATTEN_O = "picO";

    /* 原图的上下文*/
    static final String FILE_URL_PATTEN = "_";

    /* 静态文件地址*/
    static final String STATIC_FILE_URL_PATTEN_V = "images";
    /* 静态文件地址*/
    static final String STATIC_FILE_URL_PATTEN_I = "videos";

    /**
     * 根据传入参数进行文件上传
     *
     * @param file     文件
     * @param fileType 文件类型
     * @return 上传结果
     */
    ApiResult uploadFile(MultipartFile file, FileUploadReqPojo fileType);

    /**
     * 根据上传的参数初始化附件对象
     *
     * @param file     文件
     * @param fileType 文件类型
     * @return 初始化之后的附件对象
     */
    ShdippSysAttachmentEntity validateAttachment(MultipartFile file, FileUploadReqPojo fileType);

    /**
     * 获取的浏览URL
     *
     * @param baseUrl 请求头
     * @param entity  文件实体
     * @return 上传结果
     */
    String getFileUrl(String baseUrl, ShdippSysAttachmentEntity entity, String fileType);

    /**
     * 获取的浏览URL
     *
     * @param baseUrl 请求头
     * @param id      文件ID
     * @return 上传结果
     */
    String getFileUrl(String baseUrl, String id, String fileType);

}
