package leetCode.subjectRang1To50;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BrunE
 * @date 2019-09-18 16:07
 **/
public class subject6 {
    //将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
    //也就是对自负进行n字型变幻
    private static final Logger logger = LoggerFactory.getLogger(subject6.class);

    //前值小于后值，减去前值
//前值大于或等于后值，加上前值
//最后一个值必然是加上的
    public static void main(String[] args) {
        String string = "PAYPALISHIRING";
        int row = 3;
        String res = convert(string, row);
        System.out.println(res);
    }

    /**
     * 二维数组法，计算每个字符所在位置
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        //实现对二维数组的初始化
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {

            rows.add(new StringBuilder());
        }


        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            //在当前行中增加
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            //判断行数是否增加
            curRow += goingDown ? 1 : -1;
        }

        //输出二维数组
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();


    }

    /**
     * 下标计算法，直接计算下标所在位置
     * 对于所有整数 k，

     行 0 中的字符位于索引k(2⋅numRows−2) 处;
     行  numRows−1 中的字符位于索引 k(2⋅numRows−2)+numRows−1 处;
     内部的 行 ii 中的字符位于索引 k \; (2 \cdot \text{numRows}-2)+ik(2⋅numRows−2)+i 以及 (k+1)(2 \cdot \text{numRows}-2)- i(k+1)(2⋅numRows−2)−i 处;

     * @param s
     * @param numRows
     * @return
     */
    public static String convert2(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        //计算回行的行数
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();




    }

}
