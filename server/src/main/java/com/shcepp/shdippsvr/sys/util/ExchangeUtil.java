package com.shcepp.shdippsvr.sys.util;

import com.shcepp.shdippsvr.business.enums.BaseEnums;
import com.shcepp.shdippsvr.business.enums.EnumsConstant;
import com.shcepp.shdippsvr.business.exception.BizCheckException;
import com.shcepp.shdippsvr.business.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BrunE
 * @date 2018-12-16 10:35
 **/
public class ExchangeUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeUtil.class);

    /**
     * 将bean转换为String
     *
     * @param bean 传入的需要被拼装的bean
     * @return 拼装好的bean
     */
    public static String beanToString(Object bean, String split) {
        StringBuilder midStr = new StringBuilder("");
        try {
            if (null != bean) {

                Class<?> cls = Class.forName(bean.getClass().getName());
                Field[] fields = cls.getDeclaredFields();
                //保证线程安全
                for (Field field : fields) {
                    //设置属性可访问
                    ((AccessibleObject) field).setAccessible(true);
                    String value = String.valueOf( field.get(bean));
                    //在数据不为空的时候拼接字符串
                    if (StringUtils.isNotEmptyWithNUllCheckStr(value)) {
                        midStr.append(value).append(split);
                    }
                }
            }
            return midStr.toString();
        } catch (Exception e) {

            logger.error("在进行字符串拼接的时候出现异常", e);
            throw new BizCheckException(e);
        }
    }

    /**
     * 将bean转换为String
     *
     * @return 拼装好的bean
     */
    public static String attrListToStr(List<BaseEnums> attrList, String split) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < attrList.size(); i++) {
                sb.append(attrList.get(i).toString());
                sb.append(split);
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e) {

            logger.error("在进行字符串拼接的时候出现异常", e);
            throw new BizCheckException(e);
        }
    }

    /**
     * 将bean转换为String
     *
     * @return 拼装好的bean
     */
    public static List<BaseEnums> attrListToStr(String attrStr, String split) {
        List<BaseEnums> attrList = new ArrayList<>();
        BaseEnums enumEn;
        String[] attr = attrStr.split(split);
        String[] detial;
        for (String detailStr : attr) {
            try {

                detial = detailStr.split(FileService.FILE_URL_PATTEN);
                enumEn = EnumsConstant.getEnums(detial[0], detial[1]);
                attrList.add(enumEn);

            } catch (Exception e) {

                logger.error("在解析属性{}的时候出现异常", detailStr, e);
                throw new BizCheckException(e);
            }
        }
        return attrList;
    }

}
