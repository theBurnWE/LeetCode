package com.shcepp.shdippsvr.business.util;

/**
 * 结汇申请的工具类
 *
 * @author BrunE
 * @date 2018-07-04 16:20
 **/
public class BatchApplyUtil {


    //判断是否是聚集结汇模式的站点
    public static boolean isGatherModeStt(String sttCode) {

        //获取聚集结汇模式的站点
        String[] settlementDeliveryModeSttCode = PropertiesUtil.get("GATHERING.MODE.STTCODE.LIST").split(",");
        //默认为假
        boolean returnFlag = false;
        for (String code : settlementDeliveryModeSttCode) {
            if (code.equals(sttCode)) {
                returnFlag = true;
                //判断完成，结束整个循环
                break;
            }
        }
        return returnFlag;
    }
}
