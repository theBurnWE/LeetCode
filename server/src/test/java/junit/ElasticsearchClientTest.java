//package junit;
//
//import com.shcepp.shdippsvr.sys.config.ElasticsearchConfig;
//import com.shcepp.shdippsvr.sys.util.FileReadHelper;
//import com.shcepp.shdippsvr.sys.util.JsonUtil;
//import com.shcepp.shdippsvr.sys.util.SerialNumberUtil;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.*;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.Scroll;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.junit.Before;
//import org.junit.Test;
//import org.yaml.snakeyaml.Yaml;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
//
///**
// * Elasticsearch client test
// *
// * @author kzhou
// * @date2018/10/23
// */
//public class ElasticsearchClientTest {
//
//    private static String INDEX_NAME = "shdipp-rtxt";
//    private RestHighLevelClient client;
//
//    @Before
//    public void init() {
//        File file = new File(this.getClass().getResource("/").getPath());
//
//        String path = file.getParent() + "/recommended/elasticsearch.yaml";
//        Yaml yaml = new Yaml();
//        try {
//            ElasticsearchConfig elasticsearchConfig = yaml.loadAs(new FileReader(new File(path)), ElasticsearchConfig.class);
//            List<HttpHost> httpHostList = new ArrayList<>();
//
//            if (elasticsearchConfig.getNodes().size() > 0) {
//                elasticsearchConfig.getNodes().stream().forEach(elasticsearchNode -> {
//                    httpHostList.add(
//                            new HttpHost(elasticsearchNode.getHostName(),
//                                         elasticsearchNode.getPort(),
//                                         elasticsearchNode.getScheme()));
//                });
//                RestClientBuilder restClientBuilder = RestClient.builder(
//                        httpHostList.toArray(new HttpHost[1])
//                );
//                client = new RestHighLevelClient(
//                        restClientBuilder);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //
////    //创建index
//    @Test
//    public void createIndex() {
////        return;
//        //建立索引
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest("shdipp-rtxt-1");
//        //分片
//        // 备份数量
//        //是否可写
//        createIndexRequest.settings(Settings.builder()
//                                            .put("index.number_of_shards", 5)
//                                            .put("index.number_of_replicas", 1)
//                                            .put("index.blocks.write", "false")
//        );
//
//        File file = new File(this.getClass().getResource("/").getPath());
//
//        String path = file.getParent() + "/recommended/ESFormateconfig.txt";
//        String indexConfig = FileReadHelper.readFileByBytes(path);
//        //定义结构
//        //_doc是文本概念
//        //默认值
//        createIndexRequest.mapping("_doc",
//                                   indexConfig,
//                                   XContentType.JSON);
//        //取别名
////        createIndexRequest.alias(new Alias("twitter_alias").filter(QueryBuilders.termQuery("user", "kimchy")));
//
//        try {
//            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//            boolean acknowledged = createIndexResponse.isAcknowledged();
//            boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
////
////    //删index
////    @Test
////    public void removeIndex() {
////        try {
////            DeleteIndexRequest request = new DeleteIndexRequest("twitter_bak");
////            DeleteIndexResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);
////            boolean acknowledged = deleteIndexResponse.isAcknowledged();
////            System.out.println(acknowledged);
////        } catch (ElasticsearchException exception) {
////            if (exception.status() == RestStatus.NOT_FOUND) {
////
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    //index 是否存在
//    @Test
//    public void existsIndex() {
//        GetIndexRequest request = new GetIndexRequest();
//        request.indices(INDEX_NAME);
//        try {
//            client.info();
//            boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
//            System.out.println("exists index:" + exists);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
////
////    //打开index
////    @Test
////    public void openIndex() {
////        OpenIndexRequest request = new OpenIndexRequest("twitter");
////        try {
////            OpenIndexResponse openIndexResponse = client.indices().open(request, RequestOptions.DEFAULT);
////            boolean acknowledged = openIndexResponse.isAcknowledged();
////            System.out.println("acknowlodged:" + acknowledged);
////            boolean shardsAcked = openIndexResponse.isShardsAcknowledged();
////            System.out.println("shards acknowlodged:" + shardsAcked);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    //关闭index
////    @Test
////    public void closeIndex() {
////        CloseIndexRequest request = new CloseIndexRequest("twitter");
////        try {
////            CloseIndexResponse closeIndexResponse = client.indices().close(request, RequestOptions.DEFAULT);
////            boolean acknowledged = closeIndexResponse.isAcknowledged();
////            System.out.println("acknowlodged:" + acknowledged);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    //收缩index
////    @Test
////    public void shrinkIndex() {
////        ResizeRequest request = new ResizeRequest("twitter_bak", "twitter");
////        request.getTargetIndexRequest().settings(Settings.builder()
////                .put("index.number_of_shards", 1));
////        try {
////            ResizeResponse resizeResponse = client.indices().shrink(request, RequestOptions.DEFAULT);
////            boolean acknowledged = resizeResponse.isAcknowledged();
////            System.out.println("acknowlodged:" + acknowledged);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Test
////    public void refreshIndex() {
////        RefreshRequest request = new RefreshRequest("twitter");
////        try {
////            RefreshResponse refreshResponse = client.indices().refresh(request, RequestOptions.DEFAULT);
////            int totalShards = refreshResponse.getTotalShards();
////            int successfulShards = refreshResponse.getSuccessfulShards();
////            int failedShards = refreshResponse.getFailedShards();
////            DefaultShardOperationFailedException[] failures = refreshResponse.getShardFailures();
////            System.out.println("totalShards:" + totalShards + ";successfulShards:" + successfulShards + ";" +
////                    "failedShards:" + failedShards);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Test
////    public void getIndexMapping() {
////        GetMappingsRequest request = new GetMappingsRequest();
////        request.indices("twitter");
////        //request.types("doc");
////        try {
////            GetMappingsResponse getMappingResponse = client.indices().getMapping(request, RequestOptions.DEFAULT);
////            ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> allMappings = getMappingResponse.mappings();
////            MappingMetaData typeMapping = allMappings.get("twitter").get("doc");
////            String mapContent = typeMapping.source().string();
////            System.out.print("map:" + mapContent);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Test
////    public void getIndexFieldsMapping() {
////        GetFieldMappingsRequest request = new GetFieldMappingsRequest();
////        request.indices("twitter");
////        request.fields("message", "user");
////        //request.types("doc");
////        try {
////            GetFieldMappingsResponse response = client.indices().getFieldMapping(request, RequestOptions.DEFAULT);
////            final Map<String, Map<String, Map<String, GetFieldMappingsResponse.FieldMappingMetaData>>> mappings =
////                    response.mappings();
////            final Map<String, GetFieldMappingsResponse.FieldMappingMetaData> typeMappings =
////                    mappings.get("twitter").get("doc");
////            final GetFieldMappingsResponse.FieldMappingMetaData metaData =
////                    typeMappings.get("user");
////
////            final String fullName = metaData.fullName();
////            final Map<String, Object> source = metaData.sourceAsMap();
////            System.out.print("map:" + source.toString());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Test
////    public void putMapping() {
////        PutMappingRequest request = new PutMappingRequest("twitter");
////        request.type("doc");
////        request.source(
////                "{\n    " +
////                        "\"properties\":{\n" +
////                        "\"test\":{\n" +
////                        "   \"type\":\"text\"" +
////                        "}\n" +
////                        "}\n" +
////                        "}", XContentType.JSON);
////        try {
////            PutMappingResponse putMappingResponse = client.indices().putMapping(request, RequestOptions.DEFAULT);
////            boolean acknowledged = putMappingResponse.isAcknowledged();
////            System.out.println("acknowlodged:" + acknowledged);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Test
////    public void testCount() {
////        SearchRequest searchRequest = new SearchRequest("order");
////
////        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
////        searchSourceBuilder.size(0);
////
////        TermsAggregationBuilder aggregation = AggregationBuilders.terms("order_status_count").field("order_status.keyword");
////        aggregation.order(BucketOrder.count(false));
////        aggregation.size(Integer.MAX_VALUE);//todo
////
////        searchSourceBuilder.aggregation(aggregation);
////
////        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
////
////        searchRequest.source(searchSourceBuilder);
////        try {
////            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
////            System.out.println(searchResponse.toString());
////            RestStatus status = searchResponse.status();
////            TimeValue took = searchResponse.getTook();
////            Boolean terminatedEarly = searchResponse.isTerminatedEarly();
////            boolean timedOut = searchResponse.isTimedOut();
////            int totalShards = searchResponse.getTotalShards();
////            int successfulShards = searchResponse.getSuccessfulShards();
////            int failedShards = searchResponse.getFailedShards();
////            for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
////                // failures should be handled here
////            }
////            Aggregations aggregations = searchResponse.getAggregations();
////            Terms byCompanyAggregation = aggregations.get("order_status_count");
////            byCompanyAggregation.getBuckets().stream().forEach(bucket -> {
////                System.out.println(bucket.getKeyAsString() + "\t count:" + bucket.getDocCount());
////            });
////
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
//
//    @Test
//    public void putMessage() {
//        //直接JOSN键值对就OK了
//        StringBuilder test = new StringBuilder("{\"message\":\"X01\",\"orMessage\":\"未来空间界城\"}");
//        String[] s = test.toString().split("##");
//        String ser=SerialNumberUtil.getReqSer();
//
//        System.out.println(ser);
//        BulkRequest bulkRequest = new BulkRequest();
//        int i = 201;
//        for (String x : s) {
//            //索引
//            //文档
//            //主键
//            IndexRequest indexRequest = new IndexRequest(INDEX_NAME, "_doc",ser) ;
//            //指定X为JSON格式
//            indexRequest.source(x, XContentType.JSON);
//            //打包
//            bulkRequest.add(indexRequest);
//        }
//
//        try {
//            //批量上传【同步上传】
//            //            client.bulkAsync();//异步上传
//            client.bulk(bulkRequest, RequestOptions.DEFAULT);
//            //关闭
//            client.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void search() {
//        //指定索引
//        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);//"wbh_test-2*"//可以输入正则表达式进行模匹配
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//
//        List<String >attrList =  new ArrayList<>();
//        //查询语句
////        boolQueryBuilder.must(matchQuery("message", "X01"));
////        boolQueryBuilder.must(matchQuery("message", "P02"));
////        attrList.add("X01");
////
////        if (attrList != null && attrList.size() > 0) {
////            attrList.forEach(f -> {
////                boolQueryBuilder.must(matchQuery(ElasticSearchQueryBean.MESSAGE_V, String.valueOf(f)));
////
////            });
////        }
//
//        boolQueryBuilder.must(matchQuery("user", "空间"));
//        searchSourceBuilder.query(boolQueryBuilder);
//        searchSourceBuilder.size(10);
//        searchRequest.source(searchSourceBuilder);
//
//        try {
//            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//            SearchHits hits = searchResponse.getHits();
//            long totalHits = hits.getTotalHits();
//            float maxScore = hits.getMaxScore();
//            System.out.println(String.format("total %d maxScore %f", totalHits, maxScore));
//            SearchHit[] searchHits = hits.getHits();
//
//            for (SearchHit hit : searchHits) {
//                // do something with the SearchHit
////                hit.getSourceAsMap();
//                System.out.println(JsonUtil.beanToJson(hit.getSourceAsMap()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
////
////    @Test
////    public void searchJSON() {
////        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
////        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
////
////        String json = "{\n" +
////                "    \"bool\": {\n" +
////                "      \"must\": [\n" +
////                "        {\"match\": {\n" +
////                "          \"_id\": \"106\"\n" +
////                "        }}\n" +
////                "      ]\n" +
////                "    }\n" +
////                "  }";
////        searchSourceBuilder.query(QueryBuilders.wrapperQuery(json));
////        searchRequest.source(searchSourceBuilder);
////
////        try {
////            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
////            SearchHits hits = searchResponse.getHits();
////            long totalHits = hits.getTotalHits();
////            float maxScore = hits.getMaxScore();
////            System.out.println(String.format("total %d maxScore %f", totalHits, maxScore));
////            SearchHit[] searchHits = hits.getHits();
////            for (SearchHit hit : searchHits) {
////                // do something with the SearchHit
////                System.out.println(hit.toString());
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
//    @Test
//    ////分页查询
//    //效率会高一些
//    public void searchScroll() {
//        //滚动数目
//        //一次查询1条
//        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
//        try {
//            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
//            searchRequest.scroll(scroll);
//            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            boolQueryBuilder.must(matchQuery("cus_rsp_code", "800"));
//            searchSourceBuilder.query(boolQueryBuilder);
//            searchRequest.source(searchSourceBuilder);
//
//            SearchResponse searchResponse = null;
//
//            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//            String scrollId = searchResponse.getScrollId();
//            System.out.println("size :" + searchResponse.getHits().getTotalHits());
//            SearchHit[] searchHits = searchResponse.getHits().getHits();
//
//            while (searchHits != null && searchHits.length > 0) {
//                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
//                scrollRequest.scroll(scroll);
//                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
//                scrollId = searchResponse.getScrollId();
//                System.out.println("scrollId:" + scrollId);
//                searchHits = searchResponse.getHits().getHits();
//                int count = 0;
//                for (SearchHit hit : searchHits) {
//                    // do something with the SearchHit
//                    //System.out.println(hit.toString());
//                    count++;
//                }
//                System.out.println("page:" + count);
//            }
//
//            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
//            clearScrollRequest.addScrollId(scrollId);
//            ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
//            boolean succeeded = clearScrollResponse.isSucceeded();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
