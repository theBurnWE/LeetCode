package com.shcepp.shdippsvr.sys.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件读取辅助类
 *
 * @author BrunE
 * @date 2019-08-01 17:15
 **/
public class FileReadHelper {

    public static String readFileByBytes(String fileName) {
        File file = new File(fileName);
        StringBuffer sb = new StringBuffer();
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            BufferedReader in = new BufferedReader(new FileReader(file));
            while (in.ready()) {
                sb = sb.append(new StringBuffer(in.readLine()));

            }
            System.out.println(sb);
            in.close();
            in.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
