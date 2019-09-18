//package util;
//
//import com.shcepp.shdippsvr.Application;
//import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
//import com.shcepp.shdippsvr.business.service.BaseService;
//import com.shcepp.shdippsvr.business.service.impl.RecommendedResourcesServiceImpl;
//import com.shcepp.shdippsvr.sys.util.ApiResult;
//import com.shcepp.shdippsvr.sys.util.JsonUtil;
//import com.shcepp.shdippsvr.sys.util.SerialNumberUtil;
//import org.junit.After;
//import org.junit.Assert;
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
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
//public class RecommendedResourcesServiceImplTest {
//    private static final Logger logger = LoggerFactory.getLogger(RecommendedResourcesServiceImpl.class);
//
//    @Autowired
//    protected WebApplicationContext wac;
//
//    @Autowired
//    private RecommendedResourcesServiceImpl recommendedResourcesServiceImpl;
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
////TODO: Test goes here...
//
//
//        RecommendedResourcesReqPojo pojo = new RecommendedResourcesReqPojo();
//        ApiResult res ;
//        pojo.setModuleTypeArray("00");
//
//        pojo.setLanCode("EN");
//        pojo.setDominCode("01");
//        pojo.setRecommendationStatus("1");
//        res =  recommendedResourcesServiceImpl.queryHomePageRecommendedResources(pojo);
//        logger.info(JsonUtil.beanToJson(res));
//        //打印值，期望值，真实值
//        Assert.assertEquals("123", BaseService.BR_SUCCESS, res.getReturnCode());
//
//
//    }
//
//
//
//}
