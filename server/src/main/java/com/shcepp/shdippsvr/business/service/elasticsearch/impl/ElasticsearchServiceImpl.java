package com.shcepp.shdippsvr.business.service.elasticsearch.impl;

/**
 * 搜索引擎的实现类
 *
 * @author BrunE
 * @date 2019-07-25 17:53
 **/

import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticSearchQueryResultBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.dao.elasticsearch.ElasticsearchServiceDao;
import com.shcepp.shdippsvr.business.dao.elasticsearch.impl.ElasticsearchServiceDaoImpl;
import com.shcepp.shdippsvr.business.enums.BaseEnums;
import com.shcepp.shdippsvr.business.exception.ErrorException;
import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);

    @Autowired
    private ElasticsearchServiceDaoImpl elasticsearchServiceDaoImpl;

    @Override
    public ElasticsearchPagination queryEsByBean(ShdippElasticSearchBean bean, ElasticsearchPagination page) {
        logger.debug("开始根据{}，{}对ES进行查询", JsonUtil.beanToJson(bean), JsonUtil.beanToJson(page));
        List<ElasticSearchQueryResultBean> resultBeanList = new ArrayList<>();
        String indexName = elasticsearchServiceDaoImpl.getIndexName(bean);
        bean.setIndexName(indexName);
        List<BaseEnums> attrList = new ArrayList<>();
        List<BaseEnums> fuzzyAttrList = new ArrayList<>();
        if (StringUtils.isNotEmptyWithNUllCheckStr(bean.getId())) {
            resultBeanList.add(elasticsearchServiceDaoImpl.getEsDataById(indexName, bean.getId(), bean.getIsDelete()));
            page.setContentList(resultBeanList);
        } else {
            attrList = bean.getAttrList();
            fuzzyAttrList = bean.getFuzzyAttrList();
            page = elasticsearchServiceDaoImpl.getEsDataInFuzzyByKeyAndAttr(bean.getQueryKey(), attrList, fuzzyAttrList, bean, page);
        }
        logger.debug("查询结果为:{}", JsonUtil.beanToJson(page));

        return page;
    }

    @Override
    public ElasticSearchQueryResultBean queryEsById(ShdippElasticSearchBean bean) {
        logger.debug("开始根据{}，{}对ES进行查询", JsonUtil.beanToJson(bean));

        ElasticSearchQueryResultBean resultBean;
        String indexName = elasticsearchServiceDaoImpl.getIndexName(bean);
        bean.setIndexName(indexName);
        resultBean = elasticsearchServiceDaoImpl.getEsDataById(indexName, bean.getId(), bean.getIsDelete());
        logger.debug("查询结果为:{}", JsonUtil.beanToJson(resultBean));
        return resultBean;
    }

    @Override
    public boolean saveOrUpdate(ShdippElasticSearchBean bean) {
        bean.setIndexName(elasticsearchServiceDaoImpl.getIndexName(bean));
        //如果查询到数据，则更新，否则保存
        if (null != queryEsById(bean)) {

            return elasticsearchServiceDaoImpl.update(bean);
        } else {
            return elasticsearchServiceDaoImpl.save(bean);
        }

    }

    @Override
    public boolean delete(ShdippElasticSearchBean bean) {
        String indexName = elasticsearchServiceDaoImpl.getIndexName(bean);
        bean.setIndexName(indexName);
        if (null != queryEsById(bean)) {
            bean.setDelete(true);
            return elasticsearchServiceDaoImpl.update(bean);
        } else {
            logger.info("在对{}进行删除的时候，未能查找到数据", JsonUtil.beanToJson(bean));
            throw new ErrorException(ElasticsearchServiceDao.NO_SUCH_DATA, ElasticsearchServiceDao.NO_SUCH_DATA_MESSAGE);
        }
    }
}
