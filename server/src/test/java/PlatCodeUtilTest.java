import com.shcepp.shdippsvr.business.util.RouterSelectUtil;

public class PlatCodeUtilTest {

    public static void main(String[] args) {
            String platCodeStrArray = RouterSelectUtil.getSubMappedPlatCode("PPX1004", "cpnr", "");
//            for(String ss :platCodeStrArray){
//                System.out.println("value is :"+ss);
//            }

        System.out.println(platCodeStrArray);
    }
}
