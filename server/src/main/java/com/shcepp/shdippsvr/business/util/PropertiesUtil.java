package com.shcepp.shdippsvr.business.util;

import com.fasterxml.jackson.module.afterburner.util.ClassName;
import com.shcepp.shdippsvr.business.config.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取config.properties配置
 * @author BrunE
 * @date  2019/4/15 20:26
 * @Description: 东方支付的代发明细
 */
@Component
public class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static String path;
    private static boolean loadFlag = true;
    private static Properties properties = new Properties();

    /**
     * 20190129
     * 由于配置出包的需求，现在
     * 修改其加载方式
     */
    private static void init() {

        logger.debug("-----config.properties path is {}-----", path);
        String osName = System.getProperty("os.name");
        //中间path
        String midpath;
        //如果是win系统，使用当前目录路径替换当前路径
        //如果是linux系统，使用配置的路径
        if(osName.toUpperCase().contains(BaseConfig.WIN_SYSTEM_NAME)){
            logger.debug("-----判断为win系统 {}-----", osName);

            midpath  = ClassName.class.getClassLoader().getResource("config.properties_dev").getPath();
            path =midpath;
        }else {
            logger.debug("-----判断为UNIX系统 {}-----", osName);
        }
        try {
            InputStream in = new FileInputStream(path);
            properties.load(in);
            loadFlag = false;
        } catch (IOException e) {

            logger.error(" error: ", e);
        }
    }

    public static String get(String key) {

        if (loadFlag) {
            init();
        }
        if (null != properties.getProperty(key)) {
            return properties.getProperty(key).trim();
        } else {
            return "";
        }
    }

    public String getPath() {
        return path;
    }

    @Value("${myAppSet.path}")
    public void setPath(String path) {
        PropertiesUtil.path = path;
    }

}
