//import com.shcepp.shdippsvr.Application;
////import com.shcepp.shdippsvr.business.dao.EasipayBatchOrderDao;
////import com.shcepp.shdippsvr.business.entity.EasipayBatchOrderEntity;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.List;
//
///**
// * 单元测试主类
// *
// * @author BrunE
// * @date 2019-05-29 17:56
// **/
//@SpringBootApplication
//@EnableScheduling
//@ComponentScan(basePackages = "com.shcepp.*")
////这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
//@RunWith(SpringJUnit4ClassRunner.class)
////这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
//@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
//public class JunitTestMain {
//
////    @Autowired
////    private EasipayBatchOrderDao easipayBatchOrderDao;
//
//    @Test
//    public void getPass() {
////        List<EasipayBatchOrderEntity> TESTLIST = easipayBatchOrderDao.queryOrderDetail(1539082L, 1L);
////        System.out.println("-----------WBH----------");
////        for (EasipayBatchOrderEntity order : TESTLIST) {
////            System.out.println("11111111111");
////            System.out.println(order.toString());
////            System.out.println("22222222222");
////        }
////        System.out.println("-----------WBH----------");
//    }
//
//}
