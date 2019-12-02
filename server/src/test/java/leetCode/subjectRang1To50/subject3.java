package leetCode.subjectRang1To50;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BrunE
 * @date 2019-09-18 16:07
 **/
public class subject3 {

    private static final Logger logger = LoggerFactory.getLogger(subject3.class);


    public static void main(String[] args) {
        lengthOfLongestSubstring03("12312312312");

    }
        public int lengthOfLongestSubstringv1(String s) {
        Map<String,String>map = new HashMap();
        int len=0;
        char[] ss =s.toCharArray();
        for (char c : ss) {
//            if ()
        }

        return len;

    }
    public  static  int lengthOfLongestSubstring03(String s) {
        int left = 0;
        int max=0;
        //创建map窗口,滑动窗口的右下标
        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            // 如果窗口中包含当前字符，
            if (map.containsKey(s.charAt(right))) {
                //计算 左边界移动
                left = Math.max(map.get(s.charAt(right)), left);
            }
            //计算当前最大值
            max = Math.max(max, right - left + 1);

            // 将当前字符为key，下一个索引为value放入map中
            map.put(s.charAt(right), right +1);
        }
        return max;
    }
}
