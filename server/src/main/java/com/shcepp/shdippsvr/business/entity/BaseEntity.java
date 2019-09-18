package com.shcepp.shdippsvr.business.entity;

import com.shcepp.shdippsvr.business.exception.BizCheckException;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * entity 父类
 *
 * @author liutq
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

    public static final String F_NAME_SPLIT = ":";
    public static final String FILE_SPLIT = ",";
    public static final String SEND_FLAG_ON = "00";// 发送开关打开
    public static final String SEND_FLAG_OFF = "01";// 发送开关关闭
    public static final String DELETE_ENABLE = "0";// 发送开关打开
    public static final String DELETE_DISABLE = "1";// 发送开关关闭
    public static final String AUDIT_SUCCESS = "T";// 操作成功
    public static final String AUDIT_FAIL = "F";// 操作失败
    private static final String SPECIAL_PARAMS = "serialVersionUID";
    private static final Logger logger = LoggerFactory.getLogger(BaseEntity.class);

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                                                  ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toAuditString() {
        StringBuilder midStr = new StringBuilder("");
        try {

            Class<?> cls = Class.forName(this.getClass().getName());
            Field[] fields = cls.getDeclaredFields();
            //保证线程安全
            for (Field field : fields) {
                //设置属性可访问
                ((AccessibleObject) field).setAccessible(true);
                //越过特殊字符
                if (!SPECIAL_PARAMS.equals(field.getName())) {

                    String value = String.valueOf(field.get(this));
                    //在数据不为空的时候拼接字符串
                    if (StringUtils.isNotEmptyWithNUllCheckStr(value)) {
                        midStr.append(field.getName());
                        midStr.append(F_NAME_SPLIT);
                        midStr.append(value);
                        midStr.append(FILE_SPLIT);
                    }
                }
            }
            return midStr.toString();
        } catch (Exception e) {

            logger.error("在进行字符串拼接的时候出现异常", e);
            throw new BizCheckException(e);
        }
    }
}
