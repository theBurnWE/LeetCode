package leetCode.subjectRang1To50;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author BrunE
 * @date 2019-09-18 16:07
 **/
public class subject5_1 {
//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
//示例 1：
//
//输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//示例 2：
//
//输入: "cbbd"
//输出: "bb"

    private static final Logger logger = LoggerFactory.getLogger(subject5_1.class);

    //前值小于后值，减去前值
//前值大于或等于后值，加上前值
//最后一个值必然是加上的
    public static void main(String[] args) {

        List<String> list = Arrays.asList("cabbas", "abba", "abbc", "abbc", "abc", "aba", "abcb", "bb", "a");
//        list = Arrays.asList("abc");

        list.forEach(f -> {
            System.out.println("----");

            System.out.println(f);

            System.out.println(longestPalindrome(f));
            System.out.println("----");


        });
    }

    public  static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0 ,len=0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
             len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        System.out.println("开始计算,"+s+" 左坐标为：" + start + " 右坐标为：" + (end+1)+" 步长为："+(end - start));

        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}