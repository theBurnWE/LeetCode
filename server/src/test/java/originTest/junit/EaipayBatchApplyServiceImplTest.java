//package junit;
//
//import com.shcepp.shdippsvr.Application;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * EaipayBatchApplyServiceImpl Tester.
// *
// * @author <Authors name>
// * @version 1.0
// * @since <pre>���� 31, 2019</pre>
// */
//
//@RunWith(SpringRunner.class)
////配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
//@SpringBootTest(classes = Application.class)
//public class EaipayBatchApplyServiceImplTest {
//
//    @Autowired
//    protected WebApplicationContext wac;
//    /**
//     * 模拟mvc测试对象
//     */
//    private MockMvc mockMvc;
//
//    @Before
//    public void before() throws Exception {
//
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
//
//    }
//
//    @After
//    public void after() throws Exception {
//    }
//
//    /**
//     * Method: onMessage(EasipayBatchApplyReqBean reqBean)
//     */
//    @Test
//    public void testOnMessage() throws Exception {
////TODO: Test goes here...
//        String res = mockMvc
//                .perform(// 1
//                         MockMvcRequestBuilders.post("/quartzScheduler/batchOrde/check/v1.0") // 2
//                                               .param("name", "getList")        // 3
//                ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())         //打印出请求和相应的内容
//                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
//
//
//        MvcResult mvcResult = mockMvc
//                .perform(// 1
//                         MockMvcRequestBuilders.post("/quartzScheduler/batchOrde/check/v1.0") // 2
//                                               .param("name", "getList")        // 3
//                ).andReturn();
//        int status = mvcResult.getResponse().getStatus(); // 5
//        String responseString = mvcResult.getResponse().getContentAsString(); // 6
//
//        Assert.assertEquals("请求错误", 200, status); // 7
//        Assert.assertEquals("返回结果不一致", "", responseString); // 8
//
//    }
//
//    /**
//     * Method: dealBatchApplyCheckoutResultToEasipay()
//     */
//    @Test
//    public void testDealBatchApplyCheckoutResultToEasipay() throws Exception {
////TODO: Test goes here...
//    }
//
//    /**
//     * Method: sendBatchApplyCheckoutResultToEasipay(UpfBatchApplyEntity batchApplyEntity)
//     */
//    @Test
//    public void testSendBatchApplyCheckoutResultToEasipay() throws Exception {
////TODO: Test goes here...
//    }
//
//}
