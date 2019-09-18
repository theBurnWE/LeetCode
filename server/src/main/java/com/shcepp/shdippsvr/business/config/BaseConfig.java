package com.shcepp.shdippsvr.business.config;

/**
 * 缓存的基础类
 *
 * @author BrunE
 * @date 2018-04-12 14:26
 **/
public interface BaseConfig {

    //开关值
    public static final String SWITCH_ON = "ON";
    public static final String SWITCH_OFF = "OFF";
    //发送标志
    public static final String SEND_SWITCH_ON = "00";
    public static final String SEND_SWITCH_OFF = "01";

    public static final String BR_SUCCESS = "000000";// 交易成功
    public static final String BR_COMM_ERROR = "999991";
    public static final String BR_NOT_ALLOWED = "999992";// 不允许的错误
    public static final String BR_SUSPEND = "999993";//
    public static final String BR_CA_ERROR = "999994";// 证书错误
    public static final String BR_OTHER_ERROR = "999999";// 其他错误

    public static final String USER_NAME = "upfrmrec";
    public static final String PASSWORD = "efep1234";
    public static final String ENCRYPTION = "";
    public   static  final String WIN_SYSTEM_NAME = "WINDOWS";
}
