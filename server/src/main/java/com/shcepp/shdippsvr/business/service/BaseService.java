package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.config.BaseConfig;

/**
 * 基础service用于
 *
 * @author BrunE
 * @date 2018-04-03 15:51
 **/
public interface BaseService extends BaseConfig {


    /* 交易成功*/
    static final String FLAG_T = "T";
    /* 交易成功*/
    static final String FLAG_F = "F";

    /* 交易成功*/
    static final String BR_SUCCESS = "000";
    /* 交易成功*/
    static final String BR_SUCCESS_MESSAGE = "处理成功";
    /* 到账编号重复*/
    static final String BR_COMM_ERROR = "901";
    /* 不允许的错误*/
    static final String BR_NOT_ALLOWED = "902";
    /* 到账编号重复*/
    static final String BR_SUSPEND = "903";
    /* 证书错误*/
    static final String BR_CA_ERROR = "904";

    /* 证书错误*/
    static final String BR_CA_ERROR_MESSAGE = "证书错误";
    /* 报文解析异常*/
    static final String BR_ANALYSIS_ERROE = "906";
    /* 报文解析异常*/
    static final String BR_ANALYSIS_ERROE_MESSAGE = "报文解析异常";
    /* 系统IO异常*/
    static final String BR_IO_ERROE = "907";
    /* 系统IO异常*/
    static final String BR_IO_ERROE_MESSAGE = "系统IO异常";
    /* 其他错误*/
    static final String BR_OTHER_ERROR = "909";
    /* 其他错误*/
    static final String BR_OTHER_ERROR_MESSAGE = "未知异常";

    /* url的分词字符*/
    static final String URL_SPLIT_CHARACTER = "/";

}
