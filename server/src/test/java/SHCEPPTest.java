//import com.shcepp.shdippsvr.business.bean.easipay.req.EasipayBatchApplyReqBean;
//import com.shcepp.shdippsvr.business.bean.easipay.req.EasipayBatchApplyReqDetailBean;
//import com.shcepp.shdippsvr.business.bean.http.RequestParamBean;
//import com.shcepp.shdippsvr.business.httputil.IHttpUtil;
//import com.shcepp.shdippsvr.business.httputil.impl.SHCEPPFormatUtil;
//import com.shcepp.shdippsvr.business.util.FastDateFormateUtil;
//import com.shcepp.shdippsvr.sys.util.SnowflakeIdWorkerUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class SHCEPPTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(SHCEPPTest.class);
//
//    public static void main(String[] args) {
//        /**
//         * 声明发送器用以驱动测试类
//         */
//        IHttpUtil httpUtil = new SHCEPPFormatUtil();
//        //发送类的父类
//        RequestParamBean bean = new RequestParamBean();
//        // 初始化请求类
//        // 注意请求类的继承关系
//        //所有的交互内容消息请放在meaasge对象中，目前gateway还有点不自由的地方，先这样处理
//
//        EasipayBatchApplyReqBean upfPreArrivalFundResponseForm = new EasipayBatchApplyReqBean();
//        List<EasipayBatchApplyReqDetailBean> orderList = new ArrayList<>();
//        EasipayBatchApplyReqDetailBean detailBean;
//        upfPreArrivalFundResponseForm.setSerialNumber(String.valueOf(SnowflakeIdWorkerUtil.nextId()));
//
//        upfPreArrivalFundResponseForm.setCommitTime(FastDateFormateUtil.formatDate(FastDateFormateUtil.timePattern2,
//                                                                                   new Date(System.currentTimeMillis())));
//
//        upfPreArrivalFundResponseForm.setNotifyUrl("http://uat1.easipay.net/ppat/transcheck.shtml");
//        upfPreArrivalFundResponseForm.setPlatCode("EASIPAY");
//        upfPreArrivalFundResponseForm.setAllOrderTotal("7");
//        for (int i = 0; i < 7; i++) {
//
//            detailBean = new EasipayBatchApplyReqDetailBean();
//            detailBean.setOrderId(String.valueOf(SnowflakeIdWorkerUtil.nextId()));
//            detailBean.setOrderAmount("144.44");
//            detailBean.setLogisticsNo(String.valueOf(SnowflakeIdWorkerUtil.nextId()));
//            detailBean.setEbcName("淘宝");
//            detailBean.setEbcUSCI("NW883W2001");
//            detailBean.setEbcCode("NW883W2001");
//            detailBean.setEbpName("淘宝");
//            detailBean.setEbpCode("2222222222");
//            detailBean.setEbpUSCI("222222222222222222");
//            detailBean.setSpt1(String.valueOf(SnowflakeIdWorkerUtil.nextId()));
//            detailBean.setSpt2(String.valueOf(SnowflakeIdWorkerUtil.nextId()));
//            detailBean.setSpt3(String.valueOf(SnowflakeIdWorkerUtil.nextId()));
//            orderList.add(detailBean);
//
//        }
//        upfPreArrivalFundResponseForm.setOrderList(orderList);
//
//        bean.setMagicObject(upfPreArrivalFundResponseForm);//inf的eData部分
//        bean.setNotifyUrl("http://uat1.easipay.net/ppat/transcheck.shtml");
//        //订单申报
//        bean.setUrl("http://192.168.4.177:9099/shdippsvr/UPF/040800/v1.0");
//        //测试环境地址
//        bean.setUrl("http://test.shcepp.com:18080/shdippsvr/UPF/040800/v1.0");
//        //结果发送地址
////        bean.setUrl("http://192.168.4.177:9099/shdippsvr/quartzScheduler/batchOrde/sendResult/v1.0");
//        //获取结果地址值
////        bean.setUrl("http://192.168.4.177:9099/shdippsvr/quartzScheduler/batchOrde/check/v1.0");
//
//
//
//        bean.setTrxCode("123123");//inf的trxCode部分
////        bean.setNotifyUrl("123");//inf的notifyUrl部分
//        bean.setVersion("44444");//inf的version部分
////        bean.setSttCode("SHCEPP");//PropertiesUtil.get("PLATCODE.SHCEPP");//
//
//        try {
//            String httpResponse = httpUtil.sendRequest(bean);
//
//            //处理同步结果
//            logger.error(" 回执为： {}: ", httpResponse);
//        } catch (Exception e) {
//            logger.error(" error: ", e);
//
//        }
//
//    }
//}
//
//
//
