package com.shcepp.shdippsvr.business.httputil.impl;

import com.shcepp.shdippsvr.business.bean.http.RequestParamBean;
import com.shcepp.shdippsvr.business.httputil.IHttpUtil;
import com.shcepp.shdippsvr.business.util.HttpUtils;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.MD5Util;
import com.shcepp.shdippsvr.sys.util.URLEncodingUtil;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * shcepp接口的发送类
 *
 * @author BrunE
 * @date 2019-04-11 22:40
 **/
public class SHCEPPFormatUtil implements IHttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(SHCEPPFormatUtil.class);

    //组织给PP的请求数据
    @Override
    public NameValuePair[] formatRequestData(RequestParamBean bean) {
        try {

            MD5Util md5 = new MD5Util();
            NameValuePair nameValuePair = new NameValuePair();
            List<NameValuePair> dataList = new ArrayList<NameValuePair>();
            String jsonString = URLEncodingUtil.encodeData(JsonUtil.beanToJson(bean.getMagicObject()));
            //EDATA
            nameValuePair.setName("eData");
            nameValuePair.setValue(jsonString);
            dataList.add(nameValuePair);
            //SIGN
            nameValuePair = new NameValuePair();
            String checkMD5Str = md5.getMD5ToUpperBy32bit(jsonString + bean.getKey(), "UTF-8");
            nameValuePair.setName("signMsg");
            nameValuePair.setValue(checkMD5Str);
            dataList.add(nameValuePair);
            //TRX_CODE
            nameValuePair = new NameValuePair();
            nameValuePair.setName("trxCode");
            nameValuePair.setValue(bean.getTrxCode());
            dataList.add(nameValuePair);
            //VERSION
            nameValuePair = new NameValuePair();
            nameValuePair.setName("version");
            nameValuePair.setValue(bean.getVersion());
            dataList.add(nameValuePair);
            //通知地址
            nameValuePair = new NameValuePair();
            nameValuePair.setName("notifyUrl");
            nameValuePair.setValue(bean.getNotifyUrl());
            dataList.add(nameValuePair);
            return dataList.toArray(new NameValuePair[dataList.size()]);
        } catch (Exception ex) {
            logger.error("error happen when do data parse ，error detail is ", ex);
            return null;
        }

    }

    @Override
    public String sendRequest(RequestParamBean bean) throws Exception {
        NameValuePair[] nameValuePairs = formatRequestData(bean);
        if (null == nameValuePairs) {
            logger.error("data parse error before send message ,please check your data");
        }
        //调用对应的发送方法将消息发送出去
        //并返回结果
        logger.info("target url is :{}", bean.getUrl());
        logger.debug("reqdata is :{}", JsonUtil.beanToJson(nameValuePairs));

        return HttpUtils.post(bean.getUrl(), nameValuePairs);
    }

    @Override
    public Object sendRequestFormated(RequestParamBean o) throws Exception {
        return null;
    }

}
