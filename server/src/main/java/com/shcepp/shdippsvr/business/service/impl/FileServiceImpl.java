package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.pojo.FileUploadReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.FileUploadResPojo;
import com.shcepp.shdippsvr.business.dao.ShdippSysAttachmentDao;
import com.shcepp.shdippsvr.business.entity.ShdippSysAttachmentEntity;
import com.shcepp.shdippsvr.business.enums.FileType;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.business.util.FileUploadUtil;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.SnowflakeIdWorkerUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 基于平台纬度业务逻辑的实现
 *
 * @author BrunE
 * @date 2019-07-18 14:10
 **/

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private ShdippSysAttachmentDao shdippSysAttachmentDao;

    @Override
    public ApiResult uploadFile(MultipartFile file, FileUploadReqPojo pojo) {

        ApiResult apiResult = null;
        FileUploadResPojo resPojo = new FileUploadResPojo();
        ShdippSysAttachmentEntity entity;
        String fileRPath;
        StringBuilder fileSavePath = new StringBuilder();
        String fileType;
        entity = validateAttachment(file, pojo);
        boolean upLoadFlag;
        try {

            fileSavePath.append(FileUploadUtil.getFilePrePatten());
            fileSavePath.append(BaseService.URL_SPLIT_CHARACTER);
            fileSavePath.append(entity.getUrl());
            logger.info("文件的上传路径为：{}", fileSavePath);
            upLoadFlag = FileUploadUtil.uploadFile(file, entity, fileSavePath.toString());
            if (!upLoadFlag) {
                logger.error("文件上传失败，上传的实体为: {}", entity);
                apiResult = new ApiResult(FileService.FLAG_F, UPLOAD_FAIL_CODE, UPLOAD_FAIL);
                return apiResult;
            }
            entity = shdippSysAttachmentDao.save(entity);

            fileRPath = getFileUrl(pojo.getBaseUrl(), entity, null);
            if (FileType.FT_105.getCode().equals(entity.getBusinessType())) {
                fileType = FileService.VID_FILE_URL_PATTEN;

            } else {
                //如果是图片类型
                fileType = FileService.PIC_FILE_URL_PATTEN;
            }
            resPojo.setType(fileType);
            resPojo.setFileId(entity.getId());
            resPojo.setUrl(fileRPath);

            apiResult = new ApiResult(FileService.FLAG_T, FileService.BR_SUCCESS, UPLOAD_SUCCESS, resPojo);
        } catch (Exception e) {
            logger.error("上传失败，失败原因为：", e);
            apiResult = new ApiResult(FileService.FLAG_F, UPLOAD_FAIL_CODE, UPLOAD_FAIL);
        }
        logger.info("文件上传结果为：{}", JsonUtil.beanToJson(apiResult));

        return apiResult;
    }

    @Override
    public ShdippSysAttachmentEntity validateAttachment(MultipartFile file, FileUploadReqPojo reqPojo) {
        ShdippSysAttachmentEntity entity = new ShdippSysAttachmentEntity();

        entity.setId(SnowflakeIdWorkerUtil.nextIdStr());
        entity.setAttachmentType(reqPojo.getFileType());
        entity.setBusinessType(reqPojo.getBusinessType());
        entity.setFileName(file.getOriginalFilename());
        //获取新的用户名
        String switchFileName;
        String filename = file.getOriginalFilename();

        String fileSuffix = StringUtils.isNotEmptyWithNUllCheckStr(filename) ? filename.substring(filename.lastIndexOf(".") + 1) : "";

        switchFileName = FileUploadUtil.getFileName() + "." + fileSuffix;

        entity.setSwitchFileName(switchFileName);
        entity.setUrl(FileUploadUtil.getFilePath(reqPojo));
        entity.setIsDelete(ShdippSysAttachmentEntity.DELETE_ENABLE);

        return entity;
    }

    @Override
    public String getFileUrl(String baseUrl, ShdippSysAttachmentEntity entity, String fileType) {

        StringBuilder filePath = new StringBuilder();
        if (StringUtils.isNotEmptyWithNUllCheckStr(baseUrl)) {

            filePath.append(baseUrl);
        }
        filePath.append(FileService.URL_SPLIT_CHARACTER);

        filePath.append(FileService.FILE_RESOURCE_PATTEN);
        filePath.append(FileService.URL_SPLIT_CHARACTER);
        //根据文件类型获取上下文
        if (StringUtils.isNotEmptyWithNUllCheckStr(fileType)) {
            logger.info("传入文件类型为:{}", fileType);

            filePath.append(fileType);
        } else {
            logger.info("传入文件类型为:{}", fileType);

            filePath.append(FileUploadUtil.getFileUrlKeyWord(entity.getAttachmentType()));
        }
        filePath.append(FileService.FILE_URL_PATTEN);
        filePath.append(entity.getId());
        return filePath.toString();

    }

    @Override
    public String getFileUrl(String baseUrl, String id, String fileType) {
        ShdippSysAttachmentEntity entity;
        entity = shdippSysAttachmentDao.findById(id);
        if (null == entity) {
            logger.info("根据ID[{}]无法获取到对应的附件", id);
            return null;
        }
        StringBuilder filePath = new StringBuilder();
        if (StringUtils.isNotEmptyWithNUllCheckStr(baseUrl)) {

            filePath.append(baseUrl);
        }
        filePath.append(FileService.URL_SPLIT_CHARACTER);
        filePath.append(FileService.FILE_RESOURCE_PATTEN);
        filePath.append(FileService.URL_SPLIT_CHARACTER);
        //根据文件类型获取上下文
        if (StringUtils.isNotEmptyWithNUllCheckStr(fileType)) {
            logger.info("传入文件类型为:{}", fileType);
            filePath.append(fileType);
        } else {
            logger.info("传入文件类型为:{}", fileType);
            filePath.append(FileUploadUtil.getFileUrlKeyWord(entity.getAttachmentType()));
        }

        filePath.append(FileService.FILE_URL_PATTEN);
        filePath.append(id);
        logger.info("根据ID【{}】,获取到的文件路径为：{}", id, filePath);
        return filePath.toString();
    }
}
