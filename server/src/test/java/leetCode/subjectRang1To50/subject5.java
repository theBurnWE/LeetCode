package leetCode.subjectRang1To50;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        List<String> list = Arrays.asList("cabbas", "abba", "abbc", "abbc", "abc", "aba", "abcb", "bb", "a");
        list = Arrays.asList("cbbd");

        list.forEach(f -> {
            System.out.println("----");

            System.out.println(f);

            System.out.println(longestPalindrome(f));
            System.out.println("----");


        });
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


            int length = expandAroundCenter(s, i, i);
            int length2 = expandAroundCenter(s, i, i + 1);
            //判断步长
            step = Math.max(length, length2);

            //如果步长大于当前最大步长
            if (step > max) {
                max = step;
                //左坐标：
                //在长度为奇数的时候求商会自动向下求整数
                //在长度为偶数的时候由于坐标为左坐标，所以在求出长度之后-1
                //在整合两个写法之后可以使用求商的向下求整数
                //右坐标：
                //在长度为奇数的时候由于自动向下求整数，所以不用对length做运算
                //在长度为偶数对时候由于当前坐标为左坐标，需要完整的长度以运算右端
                statr = i - ((step - 1) / 2);
                end = i + (step  / 2);

            }
        }

        res = s.substring(statr, end + 1);
        System.out.println("开始计算," + s + " 左坐标为：" + statr + " 右坐标为：" + (end + 1));


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
/**
 * 若满足s[i]==s[r]\ and\ dp[i+1][r-1]==Trues[i]==s[r] and dp[i+1][r−1]==True。表示子串s[i+1,...,r-1]s[i+1,...,r−1]为回文且s[i]==s[r]s[i]==s[r]，说明s[i,...,r]s[i,...,r]也是回文。则此时，更新dp[i][r]=Truedp[i][r]=True，并更新最长子串开始索引start=istart=i和长度max\_len=lmax_len=l
 * 返回s[start,...,start+max\_len-1]s[start,...,start+max_len−1]
 * <p>
 * 复杂度分析
 * 时间复杂度：O\left(n^{2}\right)O(n
 * 2
 * )
 * 空间复杂度：O(n^{2})O(n
 * 2
 * )
 * Python
 * class Solution:
 * def longestPalindrome(self, s: str) -> str:
 * if(not s or len(s)==1):
 * return s
 * n=len(s)
 * dp=[[False]*n for _ in range(n)]
 * max_len=1
 * start=0
 * for i in range(n):
 * dp[i][i]=True
 * if(i<n-1 and s[i]==s[i+1]):
 * dp[i][i+1]=True
 * start=i
 * max_len=2
 * for l in range(3,n+1):
 * for i in range(n+1-l):
 * r=i+l-1
 * if(s[i]==s[r] and dp[i+1][r-1]):
 * dp[i][r]=True
 * start=i
 * max_len=l
 * return s[start:start+max_len]
 * 中心拓展法
 * 回文串的中心可能是一个字符或者两个字符。因此，我们遍历每一个字符和每一对相邻的字符
 * <p>
 * 特判，若ss长度为11或00，返回ss
 * <p>
 * 初试化最长回文子串的开始索引和结束索引start=0,end=0start=0,end=0
 * <p>
 * 拓展函数expand(l,r)expand(l,r)，ll表示传入中心字符的左界，rr表示传入字符中心的右界。
 * <p>
 * 若满足条件0<=l\ and\ r<n\ and s[l]==s[r]0<=l and r<n ands[l]==s[r]，执行循环：向两侧拓展l-=1l−=1，r+=1r+=1
 * 返回回文串的长度r-l-1r−l−1。注意！为什么是r-l-1r−l−1?，若不满足循环条件，则最后结束时，ll多减了1，rr多加了1。另外一种情况，就是传入两个字符时，不满足循环条件，此时l+1-l-1=0l+1−l−1=0，表示不是回文串。
 * 遍历ss，遍历区间[0,n)[0,n)：
 * <p>
 * 中心为一个字符的拓展后长度len\_1=expand(i,i)len_1=expand(i,i)
 * 中心为两个字符的拓展后长度len\_2=expand(i,i+1)len_2=expand(i,i+1)
 * 较长的子串长度len\_long=max(len\_1,len\_2)len_long=max(len_1,len_2)
 * 若满足len\_long>end-start+1len_long>end−start+1，表示当前长度大于之前的最长长度，更新startstart和endend
 * start=i-(len\_long-1)//2start=i−(len_long−1)//2
 * end=i+len\_long//2end=i+len_long//2
 * 解释！start和end的更新公式
 * ////表示向下取整
 * 因为有两种情况，拓展中心为一个字符或者两个字符。无论是一个还是两个，令字符串长度(len-1)//2(len−1)//2总能得到拓展中心左侧元素的个数。同理，令len//2len//2总能得到拓展中心右侧的个数。
 * 返回s[start,...,end]s[start,...,end]
 * <p>
 * 复杂度分析
 * 时间复杂度：O\left(n^{2}\right)O(n
 * 2
 * )
 * 空间复杂度：O(1)O(1)
 */
