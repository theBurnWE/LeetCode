package com.shcepp.shdippsvr.business.dao.elasticsearch.impl;

import com.fasterxml.jackson.module.afterburner.util.ClassName;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticSearchQueryResultBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.config.BaseConfig;
import com.shcepp.shdippsvr.business.config.EsConfig;
import com.shcepp.shdippsvr.business.dao.elasticsearch.ElasticsearchServiceDao;
import com.shcepp.shdippsvr.business.entity.elasticsearch.ElasticSearchEntity;
import com.shcepp.shdippsvr.business.enums.BaseEnums;
import com.shcepp.shdippsvr.business.exception.ErrorException;
import com.shcepp.shdippsvr.business.service.elasticsearch.impl.ElasticsearchServiceImpl;
import com.shcepp.shdippsvr.sys.config.ElasticsearchConfig;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * 搜索引擎dao的实现类
 *
 * @author BrunE
 * @date 2019-08-21 11:17
 **/
@Service
public class ElasticsearchServiceDaoImpl implements ElasticsearchServiceDao {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);

    private static final String ES_INDEX_NAME_SPLIT_CHAR = "-";
    private static final String ES_INDEX_NAME_ALLMATCH_CHAR = "*";

    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchConfig elasticsearchConfig;

    @Override
    public boolean update(ElasticSearchEntity queryBean) {
        return false;
    }

    @Override
    public boolean delete(ElasticSearchEntity queryBean) {
        return false;
    }

    @Override
    public String getIndexName(ShdippElasticSearchBean queryBean) {
        StringBuilder indexName = new StringBuilder("");
        indexName.append(EsConfig.SysCode);
        indexName.append(ES_INDEX_NAME_SPLIT_CHAR);
        indexName.append(EsConfig.IndexPatten);
        return indexName.toString();

    }

    @Override
    public boolean update(ShdippElasticSearchBean queryBean) {
        boolean dealResult;

        BulkRequest bulkRequest = new BulkRequest();
        //索引
        //文档
        //主键
        ElasticSearchEntity entity;
        entity = ShdippElasticSearchBean.buildEntity(queryBean);
        IndexRequest indexRequest = new IndexRequest(queryBean.getIndexName(), "_doc", String.valueOf(queryBean.getId()));
        //指定X为JSON格式
        indexRequest.source(JsonUtil.beanToJson(entity), XContentType.JSON);
        //打包
        bulkRequest.add(indexRequest);

        try {
            //批量上传【同步上传】
            //            client.bulkAsync();//异步上传
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
            return dealResult = true;
        } catch (IOException e) {
            logger.error("操作数据为{}，索引为{}的数据的时候操作失败，失败原因为：", JsonUtil.beanToJson(queryBean), queryBean.getIndexName(), e);
            throw new ErrorException(ElasticsearchServiceDao.DATA_QUERY_ERROR, ElasticsearchServiceDao.DATA_QUERY_ERROR_MESSAGE);
        }

    }

    @Override
    public boolean delete(ShdippElasticSearchBean queryBean) {

        BulkRequest bulkRequest = new BulkRequest();

        //索引
        //文档
        //主键
        IndexRequest indexRequest = new IndexRequest(queryBean.getIndexName(), "_doc", String.valueOf(queryBean.getId()));
        //指定X为JSON格式
        ElasticSearchEntity entity;

        entity = ShdippElasticSearchBean.buildEntity(queryBean);

        indexRequest.source(JsonUtil.beanToJson(entity), XContentType.JSON);
        //打包
        bulkRequest.add(indexRequest);

        try {

            //批量上传【同步上传】
            //            client.bulkAsync();//异步上传
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
            //关闭
            return true;
        } catch (IOException e) {
            logger.error("操作数据为{}，索引为{}的数据的时候操作失败，失败原因为：", JsonUtil.beanToJson(queryBean), queryBean.getIndexName(), e);
            throw new ErrorException(ElasticsearchServiceDao.DATA_QUERY_ERROR, ElasticsearchServiceDao.DATA_QUERY_ERROR_MESSAGE);
        }

    }

    @Override
    public boolean save(ShdippElasticSearchBean bean) {
        ElasticSearchEntity entity;
        entity = ShdippElasticSearchBean.buildEntity(bean);
        return save(entity.getId(), bean.getIndexName(), entity);
    }

    @Override
    public boolean save(String id, String indexName, ElasticSearchEntity entity) {
        boolean dealResult;

        BulkRequest bulkRequest = new BulkRequest();
        //索引
        //文档
        //主键
        IndexRequest indexRequest = new IndexRequest(indexName, "_doc", id);
        //指定X为JSON格式
        indexRequest.source(JsonUtil.beanToJson(entity), XContentType.JSON);
        //打包
        bulkRequest.add(indexRequest);

        try {

            //批量上传【同步上传】
            //            client.bulkAsync();//异步上传
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
            //关闭
            return true;
        } catch (IOException e) {
            logger.error("操作数据为{}，索引为{}的数据的时候操作失败，失败原因为：", JsonUtil.beanToJson(entity), indexName, e);
            throw new ErrorException(ElasticsearchServiceDao.DATA_QUERY_ERROR, ElasticsearchServiceDao.DATA_QUERY_ERROR_MESSAGE);
        }
    }

    @Override
    public ElasticSearchQueryResultBean getEsDataById(String indexName, String id) {
        return getEsDataById(indexName, id, false);
    }

    @Override
    public ElasticSearchQueryResultBean getEsDataById(String indexName, String id, boolean status) {

        ElasticSearchQueryResultBean res = null;
        Map<String, Object> dataMap = new HashMap<>();
        //指定索引
        SearchRequest searchRequest = new SearchRequest(indexName);//可以输入正则表达式进行模匹配
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //查询语句
        boolQueryBuilder.must(matchQuery("_id", String.valueOf(id)));
        //设置数据是否被删除
        boolQueryBuilder.must(QueryBuilders.matchQuery(ElasticSearchEntity.EFFECT_FLAGE, status));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        try {

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            long totalHits = hits.getTotalHits();
            float maxScore = hits.getMaxScore();
            //在有数据命中的情况下返回对应的参数
            if (totalHits > 0) {
                dataMap = hits.getHits()[0].getSourceAsMap();
                res = formatResult(dataMap);
            }
            return res;
        } catch (Exception e) {
            logger.error("查询ID为{}，索引为{}的数据的时候查询失败，失败原因为：", id, indexName, e);
            return null;
        }

    }

    @Override
    public ElasticsearchPagination getEsDataInFuzzyByKeyAndAttr(String key,
                                                                List<BaseEnums> attrList,
                                                                List<BaseEnums> fuzzyAttrList,
                                                                ShdippElasticSearchBean queryBean,
                                                                ElasticsearchPagination page) {
        List<ElasticSearchQueryResultBean> resultList = null;
        ElasticSearchQueryResultBean res;
        Map<String, Object> dataMap = new HashMap<>();
        //指定索引
        SearchRequest searchRequest = new SearchRequest(queryBean.getIndexName());//可以输入正则表达式进行模匹配
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //声明最后的查询bulider
        BoolQueryBuilder totalQueryBuilder = QueryBuilders.boolQuery();
        //常规查询builder
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //匹配查询Builder
        BoolQueryBuilder fuzzyQueryBuilder = QueryBuilders.boolQuery();

        //在查询值中进行查询
        if (StringUtils.isNotEmptyWithNUllCheckStr(key)) {
            boolQueryBuilder.must(matchQuery(ElasticSearchEntity.MESSAGE_V, String.valueOf(key)).operator(Operator.AND));

        }
        //在属性值中进行查询
        if (attrList != null && attrList.size() > 0) {
            attrList.forEach(f -> {
                boolQueryBuilder.must(matchQuery(ElasticSearchEntity.MESSAGE_ATTR, f.toString()).operator(Operator.AND));

            });
        }

        //在模式属性值中进行查询
        if (fuzzyAttrList != null && fuzzyAttrList.size() > 0) {
            fuzzyAttrList.forEach(f -> {
                fuzzyQueryBuilder.should(matchPhraseQuery(ElasticSearchEntity.MESSAGE_ATTR, f.toString()));

            });
        }
        if (StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getRegion())) {
            boolQueryBuilder.must(matchQuery(ElasticSearchEntity.REGIGON, queryBean.getRegion()).operator(Operator.AND));

        }

        //根据创建时间筛选数据
        if (StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getCreateDateStartTime()) &&
                StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getCreateDateEndTime())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticSearchEntity.CREATE_DATE).gte(queryBean.getCreateDateStartTime())
                                               .lte(queryBean.getCreateDateEndTime()));
        } else if (StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getCreateDateStartTime())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticSearchEntity.CREATE_DATE).gte(queryBean.getCreateDateStartTime()));
        }
        //根据更新时间筛选数据
        if (StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getUpdateDateStartTime()) &&
                StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getUpdateDateDateEndTime())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticSearchEntity.UPDATE_DATE).gte(queryBean.getUpdateDateStartTime())
                                               .lte(queryBean.getUpdateDateDateEndTime()));
        } else if (StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getUpdateDateStartTime())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery(ElasticSearchEntity.UPDATE_DATE).gte(queryBean.getCreateDateStartTime()));
        }
        //设置数据是否被删除
        boolQueryBuilder.must(QueryBuilders.matchQuery(ElasticSearchEntity.EFFECT_FLAGE, queryBean.getIsDelete()).operator(Operator.AND));
        //设置排序关键字
        if (StringUtils.isNotEmptyWithNUllCheckStr(queryBean.getSortKey())) {
            //如果排除不为空则使用传来的排序
            //否则默认使用倒序排列
            if (null != queryBean.getSortMethod()) {
                searchSourceBuilder.sort(queryBean.getSortKey(), queryBean.getSortMethod());
            } else {
                searchSourceBuilder.sort(queryBean.getSortKey(), SortOrder.DESC);
            }
        }

        //组合两个查询条件两个条件直接可以用must和should组合
        if (fuzzyAttrList != null && fuzzyAttrList.size() > 0) {
            //在模糊匹配存在的情况下
            totalQueryBuilder.must(QueryBuilders.boolQuery().must(boolQueryBuilder).must(fuzzyQueryBuilder));
        } else {
            //在模糊匹配不存在的情况下
            totalQueryBuilder = boolQueryBuilder;
        }
        //设置查询条件
        searchSourceBuilder.query(totalQueryBuilder);
        //起始位置
        int beginSore = page.getPageSize() * page.getCurrentPage();
        //起始位置
        long endSocre = page.getPageSize() * (page.getCurrentPage() + 1);
        //页面大小
        searchSourceBuilder.size(page.getPageSize());
        //从第几个开始，从0开始
        searchSourceBuilder.from(beginSore);

        searchRequest.source(searchSourceBuilder);

        try {

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            //结构集
            SearchHits hits = searchResponse.getHits();
            //本次查询总条数
            long totalHits = hits.getTotalHits();
            page.setTotalSize(totalHits);

            //最大评分
            float maxScore = hits.getMaxScore();
            //在查询起始数小于页面总数的时候进行数据的拼装操作
            if (beginSore < totalHits) {

                //在有数据命中的情况下返回对应的参数
                if (totalHits > 0) {
                    resultList = new ArrayList<ElasticSearchQueryResultBean>();
                    for (int i = 0; i < hits.getHits().length; i++) {
                        dataMap = hits.getHits()[i].getSourceAsMap();
                        res = formatResult(dataMap);
                        res.setId(hits.getHits()[i].getId());
                        resultList.add(res);

                    }
                    page.setContentList(resultList);

                } else {
                    logger.info("在对参数{}，进行查询的时候未查询到任何结果", JsonUtil.beanToJson(queryBean));
                    page.setContentList(null);
                }

            } else {
                logger.error("查询起始位置异常，查询起始量为：{}，数据总量为{}", beginSore, page.getTotalSize());
            }

//            page.setTotalSize(totalHits);
            return page;
        } catch (Exception e) {
            logger.error("查询关键词为{}，属性为{},索引为{}的数据的时候查询失败，失败原因为：", key, JsonUtil.beanToJson(attrList), queryBean.getIndexName(), e);
            throw new ErrorException(ElasticsearchServiceDao.DATA_QUERY_ERROR, ElasticsearchServiceDao.DATA_QUERY_ERROR_MESSAGE);
        }
    }

    /**
     * 初始化client
     */
    @Autowired
    public void ElasticsearchServiceDaoImpl() {

        //在client为空的时候为项目建立client
        if (client == null) {
            logger.info("开始创建ES客户端");
            String path;
            String osName = System.getProperty("os.name");
            if (osName.toUpperCase().contains(BaseConfig.WIN_SYSTEM_NAME)) {
                //如果是win系统
                logger.debug("-----判断为win系统 {}-----", osName);

                path = ClassName.class.getClassLoader().getResource("elasticsearch.yaml").getPath();

            } else {
                path = System.getProperty("user.dir") + "/config/elasticsearch.yaml";
            }

            logger.debug("开始从如下路径加载配置文件：{}", path);

            Yaml yaml = new Yaml();
            try {
                ElasticsearchConfig elasticsearchConfig = yaml.loadAs(new FileReader(new File(path)), ElasticsearchConfig.class);
                List<HttpHost> httpHostList = new ArrayList<>();

                if (elasticsearchConfig.getNodes().size() > 0) {
                    elasticsearchConfig.getNodes().stream().forEach(elasticsearchNode -> {
                        httpHostList.add(
                                new HttpHost(elasticsearchNode.getHostName(),
                                             elasticsearchNode.getPort(),
                                             elasticsearchNode.getScheme()));
                    });
                    RestClientBuilder restClientBuilder = RestClient.builder(
                            httpHostList.toArray(new HttpHost[1])
                    );
                    client = new RestHighLevelClient(
                            restClientBuilder);
                }
            } catch (FileNotFoundException e) {
                logger.error("创建es客户端的时候出错，错误信息为", e);
            }
        }
    }

    /**
     * 关闭client
     *
     * @throws IOException 抛出IO异常
     */
    public void close() throws IOException {
        client.close();
        client = null;
        logger.debug("Elasticsearch connection was closed");
    }

    /**
     * 在线程结束之后关闭
     *
     * @throws Throwable 抛出线程异常
     */
    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private ElasticSearchQueryResultBean formatResult(Map<String, Object> dataMap) {
        ElasticSearchQueryResultBean res = new ElasticSearchQueryResultBean();
        res.setIndexName(((String) dataMap.get(ElasticSearchEntity.INDEX_NAME)));
        res.setOrMessage(((String) dataMap.get(ElasticSearchEntity.OR_MESSAGE)));
        res.setBusinessType(((String) dataMap.get(ElasticSearchEntity.BUSINESS_TYPE)));
        res.setMessage(((String) dataMap.get(ElasticSearchEntity.MESSAGE_V)));
        res.setUserId(((String) dataMap.get(ElasticSearchEntity.USER_ID)));
        res.setIsDelete(((boolean) dataMap.get(ElasticSearchEntity.EFFECT_FLAGE)));
        res.setAttrStr(((String) dataMap.get(ElasticSearchEntity.MESSAGE_ATTR)));
        res.setRegion(((String) dataMap.get(ElasticSearchEntity.REGIGON)));
        return res;
    }

    //根据游标进行的数据查询
    private void searchScroll() {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        try {
            SearchRequest searchRequest = new SearchRequest("order-*");
            searchRequest.scroll(scroll);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(matchQuery("cus_rsp_code", "800"));
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = null;

            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            String scrollId = searchResponse.getScrollId();
            System.out.println("size :" + searchResponse.getHits().getTotalHits());
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
                scrollId = searchResponse.getScrollId();
                System.out.println("scrollId:" + scrollId);
                searchHits = searchResponse.getHits().getHits();
                int count = 0;
                for (SearchHit hit : searchHits) {
                    // do something with the SearchHit
                    //System.out.println(hit.toString());
                    count++;
                }
                System.out.println("page:" + count);
            }

            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.addScrollId(scrollId);
            ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
            boolean succeeded = clearScrollResponse.isSucceeded();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
