package com.shcepp.shdippsvr.business.bean.elasticsearch;

import com.shcepp.shdippsvr.business.entity.elasticsearch.ElasticSearchEntity;
import com.shcepp.shdippsvr.business.enums.BaseEnums;
import com.shcepp.shdippsvr.business.enums.DomainType;
import com.shcepp.shdippsvr.business.enums.LanType;
import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
import com.shcepp.shdippsvr.business.util.DateUtils;
import com.shcepp.shdippsvr.sys.util.ExchangeUtil;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.elasticsearch.search.sort.SortOrder;

import java.util.*;

/**
 * shdipp项目对ES进行增删改查的bean
 *
 * @author BrunE
 * @date 2019-08-20 13:53
 **/
public class ShdippElasticSearchBean extends ElasticSearchBaseBean {

    private static final long serialVersionUID = -8971804104389422512L;

    /*传入的业务bean*/
    //  @JSONField(serialize=false)
    // 加上本标签之后不会被序列化为json格式的字段
    private Object magicObject;
    /*创建时间*/
    private Date createDate;
    /*更新时间*/
    private Date updateDate;
    /*查询创建时间起始时间*/
    private String createDateStartTime;

    /*查询创建时间结束时间*/
    private String createDateEndTime;

    /*查询更新时间起始时间*/
    private String updateDateStartTime;

    /*查询更新时间结束时间*/
    private String updateDateDateEndTime;

    /*查询国别地区*/
    private String region;

    /*查询结果的排序关键字*/
    private String sortKey;

    /*查询结果的排序顺序*/
    private SortOrder sortMethod;
    /*UserID*/
    private String userId;
    /*是否删除 true,无效数据，false，有效数据,默认查询有效数据*/
    private boolean isDelete = false;

    /*属性值的枚举*/
    private List<BaseEnums> attrList;

    /*模糊查询的属性值的枚举*/
    private List<BaseEnums> fuzzyAttrList;

    /*查询值*/
    private String queryKey;

    public static ShdippElasticSearchBean.Builder Builder() {
        return new ShdippElasticSearchBean.Builder();
    }

    //构建保存的bean
    public static ElasticSearchEntity buildEntity(ShdippElasticSearchBean entity) {
        ElasticSearchEntity reqBean = new ElasticSearchEntity();
        reqBean.setId(entity.getId());
        reqBean.setOrMessage(JsonUtil.beanToJson(entity.magicObject));
        if (entity.magicObject instanceof BaseItemPojo) {
            BaseItemPojo pojo = ((BaseItemPojo) entity.magicObject);
            reqBean.setMessage(pojo.toEsString());

        } else {
            reqBean.setMessage(ExchangeUtil.beanToString(entity.magicObject, ElasticsearchService.ES_QUERY_SPLIT_CHAR));
        }
        reqBean.setAttributes(ExchangeUtil.attrListToStr(entity.attrList, ElasticsearchService.ES_QUERY_SPLIT_CHAR));
        reqBean.setUserID(entity.userId);
        reqBean.setIsDelete(entity.isDelete);
        if (null != entity.getCreateDate()) {
            reqBean.setCreateTime(DateUtils.formatDate(DateUtils.dateTimePattern, entity.getCreateDate()));
        }
        if (null != entity.getUpdateDate()) {
            reqBean.setCreateTime(DateUtils.formatDate(DateUtils.dateTimePattern, entity.getUpdateDate()));
        }
        reqBean.setRegion(entity.region);
        return reqBean;
    }

    public Object getMagicObject() {
        return magicObject;
    }

    public void setMagicObject(Object magicObject) {
        this.magicObject = magicObject;
    }

    public String getCreateDateStartTime() {
        return createDateStartTime;
    }

    public void setCreateDateStartTime(String createDateStartTime) {
        this.createDateStartTime = createDateStartTime;
    }

    public String getCreateDateEndTime() {
        return createDateEndTime;
    }

    public void setCreateDateEndTime(String createDateEndTime) {
        this.createDateEndTime = createDateEndTime;
    }

    public String getUpdateDateStartTime() {
        return updateDateStartTime;
    }

    public void setUpdateDateStartTime(String updateDateStartTime) {
        this.updateDateStartTime = updateDateStartTime;
    }

    public String getUpdateDateDateEndTime() {
        return updateDateDateEndTime;
    }

