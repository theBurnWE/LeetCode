package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 读取系统配置
 * @author: Burn~E
 * @date: 2019/08/19 11:06
 * @version: V1.0
 */
@Component
public class EsConfig implements BaseConfig {

    public static String SysCode;
    public static String DataPatten;
    public static String IndexPatten;

    @Value("${esnodes.indexconfig.indexPatten}")
    public  void setIndexPatten(String indexPatten) {
        IndexPatten = indexPatten;
    }

    @Value("${esnodes.indexconfig.sysCode}")
    public void setSysCode(String sysCode) {
        SysCode = sysCode;
    }

    @Value("${esnodes.indexconfig.dataPatten}")
    public void setDataPatten(String dataPatten) {
        DataPatten = dataPatten;
    }

}
