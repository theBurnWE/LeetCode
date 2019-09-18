package com.shcepp.shdippsvr.sys.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang3.StringUtils;

import java.util.TimeZone;

/**
 * @author EP-mlzhang
 * @version V1.0
 * @Title: XMLUtil.java
 * @Package com.shcepp.ciqsingledecl.util
 * @Description: xml工具类
 * @date 2016年9月6日 下午2:26:06
 */
public class XMLUtil {
    public static String createXML(Object ob) {
        XStreamNameCoder nameCoder = new XStreamNameCoder();
        XStream xstream = new XStream(new XppDriver(nameCoder));
        xstream.autodetectAnnotations(true);
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + xstream.toXML(ob);
        return xmlStr;
    }

    public static <T> T createBean(String msg, Class<T> className) {
        XStreamNameCoder nameCoder = new XStreamNameCoder();
        XStream xstream = new XStream(new DomDriver("UTF-8", nameCoder));
        xstream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", new String[]{"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}, TimeZone.getTimeZone("GMT+8")) {
            @Override
            public Object fromString(String str) {
                if (StringUtils.isEmpty(str)) {
                    return null;
                }
                return super.fromString(str);
            }
        });

        xstream.registerConverter(new BigDecimalConverter() {
            @Override
            public Object fromString(String str) {
                if (StringUtils.isEmpty(str)) {
                    return null;
                }
                return super.fromString(str);
            }
        });
        xstream.registerConverter(new DoubleConverter() {
            @Override
            public Object fromString(String str) {
                if (StringUtils.isEmpty(str)) {
                    return null;
                }
                return super.fromString(str);
            }
        });
        xstream.registerConverter(new IntConverter() {
            @Override
            public Object fromString(String str) {
                if (StringUtils.isEmpty(str)) {
                    return null;
                }
                return super.fromString(str);
            }
        });
        xstream.ignoreUnknownElements();//忽略未定义的属性
        xstream.processAnnotations(className);
        return (T) xstream.fromXML(msg);
    }
}
