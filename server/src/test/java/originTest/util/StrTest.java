package originTest.util;

import com.shcepp.shdippsvr.sys.util.StringUtils;

public class StrTest {

    public static void main(String[] args) {
      String[]ss= new String[]{"",null,new String (),new String(""),"null","王炳辉","到我就没了测试","thisisatesh"};

        for (String s : ss) {
//            System.out.println(StringUtils.isNotEmptyWithNUllCheckStr(s));

            System.out.println(StringUtils.getLegalProfile(s,5));
        }
    }


}