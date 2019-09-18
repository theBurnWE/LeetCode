package com.shcepp.shdippsvr.sys.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BrunE
 * @date 2017-09-11 11:10
 **/
public class StringUtils extends org.springframework.util.StringUtils {

    public static final String BLANK = "";

    public static final String DEFAULT_CHARSET = "utf-8";

    public static final String DOT = ".";

    public static String firstCharUpperCase(String src) {
        if (Character.isUpperCase(src.charAt(0))) {
            return src;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(Character.toUpperCase(src.charAt(0))).append(
                    src.substring(1));
            return sb.toString();
        }
    }

    public static String[] match(String src, String reg) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(src);
        List<String> l = new ArrayList<String>();
        while (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                l.add(m.group(i));
            }
        }
        return l.toArray(new String[] {});
    }

    public static String matchGroup(String src, String reg, int index) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(src);
//		List<String> l = new ArrayList<String>();
        while (m.find()) {
            return m.group(index);
        }
        return BLANK;
    }

    public static String[] matchAll(String src, String reg) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(src);
        List<String> l = new ArrayList<String>();
        while (m.find()) {
            l.add(m.group());
        }
        return l.toArray(new String[] {});
    }

    public static String stringtify(Reader in) {
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            BufferedReader br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static String stringtify(InputStream in) {
        return stringtify(in, DEFAULT_CHARSET);
    }

    public static String stringtify(InputStream in, String charset) {
        try {
            InputStreamReader isr = new InputStreamReader(in, charset);
            return stringtify(isr);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * 字符串前补零
     *
     * @param src       源
     * @param padLength 字符格式化位数
     * @return 返回补零后的结果
     */
    public static String lpad(String src, Integer padLength) {
        StringBuffer sb = new StringBuffer();
        int length = padLength - src.length();
        for (int i = 0; i < length; i++) {
//            src = "0" + src;
            sb = sb.append("0").append(src);
        }
        return sb.toString();
    }

    /**
     * 比较是否含有给定字符串
     *
     * @param src 源
     * @param reg 正则
     * @return
     */
    public static boolean matches(String src, String reg) {
        return Pattern.compile(reg).matcher(src).find();
    }

    /**
     * 过滤字符串中的换行符和制表符
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    /**
     * 随机生成6位验证码
     *
     * @param length 字符的长度
     * @return 生产的字符串
     */
    public static String genRandomPsd(int length) {
        Random rad = new Random();
        String result = rad.nextInt(1000000) + "";
        if (result.length() != length) {
            return genRandomPsd(length);
        }
        return result;
    }

    public static String valueOf(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return "";
    }

    public static boolean isEmpty(Object str) {
        return str == null || valueOf(str).length() == 0;
    }

    /**
     * 对字符串进行包含对非null字符的非空判断
     */
    public static boolean isNotEmptyWithNUllCheckStr(String str) {
        return
                (null != str && !"".equals(str) && !"null".equals(str) && !(valueOf(str).length() == 0));
    }

    /**
     * 对字符串进行包含对非null字符的非空判断
     */
    public static boolean isEmptyWithNUllCheckStr(String str) {
        return !isNotEmptyWithNUllCheckStr(str);
    }

    /**
     * 对字符进行按长度的截取
     */
    public static String getLegalProfile(String str, int length) {
        //为空时候的处理
        if (isEmptyWithNUllCheckStr(str)) {
            return "";
        } else if (str.length() > length) {
            return str.substring(0, length);
        } else {
            return str;
        }
    }

    public static String[] removeNull(String[] str) {
        List<String> strListNew = new ArrayList<>();

        Arrays.stream(str).forEach(f -> {
            if (isNotEmptyWithNUllCheckStr(f)) {
                strListNew.add(f);
            }
        });

        return strListNew.toArray(new String[strListNew.size()]);
    }
}
