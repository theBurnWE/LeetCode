package com.shcepp.shdippsvr.business.bean.elasticsearch;

import com.alibaba.fastjson.annotation.JSONField;
import com.shcepp.shdippsvr.business.bean.BaseBean;

/**
 * Es的基础bean
 *
 * @author BrunE
 * @date 2019-08-23 16:06
 **/
public class ElasticSearchBaseBean extends BaseBean {

    private static final long serialVersionUID = 4264732501142157374L;

    /**
     * 索引名称
     */
    @JSONField(serialize = false)
    private String indexName;
    /**
     * 索引名称
     */
    @JSONField(serialize = false)
    private String id;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
