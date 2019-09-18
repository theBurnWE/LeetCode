package com.shcepp.shdippsvr.business.util;

/**
 * 为handler提供一部分的辅助处理类
 *
 * @author BrunE
 * @date 2018-06-05 10:30
 **/
public class HttpRequestHelper {

    public static String getSttCode(String MERCHANTID) {
        String[] shceppMerchantId = PropertiesUtil.get("SHCEPP.MERCHANTID.LIST").split(",");
        String sttCode = null;
        //如果是平台制定的STTCODE则使用平台制定的STTCODE
        for (String merchantId : shceppMerchantId) {

            if (merchantId.equals(MERCHANTID)) {
                sttCode = MERCHANTID;
                break;
            }
        }
        //如果不是平台制定的STTCODE则使用平台默认的STTCODE：CPNR000
        if (null == sttCode) {
            sttCode = "CPNR000";
        }

        return sttCode;
    }

//    public static String getSignMethod(MsgHead head) {
//        String[] mdd5SignMethodList = PropertiesUtil.get("MD5.SIGN.METHOD.PLAT.LIST").split(",");
//        String signMethod = null;
//        //如果是平台制定的STTCODE则使用平台制定的STTCODE
//        for (String merchantId : mdd5SignMethodList) {
//
//            if (head.getMERCHANTID().equals(merchantId)) {
//                signMethod = "MD5";
//                break;
//            }
//        }
//        //如果不是平台制定的STTCODE则使用平台默认的STTCODE：CPNR000
//        if (null == signMethod) {
//            signMethod = "DEFAULT";
//        }
//
//        return signMethod;
//    }

}
