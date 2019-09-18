package com.shcepp.shdippsvr.business.entity.elasticsearch;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 经过封装之后es结构对应的实体类
 *
 * @author BrunE
 * @date 2019-08-28 11:30
 **/
public class ElasticSearchEntity implements java.io.Serializable{

    public static final String ID_V = "id";
    /**业务类型*/
    public static final String BUSINESS_TYPE ="businessType";
    /**
     * 被分词的消息
     * 分词规则：不同的属性值拼接的时候以,分割
     * */
    public static final String MESSAGE_V= "message";
    /**原始消息的JSON*/
    public static final String OR_MESSAGE = "orMessage";
    /**获取到的索引的名字*/
    public static final String INDEX_NAME ="indexName";
    /**用户ID*/
    public static final String USER_ID ="userID";
    /**创建时间*/
    public static final String  CREATE_DATE="createTime";
    /**是否生效**/
    public static final String  EFFECT_FLAGE="isDelete";
    /**更新时间*/
    public static final String  UPDATE_DATE="updateTime";
    /**地区*/
    public static final String  REGIGON="region";
    /**
     * 属性字段
     * 分词规则：不同的属性值拼接的时候以,分割
     * */
    public static final String MESSAGE_ATTR ="attributes";
    private static final long serialVersionUID = -8884575798671173426L;

    /**ES数据中的唯一id*/
    @JSONField(serialize=false)
    private String id ="";

    /**被分词的消息*/
    private String message;
    /**原始消息的json*/
    private String orMessage;
    /**属性值，由attrList转换得到*/
    private String attributes;
    /**更新时间*/
    private String updateTime;
    /**入库时间*/
    private String createTime;
    /**用户ID*/
    private String userID;
    /**是否起效，true：有效数据，false：无效数据*/
    private boolean isDelete;
    /**国家地区**/
    private String region;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrMessage() {
        return orMessage;
    }

    public void setOrMessage(String orMessage) {
        this.orMessage = orMessage;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean delete) {
        isDelete = delete;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
