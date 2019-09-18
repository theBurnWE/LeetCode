package com.shcepp.shdippsvr.sys.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

/**
 * Created by YT on 2017/6/9.
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz, Feature.OrderedField);
        } catch (Exception e) {
            logger.error("error parse", e);
        }
        return null;
    }

    public static <T> String beanToJson(T entity) {
        try {
            String jsonString = JSON.toJSONString(entity, SerializerFeature.DisableCircularReferenceDetect ,SerializerFeature.MapSortField, SerializerFeature.WriteDateUseDateFormat);
            return jsonString.equals("null") ? null:jsonString;
        } catch (Exception e) {
            logger.error("parse error ", e);
        }
        return null;
    }

    public static Object formatJson2Object(String jsonString, Class object) {

        Object jsonObject  = JSON.parseObject(jsonString, object);

        return jsonObject;
    }
}
