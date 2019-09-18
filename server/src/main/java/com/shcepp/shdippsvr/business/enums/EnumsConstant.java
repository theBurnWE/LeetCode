package com.shcepp.shdippsvr.business.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类辅助类
 *
 * @author BrunE
 * @date 2019-09-05 13:29
 **/
public class EnumsConstant<T extends BaseEnums> {

    private static final Logger logger = LoggerFactory.getLogger(EnumsConstant.class);

    /**
     * 所有枚举对象的 map
     */
    public static Map<Class, Map<Object, BaseEnums>> ENUM_MAP = new HashMap<Class, Map<Object, BaseEnums>>();
    /**
     * 存放单个枚举对象 map常量定义
     */
    public static Map<Object, BaseEnums> SINGLE_ENUM_MAP = new HashMap<Object, BaseEnums>();

    //获取初始值
    public static BaseEnums getEnums(String classVal, String value) {
        Map<Object, BaseEnums> enumsMap;
        try {
            enumsMap = ENUM_MAP.get(Class.forName(classVal));
            if (null == enumsMap) {
                //如果没有解析过对应的entity则进行数据解析
                initEmu(classVal);
                enumsMap = ENUM_MAP.get(Class.forName(classVal));

            }
            return enumsMap.get(value);

        } catch (ClassNotFoundException e) {
            logger.info("属性数据解析异常", e);
            return null;
        }

    }

    public static void initEmu(String classVal) {
        try {

            Class<?> clazz1 = Class.forName(classVal);

            if (clazz1.isEnum()) {
                //反射获取枚举类
                Class<BaseEnums> clazz = (Class<BaseEnums>) Class.forName(clazz1.getName());
                //获取所有枚举实例
                BaseEnums[] enumConstants = clazz.getEnumConstants();
                //获取对应的方法

                Method method = clazz.getMethod("values");
                BaseEnums inter[] = (BaseEnums[]) method.invoke(null, null);
                for (BaseEnums enumMessage : inter) {
                    SINGLE_ENUM_MAP.put(enumMessage.getValues(), enumMessage);
                    ENUM_MAP.put(Class.forName(clazz1.getName()), SINGLE_ENUM_MAP);
                }

            }
        } catch (Exception ex) {
            logger.warn("属性数据初始化异常", ex);
        }
    }
}
