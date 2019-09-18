package com.shcepp.shdippsvr.business.service.elasticsearch;

import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.enums.BaseEnums;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 用于获取查询类
 *
 * @author BrunE
 * @date 2019-08-27 14:50
 **/
public class EsClient  {

    private static final Logger logger = LoggerFactory.getLogger(EsClient.class);

    /* 索引名称*/
    private String indexName;
    /* ES数据中的唯一id*/
    private String id = null;

//    /* 类型，只可以有一个*/
//    private String category;
//
//    /* 区域，只可以有一个*/
//    private String region;
//
//    /* 主站/分站，必填，会有全部查询*/
//    private String domain;
//
//    /*语言，必填，会有全量数据查询*/
//    private String lan;

    /*传入的业务bean*/
    private Object magicObject;
    //  @JSONField(serialize=false)
    // 加上本标签之后不会被序列化为json格式的字段

    /*查询创建时间起始时间*/
    private String createDateStartTime;

    /*查询创建时间结束时间*/
    private String createDateEndTime;

    /*查询更新时间起始时间*/
    private String updateDateStartTime;

    /*查询更新时间结束时间*/
    private String updateDateDateEndTime;

    /*查询结果的排序关键字*/
    private String sortKey;

    /*查询结果的排序顺序*/
    private SortOrder sortMethod;
    /*userID*/
    private String userId;

    /*属性值的枚举*/
    private List<BaseEnums> attrList;
    /*是否生效*/
    private boolean isDelete;
    /*查询值*/
    private String queryKey;

    private EsClient(EsClient.Builder builder) {
        this.indexName = builder.indexName;
        this.id = builder.id;
        this.magicObject = builder.magicObject;
        this.createDateStartTime = builder.createDateStartTime;
        this.createDateEndTime = builder.createDateEndTime;
        this.updateDateStartTime = builder.updateDateStartTime;
        this.updateDateDateEndTime = builder.updateDateDateEndTime;
        this.sortKey = builder.sortKey;
        this.sortMethod = builder.sortMethod;
        this.userId = builder.userId;
        this.attrList = builder.attrList;
        this.isDelete = builder.isDelete;
        this.queryKey = builder.queryKey;

    }

    public static EsClient.Builder Builder() {
        return new EsClient.Builder();
    }

    public static final class Builder  {

        private String indexName;
        private String id;
        private String userId;
        private Object magicObject;
        private String createDateStartTime;
        private String createDateEndTime;
        private String updateDateStartTime;
        private String updateDateDateEndTime;
        private String sortKey;
        private SortOrder sortMethod;
        private boolean isDelete;
        private String queryKey;
        private List<BaseEnums> attrList;

        public Builder() {
        }

        public EsClient.Builder indexName(String val) {
            this.indexName = val;
            return this;
        }

        public EsClient.Builder idVal(String val) {
            this.id = val;
            return this;
        }

        public EsClient.Builder attrList(List<BaseEnums> val) {
            this.attrList = val;
            return this;
        }

        public EsClient.Builder createDateStartTime(String createDateStartTime) {
            this.createDateStartTime = createDateStartTime;
            return this;
        }

        public EsClient.Builder createDateEndTime(String createDateEndTime) {
            this.createDateEndTime = createDateEndTime;
            return this;
        }

        public EsClient.Builder updateDateStartTime(String updateDateStartTime) {
            this.updateDateStartTime = updateDateStartTime;
            return this;
        }

        public EsClient.Builder updateDateDateEndTime(String updateDateDateEndTime) {
            this.updateDateDateEndTime = updateDateDateEndTime;
            return this;
        }

        public EsClient.Builder sortKey(String sortKey) {
            this.sortKey = sortKey;
            return this;
        }

        public EsClient.Builder sortMethod(SortOrder sortMethod) {
            this.sortMethod = sortMethod;
            return this;
        }

        public EsClient.Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public EsClient.Builder isDelete(boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public EsClient.Builder queryKey(String queryKey) {
            this.queryKey = queryKey;
            return this;
        }

        //根据传入的参数
        public ShdippElasticSearchBean build() {

            ShdippElasticSearchBean reqBean = new ShdippElasticSearchBean();
            reqBean.setId(this.id);
            reqBean.setMagicObject(this.magicObject);
            reqBean.setCreateDateStartTime(this.createDateStartTime);
            reqBean.setCreateDateEndTime(this.createDateEndTime);
            reqBean.setUpdateDateStartTime(this.updateDateStartTime);
            reqBean.setUpdateDateDateEndTime(this.updateDateDateEndTime);
            reqBean.setSortKey(this.sortKey);
            reqBean.setSortMethod(this.sortMethod);
            reqBean.setUserId(this.userId);
            reqBean.setAttrList(this.attrList);
            reqBean.setIndexName(this.indexName);
            reqBean.setDelete(this.isDelete);
            reqBean.setQueryKey(this.queryKey);
            return reqBean;
        }
    }
}
