package com.shcepp.shdippsvr.business.bean.elasticsearch;

import com.shcepp.shdippsvr.business.bean.BaseEspojoBean;
import com.shcepp.shdippsvr.business.exception.BizCheckException;
import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * @author zkmao
 * @description
 * @date 2019/9/6 17:26
 */
public abstract class BaseItemPojo extends BaseEspojoBean {

    private static final Logger logger = LoggerFactory.getLogger(BaseItemPojo.class);

    private static final long serialVersionUID = -4823335096220000756L;

    private static final String SPECIAL_PARAMS = "serialVersionUID";
    private String id;
    private String dataDomain;
    private String lan;
    //false正常，true删除
    private boolean delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataDomain() {
        return dataDomain;
    }

    public void setDataDomain(String dataDomain) {
        this.dataDomain = dataDomain;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toEsString() {
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
                        midStr.append(value).append(ElasticsearchService.ES_QUERY_SPLIT_CHAR);
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
