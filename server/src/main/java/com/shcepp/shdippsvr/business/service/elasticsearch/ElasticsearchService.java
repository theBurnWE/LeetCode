package com.shcepp.shdippsvr.business.service.elasticsearch;

import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticSearchQueryResultBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.service.BaseService;

import java.util.List;

/**
 * ES搜索引擎的service
 */
public interface ElasticsearchService extends BaseService {


    public static final String ES_QUERY_SPLIT_CHAR = ",";

    /**
     * 根据封装对象查查询ES的内容
     *
     * @param bean 对象属性
     * @param page 分页参数
     * @return 查询结果
     */
    ElasticsearchPagination queryEsByBean(ShdippElasticSearchBean bean, ElasticsearchPagination page);

    /**
     * 根据ID查询ES的对应内容
     *
     * @param bean 封装的bean
     * @return 查询结果
     */
    ElasticSearchQueryResultBean queryEsById(ShdippElasticSearchBean bean);

    /**
     * 保存或是更新ES的内容
     * 由于是否删除也是一个对应的属性，所以删除逻辑也包含在本方法中
     * 在进行增删改查操作的时候需要检查
     *
     * @param bean 封装的bean
     * @return 查询结果
     */
    boolean saveOrUpdate(ShdippElasticSearchBean bean);

    /**
     * 删除数据
     *
     * @param bean 封装的bean
     * @return 查询结果
     */
    boolean delete(ShdippElasticSearchBean bean);

}
