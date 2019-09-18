package com.shcepp.shdippsvr.business.util;

import com.shcepp.shdippsvr.sys.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zkmao
 * @description
 * @date 2019/9/10 16:07
 */
public class HtmlUtil {
    /**
     * 从html中提取纯文本
     *
     * @param strHtml
     * @return
     * @author
     * @date: 2018年8月3日 上午9:14:27
     */
    public static String StripHT(String strHtml) {
        if(StringUtils.isBlank(strHtml)) {
            return "";
        }
        String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); // 剔出<html>的标签
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");// 去除字符串中的空格,回车,换行符,制表符
        return txtcontent;
    }

    /**
     * 从HTML中提取图片链接
     *
     * @param content
     * @return
     * @author
     * @date: 2018年8月3日 上午9:08:09
     */
    public static List<String> extractImg(String content) {
        List<String> srcList = new ArrayList<String>(); // 用来存储获取到的图片地址
        if(StringUtils.isBlank(content)) {
            return srcList;
        }
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");// 匹配字符串中的img标签
        Matcher matcher = p.matcher(content);
        boolean hasPic = matcher.find();
        if(hasPic == true)// 判断是否含有图片
        {
            while(hasPic) // 如果含有图片，那么持续进行查找，直到匹配不到
            {
                String group = matcher.group(2);// 获取第二个分组的内容，也就是 (.*?)匹配到的
                Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");// 匹配图片的地址
                Matcher matcher2 = srcText.matcher(group);
                if(matcher2.find()) {
                    srcList.add(matcher2.group(3));// 把获取到的图片地址添加到列表中
                }
                hasPic = matcher.find();// 判断是否还有img标签
            }

        }
        return srcList;
    }

}
