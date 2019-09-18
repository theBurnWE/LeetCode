//package util;
//
//import com.shcepp.shdippsvr.Application;
//import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
//import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
//import com.shcepp.shdippsvr.business.bean.elasticsearch.pojo.ProductItemPojo;
//import com.shcepp.shdippsvr.business.enums.BaseEnums;
//import com.shcepp.shdippsvr.business.enums.ProductCategoryType;
//import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
//import com.shcepp.shdippsvr.business.service.impl.RecommendedResourcesServiceImpl;
//import com.shcepp.shdippsvr.sys.util.DateUtil;
//import com.shcepp.shdippsvr.sys.util.JsonUtil;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * RecommendedResourcesServiceImpl Tester.
// *
// * @author <Authors name>
// * @version 1.0
// * @since <pre>���� 19, 2019</pre>
// */
//@RunWith(SpringRunner.class)
////配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
//@SpringBootTest(classes = Application.class)
//public class EsClientTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(RecommendedResourcesServiceImpl.class);
//
//    @Autowired
//    protected WebApplicationContext wac;
//
//    @Autowired
//    private ElasticsearchService elasticsearchService;
//    /**
//     * 模拟mvc测试对象
//     */
//    private MockMvc mockMvc;
//
//    @Before
//    public void before() throws Exception {
//
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
//    }
//
//    @After
//    public void after() throws Exception {
//    }
//
//    /**
//     * Method: queryHomePageRecommendedResources(RecommendedResourcesReqPojo pojo)
//     */
//    @Test
//    public void testQueryHomePageRecommendedResources() throws Exception {
//        //TODO: Test goes here...
//
//        List<BaseEnums> list = new ArrayList<>();
//        String categories = "03,01,02";
//        for (String str : categories.split(",")) {
//            list.add(ProductCategoryType.getEnumsByCode(str));
//        }
//
//        List<BaseEnums> list2 = new ArrayList<>();
//        String categories2 = "03,03";
//        for (String str : categories2.split(",")) {
//            list2.add(ProductCategoryType.getEnumsByCode(str));
//        }
//        ProductItemPojo product = new ProductItemPojo();
//        ShdippElasticSearchBean bean;
//
//        ElasticsearchPagination page = new ElasticsearchPagination();
//        page.setCurrentPage(0);
//        page.setPageSize(10);
//        bean = ShdippElasticSearchBean.Builder()
//                                      .queryKey("北京")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//        logger.info("1期待查询结果为：查询到数据" );
//        logger.info("1实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//        bean = ShdippElasticSearchBean.Builder()
//                                      .attrList(list)
//                                      .queryKey("北京")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("2期待查询结果为：不能查到数据" );
//        logger.info("2实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//        bean = ShdippElasticSearchBean.Builder()
//                                      .fuzzyAttrList(list)
//                                      .queryKey("北京")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("3期待查询结果为：查询到数据" );
//        logger.info("3实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//        bean = ShdippElasticSearchBean.Builder()
//                                      .attrList(list)
//                                      .queryKey("北京")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("4期待查询结果为：不能查到数据" );
//        logger.info("4实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//        bean = ShdippElasticSearchBean.Builder()
//                                      .attrList(list)
//                                      .queryKey("北京")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("5期待查询结果为：不能查到数据" );
//        logger.info("5实际查询结果为：{},", page.getContentList().size());
//
//        page.setContentList(new ArrayList());
//        bean = ShdippElasticSearchBean.Builder()
//                                      .fuzzyAttrList(list)
//                                      .queryKey("北京")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("6期待查询结果为：查询到数据" );
//        logger.info("6实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//        bean = ShdippElasticSearchBean.Builder()
//                                      .createDateStartTime("2019-01-01")
//                                      .queryKey("武当山")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("7期待查询结果为：查询到数据" );
//        logger.info("7实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//        bean = ShdippElasticSearchBean.Builder()
//                                      .createDateStartTime("2019-01-01")
//                                      .createDateEndTime("2020-01-01")
//                                      .queryKey("武当山")
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("8期待查询结果为：查询到数据" );
//        logger.info("8实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//
//        bean = ShdippElasticSearchBean.Builder()
//                                      .queryKey("北京")
//                                      .fuzzyAttrList(list)
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("9期待查询结果为：查询到数据" );
//        logger.info("9实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//        bean = ShdippElasticSearchBean.Builder()
//                                      .queryKey("北京")
//                                      .fuzzyAttrList(list2)
//                                      .build();
//
//        page = elasticsearchService.queryEsByBean(bean, page);
//
//        logger.info("10期待查询结果为：不能查到数据" );
//        logger.info("10实际查询结果为：{},", page.getContentList().size());
//        page.setContentList(new ArrayList());
//
//    }
//
//}
