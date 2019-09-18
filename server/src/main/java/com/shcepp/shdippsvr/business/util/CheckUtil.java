package com.shcepp.shdippsvr.business.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串操作类
 */
public class CheckUtil {
    
    public static final String TYPE_PHONE = "phone";
    public static final String TYPE_EMAIL = "email";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_LIST = "list";
    
    /**
     * 检查email是否是邮箱格式，返回true表示是，反之为否
     */
    public static boolean isEmail(String email) {
        email = null == email ? "" : email;
        Pattern regex = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return isMatched;
    }
    
    public static boolean isPhone(String phone) {
        phone = null == phone ? "" : phone;
//        Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern regex = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher matcher = regex.matcher(phone);
        boolean isMatched = matcher.matches();
        return isMatched;
    }
    
    /**
     * 检查email是否是邮箱格式，返回true表示是，反之为否
     */
    public static boolean isNumber(String number) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]{0,2}");
        Matcher isNum = pattern.matcher(number);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    
    public static boolean isInteger(String number) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(number);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    
    public static void main(String args[]) {
        
        System.out.println(checkField("XXX", "12798098909", 1, 30, TYPE_PHONE));
        
        // System.out.println(isNumber("-12.343"));
        //
        // System.out.println(isNumber("1.e"));
        //
        // System.out.println(isNumber("4d"));
        //
        // System.out.println(isNumber("s12"));
    }
    
    public static String checkField(String fieldName,
                                    String value,
                                    int minLength,
                                    int maxLength,
                                    String type,
                                    List<String> checkList) {
        if(TYPE_LIST.equals(type) && (checkList == null || checkList.size() == 0)) {
            return "checkList不得为空";
        }
        if (StringUtils.isEmpty(checkField(fieldName,
                                           value,
                                           minLength,
                                           maxLength,
                                           TYPE_STRING))) {
            if(!checkList.contains(value)) {
                return fieldName + "只支持" + checkList;
            }
        }
        return "";
    }
    
    /**
     * 
     * 字段校验
     *
     * @param fieldName
     * @param value
     * @param minLength
     * @param maxLength
     * @param type
     * @return
     */
    public static String checkField(String fieldName,
                                    String value,
                                    int minLength,
                                    int maxLength,
                                    String type) {
        
        if (null == value && minLength > 0) {
            return fieldName + "不能为空";
        }
        
        if (null != value && TYPE_PHONE.equals(type) && !isPhone(value)) {
            return fieldName + "手机号码格式错误";
        } else if (null != value && TYPE_EMAIL.equals(type) && !isEmail(value)) {
            return fieldName + "邮箱格式错误";
        } else if (null != value && TYPE_STRING.equals(type)) {
            
            if (value.length() < minLength) {
                return fieldName + "长度不得小于" + minLength;
            } else if (value.length() > maxLength) {
                return fieldName + "长度不得超过" + maxLength;
            }
        } else if (null != value && TYPE_NUMBER.equals(type)) {
            
            if (value.length() < minLength) {
                return fieldName + "长度不得小于" + minLength;
            } else if (value.length() > maxLength) {
                return fieldName + "长度不得超过" + maxLength;
            }
            if (!isNumber(value)) {
                return fieldName + "数字格式错误";
            }
        } else if (null != value && TYPE_INTEGER.equals(type)) {
            
            if (value.length() < minLength) {
                return fieldName + "长度不得小于" + minLength;
            } else if (value.length() > maxLength) {
                return fieldName + "长度不得超过" + maxLength;
            }
            if (!isInteger(value)) {
                return fieldName + "数字格式错误";
            }
        } else if (null != value
                && TYPE_NUMBER.equals(type)
                && !isNumber(value)) {
            return fieldName + "格式错误";
        }
        return "";
    }
}
