package com.shcepp.shdippsvr.sys.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Merjiezo on 2017/1/10.
 */
public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    public static Class getClassByName(String packages, String className) throws ClassNotFoundException {
        return Class.forName(packages + className);
    }

    /**
     * 动态调用类方法
     * @param newClass
     * @param methodName
     * @return
     */
    public static Object getMethodResult(Class newClass, String methodName, Object[] args) {
        Method method = null;
        Object result = null;

        //获取需要调用的方法
        for(Method m: newClass.getDeclaredMethods()) {

            if(m.getName().equalsIgnoreCase(methodName)) {
                method = m;
                break;
            }
        }

        //调用方法
        try {
            result = method.invoke(newClass, args);
        } catch (Exception e) {
            logger.error("error happen {} ",e);
        }

        return result;
    }
}
