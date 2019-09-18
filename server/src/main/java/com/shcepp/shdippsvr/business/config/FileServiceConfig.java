package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件配置的缓存
 */
@Component
public class FileServiceConfig implements BaseConfig {

    //文件上传路径中的日期格式
    public static String filePathDataPatten;
    //文件上传路径的根路径
    public static String filePath;
    //系统代号
    public static String sysCode;

    //默认ID
    public static String defaultID;
    public   String getFilePathDataPatten() {
        return filePathDataPatten;
    }

    @Value("${shdippsvrcache.file.filePathDataPatten:YYYY-MM}")
    public   void setFilePathDataPatten(String filePathDataPatten) {
        FileServiceConfig.filePathDataPatten = filePathDataPatten;
    }

    @Value("${shdippsvrcache.file.patten:shdipp}")
    public void setSysCode(String sysCode) {
        FileServiceConfig.sysCode = sysCode;
    }

    @Value("${shdippsvrcache.file.filePath:/u01/upf/shdipp}")
    public   void setFilePath(String filePath) {
        FileServiceConfig.filePath = filePath;
    }

    @Value("${shdippsvrcache.file.defaultID:S123}")
    public   void setDefaultID(String defaultID) {
        FileServiceConfig.defaultID = defaultID;
    }
}