    public void setUpdateDateDateEndTime(String updateDateDateEndTime) {
        this.updateDateDateEndTime = updateDateDateEndTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public SortOrder getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(SortOrder sortMethod) {
        this.sortMethod = sortMethod;
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

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<BaseEnums> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<BaseEnums> attrList) {
        this.attrList = attrList;
    }

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<BaseEnums> getFuzzyAttrList() {
        return fuzzyAttrList;
    }

    public void setFuzzyAttrList(List<BaseEnums> fuzzyAttrList) {
        this.fuzzyAttrList = fuzzyAttrList;
    }

    public static final class Builder<T extends BaseEnums> {

        private String indexName;
        private String id;
        private String userId;
        private Object magicObject;
        private String createDateStartTime;
        private String createDateEndTime;
        private String updateDateStartTime;
        private String updateDateDateEndTime;
        private String region;
        private String sortKey;
        private SortOrder sortMethod;
        private boolean isDelete;
        private String queryKey;
        private List<BaseEnums> attrList;
        private List<BaseEnums> fuzzyAttrList;
        private Date createDate;
        private Date updateDate;

        public Builder() {
        }

        public ShdippElasticSearchBean.Builder indexName(String val) {
            this.indexName = val;
            return this;
        }

        public ShdippElasticSearchBean.Builder idVal(String val) {
            this.id = val;
            return this;
        }

        public ShdippElasticSearchBean.Builder attrList(List<BaseEnums> attrList) {
            this.attrList = attrList;
            return this;
        }

        public ShdippElasticSearchBean.Builder fuzzyAttrList(List<BaseEnums> fuzzyAttrList) {
            this.fuzzyAttrList = fuzzyAttrList;
            return this;
        }

        public ShdippElasticSearchBean.Builder createDateStartTime(String createDateStartTime) {
            this.createDateStartTime = createDateStartTime;
            return this;
        }

        public ShdippElasticSearchBean.Builder createDateEndTime(String createDateEndTime) {
            this.createDateEndTime = createDateEndTime;
            return this;
        }

        public ShdippElasticSearchBean.Builder updateDateStartTime(String updateDateStartTime) {
            this.updateDateStartTime = updateDateStartTime;
            return this;
        }

        public ShdippElasticSearchBean.Builder updateDateDateEndTime(String updateDateDateEndTime) {
            this.updateDateDateEndTime = updateDateDateEndTime;
            return this;
        }

        public ShdippElasticSearchBean.Builder region(String region) {
            this.region = region;
            return this;
        }

        public ShdippElasticSearchBean.Builder sortKey(String sortKey) {
            this.sortKey = sortKey;
            return this;
        }

        public ShdippElasticSearchBean.Builder sortMethod(SortOrder sortMethod) {
            this.sortMethod = sortMethod;
            return this;
        }

        public ShdippElasticSearchBean.Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public ShdippElasticSearchBean.Builder isDelete(boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public ShdippElasticSearchBean.Builder queryKey(String queryKey) {
            this.queryKey = queryKey;
            return this;
        }

        public ShdippElasticSearchBean.Builder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ShdippElasticSearchBean.Builder updateDate(Date updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public ShdippElasticSearchBean.Builder magicObject(Object magicObject) {
            this.magicObject = magicObject;
            return this;
        }

        //根据传入的参数
        public ShdippElasticSearchBean build() {
            BaseItemPojo pojo;
            ShdippElasticSearchBean reqBean = new ShdippElasticSearchBean();
            if (this.magicObject instanceof BaseItemPojo) {
                pojo = ((BaseItemPojo) magicObject);
                List<BaseEnums> list = new ArrayList<>();
                Map<String, BaseEnums> map = new HashMap<>();
                //在pojo中的ID为空的时候使用显式ID
                if (StringUtils.isNotEmptyWithNUllCheckStr(pojo.getId())) {
                    reqBean.setId(pojo.getId());
                } else {
                    reqBean.setId(this.id);

                }
                reqBean.setDelete(pojo.isDelFlag());
                String domin = pojo.getDataDomain();
                String lan = pojo.getLan();
                this.attrList.forEach(f -> {
                    map.put(f.toString(), f);
                });
                if (StringUtils.isNotEmptyWithNUllCheckStr(domin)) {
                    DomainType domainType = DomainType.getEnumsByCode(domin);
                    map.put(domainType.toString(), domainType);
                }
                if (StringUtils.isNotEmptyWithNUllCheckStr(lan)) {
                    LanType lanType = LanType.getEnumsByCode(lan);
                    map.put(lanType.toString(), lanType);
                }
                map.forEach((k, v) -> {
                    list.add(v);
                });

                reqBean.setAttrList(list);

            } else {
                reqBean.setId(this.id);
                reqBean.setDelete(this.isDelete);
                reqBean.setAttrList(this.attrList);
            }

            reqBean.setMagicObject(this.magicObject);
            reqBean.setCreateDateStartTime(this.createDateStartTime);
            reqBean.setCreateDateEndTime(this.createDateEndTime);
            reqBean.setUpdateDateStartTime(this.updateDateStartTime);
            reqBean.setUpdateDateDateEndTime(this.updateDateDateEndTime);
            reqBean.setSortKey(this.sortKey);
            reqBean.setSortMethod(this.sortMethod);
            reqBean.setUserId(this.userId);
            reqBean.setIndexName(this.indexName);
            reqBean.setCreateDate(this.createDate);
            reqBean.setUpdateDate(this.updateDate);
            reqBean.setQueryKey(this.queryKey);
            reqBean.setRegion(this.region);
            reqBean.setFuzzyAttrList(this.fuzzyAttrList);
            return reqBean;
        }

    }

}
