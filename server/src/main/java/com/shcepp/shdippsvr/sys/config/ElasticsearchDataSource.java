package com.shcepp.shdippsvr.sys.config;

import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchRowMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ElasticSearch 客户端
 * 1. transform elasticsearch response to Object
 * 2. package some elasticSearch rest function e.g. search , bulk , multi search, save document,update document
 *
 * @author kzhou
 * @date2018/11/19
 */
public class ElasticsearchDataSource {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchDataSource.class);

    RestHighLevelClient client;
    ElasticsearchConfig elasticsearchConfig;

    public ElasticsearchDataSource(ElasticsearchConfig elasticsearchConfig) {
        this.elasticsearchConfig = elasticsearchConfig;
        connection();
    }

    public RestHighLevelClient connection() {
        if (client == null) {
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
                logger.debug("connect Elasticsearch server({}) success", elasticsearchConfig.toString());
            }
        }
        return client;
    }

    public RestHighLevelClient getClient() {
        return client;
    }

    public Object search(SearchRequest searchRequest, ElasticsearchRowMapper<Object> rowMapper) throws IOException {
        return search(searchRequest, RequestOptions.DEFAULT, rowMapper);
    }

    ;

    public Object search(SearchRequest searchRequest, RequestOptions options, ElasticsearchRowMapper<Object> rowMapper) throws IOException {
        SearchResponse searchResponse = this.client.search(searchRequest, options);
        return rowMapper.mapRow(searchResponse);
    }

    ;

    public ElasticsearchPagination searchPagination(SearchRequest searchRequest, ElasticsearchRowMapper<List> rowMapper) throws IOException {
        return searchPagination(searchRequest, RequestOptions.DEFAULT, rowMapper);
    }

    ;

    public ElasticsearchPagination searchPagination(SearchRequest searchRequest, RequestOptions options, ElasticsearchRowMapper<List> rowMapper)
            throws IOException {
        SearchResponse searchResponse = this.client.search(searchRequest, options);
        int beginIndex = searchRequest.source().from();
        int pageSize = searchRequest.source().size();
        int pageNo = beginIndex / pageSize + 1;
        ElasticsearchPagination pagination = new ElasticsearchPagination();
        pagination.setContentList(rowMapper.mapRow(searchResponse.getHits().getHits()));
        pagination.setTotalSize(searchResponse.getHits().getTotalHits());
        pagination.setPageSize(searchResponse.getHits().getHits().length);
        pagination.setCurrentPage(pageNo);
        return pagination;
    }

    ;

    public boolean clearScroll(String... scrollIds) throws IOException {
        return clearScroll(RequestOptions.DEFAULT, scrollIds);
    }

    public boolean clearScroll(RequestOptions options, String... scrollIds) throws IOException {
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        for (String scrollId : scrollIds) {
            clearScrollRequest.addScrollId(scrollId);
        }
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, options);
        return clearScrollResponse.isSucceeded();
    }

    public void close() throws IOException {
        client.close();
        client = null;
        logger.debug("Elasticsearch connection was closed");
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
