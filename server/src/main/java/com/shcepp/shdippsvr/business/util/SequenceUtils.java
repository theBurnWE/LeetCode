package com.shcepp.shdippsvr.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2018/4/11.
 */
public class SequenceUtils {

    /**
     * 到账编号，前缀 + 当前年月日时分秒 + 6位不重复的随机数
     */
    public static String getUniqueCode(String pre){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateString = sdf.format(new Date());
            String randomString = String.valueOf(Math.random()).substring(2).substring(0, 6);
            return pre + dateString + randomString;
        }catch (Exception e){
            System.out.println("生成交易流水号（企业账号申请、企业充值）出错=====" + e.toString());
        }
         return "";
    }
}
