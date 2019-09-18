package com.shcepp.shdippsvr.business.util;

import com.shcepp.shdippsvr.business.bean.pojo.FileUploadReqPojo;
import com.shcepp.shdippsvr.business.config.BaseConfig;
import com.shcepp.shdippsvr.business.config.FileServiceConfig;
import com.shcepp.shdippsvr.business.entity.ShdippSysAttachmentEntity;
import com.shcepp.shdippsvr.business.enums.FileType;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.sys.util.DateUtil;
import com.shcepp.shdippsvr.sys.util.SnowflakeIdWorkerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.shcepp.shdippsvr.business.enums.FileType.FT_102;
import static com.shcepp.shdippsvr.business.enums.FileType.FT_202;

/**
 * 文件上传的工具类
 *
 * @author BrunE
 * @date 2019-08-29 14:23
 **/
@Component
public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    //尝试次数,最多尝试2次
    private static int retryTime = 0;

    /**
     * 获取文件上传的前道路径
     *
     * @return 前道路径
     */
    public static String getFilePrePatten() {
        StringBuilder path = new StringBuilder();
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains(BaseConfig.WIN_SYSTEM_NAME)) {
            //如果是win系统
            logger.debug("-----判断为win系统 {}-----", osName);

            path.append(System.getProperty("user.dir"));

        } else {
            //如果是linux系统的话是向指定的共享卷写入
            path.append(FileServiceConfig.filePath);
        }
        return path.toString();
    }

    /**
     * 获取本系统中的文件上传路径
     * 格式为：attachment+系统代码+业务类型+日期格式
     *
     * @param pojo 文件上传的对象
     * @return 对应文件的上传路径
     */
    public static String getFilePath(FileUploadReqPojo pojo) {
        StringBuilder path = new StringBuilder();
        path.append(FileService.FILE_PATTEN);

        path.append(BaseService.URL_SPLIT_CHARACTER);
        path.append(FileServiceConfig.sysCode);

        path.append(BaseService.URL_SPLIT_CHARACTER);
        path.append(pojo.getBusinessType());

        path.append(BaseService.URL_SPLIT_CHARACTER);
        path.append(DateUtil.dateToString(new Date(System.currentTimeMillis()), FileServiceConfig.filePathDataPatten));

        path.append(BaseService.URL_SPLIT_CHARACTER);

        return path.toString();
    }

    /**
     * 根据文件类型返回文件URL上下文关键词
     *
     * @param fileType 资源资源类型
     * @return URL读取关键字
     */
    public static String getFileUrlKeyWord(String fileType) {
        if (FileType.contains(fileType)) {
            if (FileType.FT_105.getCode().equals(fileType)) {
                // 是视频资源
                return FileService.VID_FILE_URL_PATTEN;
            } else {
                if (FT_102.getCode().equals(fileType) || FT_202.getCode().equals(fileType) || fileType.endsWith("2")) {
                    return FileService.PIC_FILE_URL_PATTEN_T;

                } else {
                    return FileService.PIC_FILE_URL_PATTEN_O;
                }
            }

        } else {
            logger.info("在对类型{}进行文件类型匹配的时候未正常匹配", fileType);
            return null;
        }
    }

    /**
     * 根据文件类型返回文件存储上下文关键词
     *
     * @param fileType 资源资源类型
     * @return 文件存储
     */
    public static String getFilePattenKeyWord(String fileType) {
        if (FileService.PIC_FILE_URL_PATTEN_O.equals(fileType)) {
            logger.debug("是原图类型", fileType);
            return "";

        } else if (FileService.PIC_FILE_URL_PATTEN_T.equals(fileType)) {
            logger.debug("是缩略图类型", fileType);

            return FileService.PIC_FILE_PATTEN_T + BaseService.URL_SPLIT_CHARACTER;
        } else if (FileService.VID_FILE_URL_PATTEN.equals(fileType)) {
            logger.debug("是视频类型", fileType);
            return "";
        } else {
            logger.error("对类型进行匹配的时候未匹配", fileType);
            return "";
        }

    }

    /**
     * 根据文件类型返回文件存储上下文关键词
     *
     * @param fileType 资源资源类型
     * @return 文件存储
     */
    public static String getFilePattenByUrlType(String fileType) {
        if (FileType.FT_105.getCode().equals(fileType)) {
            // 是视频资源
            return "";
        } else {
            if (FT_102.getCode().equals(fileType) || FT_202.getCode().equals(fileType) || fileType.endsWith("2")) {
                return FileService.PIC_FILE_PATTEN_T;

            } else {
                return "";
            }
        }

    }

    /**
     * 获取系统唯一的文件编号
     *
     * @return 返回系统唯一编号
     */
    public static String getFileName() {
        return SnowflakeIdWorkerUtil.nextIdStr();
    }

    /**
     * 创建路径
     * 尝试三次，三次尝试之后确定创建失败
     *
     * @param filePath 传入的文件保存路径
     * @return 创建结果
     */
    public static boolean createFileUploadPath(String filePath) {
        boolean res = false;
        for (int i = 0; i < 3; i++) {
            if (!res) {
                res = createPath(filePath);
                if (res) {
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 创建路径
     *
     * @param filePath 传入的文件保存路径
     * @return 创建结果
     */
    public static boolean createPath(String filePath) {

        File dir = new File(filePath);
        if (dir.exists()) {
            logger.info("路径{},已经存在，不再进行创建", filePath);
            return true;
        }
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        //创建多级目录
        if (dir.mkdirs()) {
            logger.info("创建目录{},成功！", filePath);
            return true;
        } else {
            logger.info("创建目录{},失败！", filePath);
            return false;
        }
    }

    /**
     * 上传文件
     *
     * @param file   文件流
     * @param entity 生成好的文件实体
     * @return 上次结果
     */
    public static boolean uploadFile(MultipartFile file, ShdippSysAttachmentEntity entity, String fileSavePath) {
        boolean uploadResult;
        //同时尝试创建原图片目录和缩略图目录
        String imageOrPath = fileSavePath;
        String imageTPath = fileSavePath + FileService.PIC_FILE_PATTEN_T + BaseService.URL_SPLIT_CHARACTER;

        if (!createFileUploadPath(imageOrPath)) {
            logger.error("在创建路径{}的时候出错，请检查", imageOrPath);
            return false;
        }

        if (!createFileUploadPath(imageTPath)) {
            logger.error("在创建路径{}的时候出错，请检查", imageTPath);
            return false;
        }
        File desc = new File(fileSavePath + entity.getSwitchFileName());
        try {
            file.transferTo(desc);//保存文件
//            this.wait(2000);
            if (!FileType.FT_105.getCode().equals(entity.getBusinessType())) {
                // 如果不是视频资源保存其缩略图
                ImageUtil.resize(imageOrPath + entity.getSwitchFileName(),
                                 imageTPath + entity.getSwitchFileName());
            }
            uploadResult = true;
        } catch (IOException e) {
            logger.error("在保存文件:{},原名称为:{}的时候发生异常，异常信息为：",
                         entity.getSwitchFileName(),
                         entity.getFileName(),
                         e);
            uploadResult = false;
        }
        return uploadResult;

    }

    /**
     * 保存缩略图
     *
     * @param file 文件流
     * @return 上次结果
     */
    public static boolean saveThumbnailFile(MultipartFile file, String fileSavePath, String fileName) {
        boolean uploadResult;

        fileSavePath = fileSavePath + FileService.PIC_FILE_PATTEN_T;

        File desc = new File(fileSavePath + fileName);
        try {
            file.transferTo(desc);//保存文件
            uploadResult = true;
        } catch (IOException e) {
            logger.error("在保存文件{}的时候发生异常，异常信息为：", fileName, e);
            uploadResult = false;
        }
        return uploadResult;

    }

}
