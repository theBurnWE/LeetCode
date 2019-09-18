package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shcepp.shdippsvr.oauth.dto.OAuthReturnInfo;
import com.shcepp.shdippsvr.sys.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * 对两种Json工具进行性能测试
 *
 * @author BrunE
 * @date 2019-08-01 10:05
 **/
public class JosnTest {

    public static void main(String[] args) {
        OAuthReturnInfo oa;
        List<OAuthReturnInfo> oaliist = new ArrayList<>();
        List<Long> changeTime = new ArrayList<>();
        List<Long> withOutChangeTime = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            oa = new OAuthReturnInfo();
            oa.setAccessToken("1231231");
            oa.setErrorCode("1231231");
            oa.setErrorInfo("1231231");
            oa.setFlag("1231231");
            oa.setRedirectUri("1231231");
            oa.setRefreshToken("1231231");

            oaliist.add(oa);
        }
        try {
            for(int i =0;i<10;i++){
                changeTime.add(JosnTetsWithchange(oaliist));
                withOutChangeTime.add(  JosnTetsWithoutchange(oaliist));
            }
            OptionalDouble changeTimeVal = changeTime.stream().mapToLong(Long::longValue).average();
            OptionalDouble withOutChangeTimeVal = withOutChangeTime.stream().mapToLong(Long::longValue).average();

            System.out.println("变更"+changeTimeVal);
            System.out.println("不变更"+withOutChangeTimeVal);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static long JosnTetsWithchange(List<OAuthReturnInfo> oaliist) {
        long start = System.currentTimeMillis();

        oaliist.forEach(oaa -> {
            try {

               String ss = new ObjectMapper().writeValueAsString(oaa);

                OAuthReturnInfo student = new ObjectMapper().readValue(ss.getBytes(), OAuthReturnInfo.class);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long JosnTetsWithoutchange(List<OAuthReturnInfo> oaliist) {
        long start = System.currentTimeMillis();

        oaliist.forEach(oaa -> {
            try {

                JsonUtil.beanToJson(oaa);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        long end = System.currentTimeMillis();
        return end - start;
    }

}
