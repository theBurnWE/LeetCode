package com.shcepp.shdippsvr.business.util;

import java.util.regex.Pattern;

/**
 * 身份证校验工具类
 *
 * @author BrunE
 * @date 2018-10-29 10:12
 **/
public class CardCheckUtils {
    //验证是否是身份证，先行校验通过
    public static boolean isIDCard(String idCard) {
        String REGEX_ID_CARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        return Pattern.matches(REGEX_ID_CARD, idCard);

    }
}
