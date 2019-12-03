package leetCode.subjectRang1To50;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author BrunE
 * @date 2019-09-18 16:07
 **/
public class subject5 {
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

    private static final Logger logger = LoggerFactory.getLogger(subject5.class);

    //前值小于后值，减去前值
//前值大于或等于后值，加上前值
//最后一个值必然是加上的
    public static void main(String[] args) {
        System.out.println("++" + longestPalindrome("cbbd"));

    }

    public static String longestPalindrome(String s) {
        String res = "";
        //异常数据处理
        if (StringUtils.isEmpty(s)) {
            return res;
        }
        int max = 1;
        //锚点从1开始，步长和锚点相同
        //锚点和步长计算核算
        int statr = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int step = 0;

            System.out.println("开始计算," + s.charAt(i) + "坐标为：" + i);

            int length = expandAroundCenter(s, i, i);
            int length2 = expandAroundCenter(s, i, i + 1);
            //判断步长
            step = Math.max(length, length2);

            //如果步长和当前最大步长相同
            if (step > max) {
                max = step;
                //判断起止坐标
                //双中心
                if(length2==max){
                    statr = i - length / 2;

                    end = i + length / 2+1 ;
                }else {
                    statr = i - length / 2;

                    end = i + length / 2 ;
                }

            }
        }
        //左闭右开
        res = s.substring(statr, end+1);
        //得到字串
        return res;

    }

    //判断当前锚点下最大步长
    private static int expandAroundCenter(String s, int keypoint, int right) {
        int start = keypoint;
        int end = right;

        //在锚点左右都相等的话
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        //计算步长//
        //由于下标从0开始，减法的时候需要减去错误运算，加上1因为下标从0开始
//        return end - start - 2+1;
        return end - start - 1;

    }


}
