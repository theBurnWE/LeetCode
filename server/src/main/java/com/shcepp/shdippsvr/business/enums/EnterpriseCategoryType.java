package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * Created by shcepp on 2019/9/10.
 */
public enum EnterpriseCategoryType implements BaseEnums{
    //01	数字视听平台
    EPCT_01("01", "云服务"),
    //02	游戏娱乐平台
    ECT_02("02", "数字视听"),
    //03	数字教育服务平台
    ECT_03("03", "游戏娱乐"),
    //04	云服务平台
    ECT_04("04", "数字阅读"),
    //05	信息技术服务平台
    ECT_05("05", "行业应用"),
    //06	跨境电商平台
    ECT_06("06", "跨境电商"),


    //  异常
    MT_99("99", "未匹配");

    private final String code;
    private final String value;

    EnterpriseCategoryType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static EnterpriseCategoryType getEnumsByCode(String code) {
        EnterpriseCategoryType[] baseTypes = values();
        for (EnterpriseCategoryType baseType : baseTypes) {
            if (baseType.code.equals(code)) {
                return baseType;
            }
        }
        return MT_99;
    }

    /**
     * 获取枚举类型值
     *
     * @param value value值
     * @return value值
     */
    public static EnterpriseCategoryType getEnumsByValue(String value) {
        EnterpriseCategoryType[] baseTypes = values();
        for (EnterpriseCategoryType baseType : baseTypes) {
            if (baseType.value.equals(value)) {
                return baseType;
            }
        }
        return MT_99;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static String getValue(String code) {
        EnterpriseCategoryType[] baseTypes = values();
        for (EnterpriseCategoryType baseType : baseTypes) {
            if (baseType.code.equals(code)) {
                return baseType.value;
            }
        }
        return MT_99.value;
    }

    /**
     * 判断是是合法的操作符
     *
     * @param type 传入key值
     * @return 合法为true，非法为false
     */
    public static boolean contains(String type) {
        for (EnterpriseCategoryType typeEnum : EnterpriseCategoryType.values()) {
            if (typeEnum.name().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {

        return EnterpriseCategoryType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }
}
