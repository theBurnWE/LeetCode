package originTest.util;

import com.shcepp.shdippsvr.sys.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class StringTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "45634", "7892", "abch", "sdfhrthj", "mvkd");
        int i=1;
        list.forEach(e ->{
            if(StringUtils.isBlank(e)){
                System.out.println( e );
            }
        });
        String s = null;
        if(StringUtils.isBlank(s)){
            System.out.println( s );
        }
        System.out.println("wbh.text");
    }


}
