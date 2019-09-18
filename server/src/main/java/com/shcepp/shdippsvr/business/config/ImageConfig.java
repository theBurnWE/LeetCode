package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by gxd on 2019/9/2.
 */
@Component
public class ImageConfig {

    //压缩后的图片宽
    public static int new_w;
    //压缩后的图片高
    public static int new_h;
    //压缩后的百分百
    public static float per;

    //基础路径
    public static String baseUrl;

    public float getPer() {
        return per;
    }

    @Value("${shdippsvrcache.picconfig.per:100}")
    public void setPer(float per) {
        ImageConfig.per = per;
    }

    public int getNew_w() {
        return new_w;
    }

    @Value("${shdippsvrcache.picconfig.new_w:100}")
    public void setNew_w(int new_w) {
        ImageConfig.new_w = new_w;
    }

    public int getNew_h() {
        return new_h;
    }

    @Value("${shdippsvrcache.picconfig.new_h:100}")
    public void setNew_h(int new_h) {
        ImageConfig.new_h = new_h;
    }
    @Value("${shdippsvrcache.picconfig.baseurl:/shdipp-svr}")
    public void setBaseUrl(String baseUrl){
        ImageConfig.baseUrl = baseUrl;
    }

}
