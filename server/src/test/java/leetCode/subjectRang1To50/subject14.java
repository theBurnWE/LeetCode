package leetCode.subjectRang1To50;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author BrunE
 * @date 2019-09-18 16:07
 **/
public class subject14 {

    private static final Logger logger = LoggerFactory.getLogger(subject14.class);

//由1算其他
    public static void main(String[] args) {

    }
    public  static String longestCommonPrefix(String[] strs) {
        String res="";
        boolean dealflag= true;
        if(strs.length > 1){

            String key =strs[0];
            for(int i=0;i<key.length();i++){
                char ss= key.charAt(i);
                for(int k=1;k<strs.length ;k++){
                    if (strs[k].length() -1 < i  ||!(strs[k].charAt(i)==ss)){
                        dealflag=!dealflag;
                        break;
                    }
                }
                if(dealflag) {
                    res=res+ss;
                }else {
                    break;
                }

            }

        }else
        {
            res = strs.length==0?"":strs[0];
        }
        return res;}

}
