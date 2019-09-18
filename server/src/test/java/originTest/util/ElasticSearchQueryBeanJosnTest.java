//package util;
//
//import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
//import com.shcepp.shdippsvr.business.enums.BaseEnums;
//import com.shcepp.shdippsvr.business.enums.ModuleType;
//import com.shcepp.shdippsvr.sys.util.JsonUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Json的不序列化操作
// *
// * @author BrunE
// * @date 2019-08-01 10:05
// **/
//public class ElasticSearchQueryBeanJosnTest {
//
//    public static void main(String[] args) {
//
//        ShdippElasticSearchBean bean = new ShdippElasticSearchBean();
//
//        List<BaseEnums> emList =  new ArrayList<>();
//
//        emList.add(ModuleType.MT_02);
//        bean.setId(123);
//        bean.setMessage("setMessage");
//        bean.setOrMessage("setOrMessage");
//        bean.setAttributes("setAttributes");
//        bean.setCreateTime("setEffectDate");
//        bean.setUpdateTime("setEffectDate");
//        bean.setUserID("123123");
//        bean.setIsDelete(true);
//
//        System.out.println(JsonUtil.beanToJson(bean));
//        System.out.println(emList.get(0).toString());
//
//        System.out.println(emList.get(0).toString().hashCode());
//    }
//
//}
