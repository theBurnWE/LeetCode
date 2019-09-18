package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by gxd on 2019/9/2.
 */
@Component
public class PlatformConfig {
    //平台详细数据数量
    public static long platfromSize;

    @Value("${shdippsvrcache.platfromSize:1}")
    public  void setPlatfromSize(long platfromSize) {
        PlatformConfig.platfromSize = platfromSize;
    }
}
