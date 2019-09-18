package com.shcepp.shdippsvr.sys.util;

import java.util.Random;

public class CaptchaUtil {
    /**
     * 随机生成验证码
     *
     * @param length
     * @return
     */
    public static String getCharacterAndNumber(int length, boolean onlyNum) {
        String value = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            String charOrNum = onlyNum ? "num"
                    : (random.nextInt(2) % 2 == 0 ? "char" : "num"); // 输出字母还是数字
            if("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                value += (char) (choice + random.nextInt(26));
            }
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                value += String.valueOf(random.nextInt(10));
            }
        }
        return value;
    }
}
