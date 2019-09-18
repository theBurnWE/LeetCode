package com.shcepp.shdippsvr.sys.controller;

import com.shcepp.shdippsvr.annotation.EntityClass;
import com.shcepp.shdippsvr.business.exception.AllowException;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.util.PropertiesUtil;
import com.shcepp.shdippsvr.sys.service.RedisUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    public RedisUtil redisUtil;


    protected Class entityClass;

    public BaseController() {
        EntityClass entityClazz = getClass().getAnnotation(EntityClass.class);
        if (entityClazz != null) {
            this.entityClass = entityClazz.value();
        } else {
            logger.debug("Annotation EntityClass is null.....");
        }
    }

    private void allowCheck(Map<String, String> checkMap) throws AllowException {
        List<String> list = getTrxCode();
        Boolean flage = true;
        String trxCode = checkMap.get("trxCode");
        for (String strTrxCode : list) {
            if (strTrxCode.equals(trxCode)) {
                flage = false;
                break;
            }
        }
        if (flage) {
            throw new AllowException();
        }
    }

    private List<String> getTrxCode() {

        String string = PropertiesUtil.get("trxCodeList");//PropertiesUtil.get("trxCodeList");
        List<String> list = new ArrayList<String>();
        String[] str = string.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(0, str[i]);
        }
        return list;
    }

    //获取完整版本的PP请求参数
    public Map<String, String> formatDataFullRequest(ServletRequest request) throws IOException, MessageException, AllowException {
        Map<String, String> returnMap = new HashMap<String, String>();
        String edata = new String();

        if (request.getParameter("eData") != null) {
            //数据不为流的时候获取的方法
            edata = request.getParameter("eData");
            returnMap.put("signMsg", request.getParameter("signMsg"));
            returnMap.put("version", request.getParameter("version"));
            returnMap.put("trxCode", request.getParameter("trxCode"));
            returnMap.put("notifyUrl", request.getParameter("notifyUrl"));
        } else {
            //当字段超长被自动转换为流的时候的获取方式
            ServletInputStream sin = ((HttpServletRequest) request).getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            String content;
            byte buffer[] = new byte[4096];
            for (int n = 0; -1 != (n = sin.read(buffer)); ) {
                output.write(buffer, 0, n);
            }
            byte[] reqdata = output.toByteArray();

            content = new String(reqdata, "UTF-8");
            //不需要在这个地方进行重新编码
            Map<String, String> map = new HashMap<String, String>();
            if (content == null) {
                return null;
            } else {
                String s1[] = content.split("&");

                for (String mid : s1) {
                    //进行判空处理
                    String s2[] = mid.split("=");
                    try {
                        logger.debug("key值为:{}", s2[0].toLowerCase());

                        map.put(s2[0].toLowerCase(), s2[1]);
                    } catch (Exception e) {
                        logger.warn("传入{}为空，赋值为空", s2[0].toLowerCase());
                        map.put(s2[0].toLowerCase(), "");
                    }
                }
                returnMap = map;
            }
            edata = returnMap.get("eData");
        }
        if (StringUtils.isBlank(edata)) {
            return null;
        }
        logger.debug("request data is {}", edata);

        returnMap.put("edata", edata);
        return returnMap;
    }

}
