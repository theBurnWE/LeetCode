package com.shcepp.shdippsvr.business.bean.elasticsearch;

import com.shcepp.shdippsvr.business.bean.BaseBean;
import com.shcepp.shdippsvr.business.entity.elasticsearch.ElasticSearchEntity;
import com.shcepp.shdippsvr.business.enums.BaseEnums;
import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
import com.shcepp.shdippsvr.business.util.DateUtils;
import com.shcepp.shdippsvr.sys.util.ExchangeUtil;
import org.elasticsearch.search.Scroll;

import java.util.Date;
import java.util.List;

/**
 * ES的查询结果bean
 *
 * @author BrunE
 * @date 2019-08-20 13:53
 **/
public class ElasticSearchQueryResultBean extends BaseBean {

    private static final long serialVersionUID = 2570882915592422367L;

    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 被分词的消息
     */
    private String message;
    /**
     * 原始消息的json
     */
    private String orMessage;

    /**
     * id
     */
    private String id;
    /**
     * 对应的精确的索引的名字
     */
    private String indexName;
    /**
     * 对应的分页数据
     */
    private Scroll scroll;
    /**
     * userID
     */
    private String userId;

    /**
     * 是否起效，true：有效数据，false：无效数据
     */
    private boolean isDelete;
    /*属性值的枚举*/
    private List<BaseEnums> attrList;
    /*属性值的原始串*/
    private String attrStr;
    /**国家地区**/
    private String region;
    /*创建时间*/
    private Date createTime;
    /*更新时间时间*/
    private Date updateTime;



    //构建保存的bean
    public static ElasticSearchQueryResultBean parseResult(ElasticSearchEntity entity) {
        ExchangeUtil exchangeUtil = new ExchangeUtil();
        ElasticSearchQueryResultBean resBean = new ElasticSearchQueryResultBean();
        resBean.setId(entity.getId());
        resBean.setOrMessage(entity.getOrMessage());
        resBean.setMessage(entity.getMessage());
        resBean.setAttrList(exchangeUtil.attrListToStr(entity.getAttributes(), ElasticsearchService.ES_QUERY_SPLIT_CHAR));
        resBean.setUserId(entity.getUserID());
        resBean.setIsDelete(entity.getIsDelete());
        resBean.setRegion(entity.getRegion());
        if (null != entity.getCreateTime()) {
            resBean.setCreateTime(DateUtils.stringToDate(DateUtils.dateTimePattern, entity.getCreateTime()));
        }
        if (null != entity.getUpdateTime()) {
            resBean.setCreateTime(DateUtils.stringToDate(DateUtils.dateTimePattern, entity.getUpdateTime()));
        }
        return resBean;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Scroll getScroll() {
        return scroll;
    }

    public void setScroll(Scroll scroll) {
        this.scroll = scroll;
    }

    public List<BaseEnums> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<BaseEnums> attrList) {
        this.attrList = attrList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean delete) {
        isDelete = delete;
    }

    public String getAttrStr() {
        return attrStr;
    }

    public void setAttrStr(String attrStr) {
        this.attrStr = attrStr;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
