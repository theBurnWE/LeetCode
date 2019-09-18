package com.shcepp.shdippsvr.business.dao.elasticsearch;

import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticSearchQueryResultBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.entity.elasticsearch.ElasticSearchEntity;
import com.shcepp.shdippsvr.business.enums.BaseEnums;
import com.shcepp.shdippsvr.business.service.BaseService;

import java.util.List;

/**
 * ES搜索引擎的DAO层
 *
 * @author BrunE
 * @date 2019-07-18 20:49
 **/
public interface ElasticsearchServiceDao extends BaseService {

    /*索引查询异常*/
    static final String INDEX_QUERY_ERROR = "912";
    /* 索引查询异常*/
    static final String INDEX_QUERY_ERROR_MESSAGE = "索引查询异常";

    /*索引查询异常*/
    static final String DATA_QUERY_ERROR = "913";
    /* 索引查询异常*/
    static final String DATA_QUERY_ERROR_MESSAGE = "数据查询异常";

    /*索引查询异常*/
    static final String NO_SUCH_DATA = "914";
    /* 索引查询异常*/
    static final String NO_SUCH_DATA_MESSAGE = "未查询到对应数据";

    //获取索引名称
    String getIndexName(ShdippElasticSearchBean queryBean);

    //新增数据/更新数据//删除数据使用状态位实现
    //CUD数据的时候使用精确的索引名称
    //操作成功返回true
    //方法改为createorupdate
    boolean save(ShdippElasticSearchBean queryBean);

    //新增数据/更新数据//删除数据使用状态位实现
    //CUD数据的时候使用精确的索引名称
    //操作成功返回true
    //方法改为createorupdate
    boolean save(String id, String indexName, ElasticSearchEntity entity);

    //新增数据/更新数据//删除数据使用状态位实现
    //CUD数据的时候使用精确的索引名称
    //操作成功返回true
    //方法改为createorupdate
    boolean update(ShdippElasticSearchBean queryBean);

    //新增数据/更新数据//删除数据使用状态位实现
    //CUD数据的时候使用精确的索引名称
    //操作成功返回true
    //方法改为createorupdate
    boolean update(ElasticSearchEntity queryBean);

    //新增数据/更新数据//删除数据使用状态位实现
    //CUD数据的时候使用精确的索引名称
    //操作成功返回true
    //方法改为createorupdate
    boolean delete(ShdippElasticSearchBean queryBean);

    //新增数据/更新数据//删除数据使用状态位实现
    //CUD数据的时候使用精确的索引名称
    //操作成功返回true
    //方法改为createorupdate
    boolean delete(ElasticSearchEntity queryBean);

    //增加删除操作

    //根据ID查询数据
    ElasticSearchQueryResultBean getEsDataById(String indexName, String id, boolean status);

    //根据ID查询数据
    //默认查询有效数据
    ElasticSearchQueryResultBean getEsDataById(String indexName, String id);

    //根据Key值模糊查询数据
    ElasticsearchPagination getEsDataInFuzzyByKeyAndAttr(String key,
                                                         List<BaseEnums> attrList,
                                                         List<BaseEnums> fuzzyAttrList,
                                                         ShdippElasticSearchBean queryBean,
                                                         ElasticsearchPagination page);

}
