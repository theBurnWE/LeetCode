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
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            // 如果窗口中包含当前字符，
            if (map.containsKey(s.charAt(right))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                left = Math.max(map.get(s.charAt(right)), left);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            max = Math.max(max, right - left + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(right), right +1);
        }
        return max;
    }
}
