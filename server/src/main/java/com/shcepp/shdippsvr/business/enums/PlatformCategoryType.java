package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * @author BrunE
 * @date 2019-07-18 14:10
 * @Description: 业务类型
 */
public enum PlatformCategoryType implements BaseEnums {
    //01	数字视听平台
    PCT_01("01", "数字视听平台"),
    //02	游戏娱乐平台
    PCT_02("02", "游戏娱乐平台"),
    //03	数字教育服务平台
    PCT_03("03", "数字教育服务平台"),
    //04	云服务平台
    PCT_04("04", "云服务平台"),
    //05	信息技术服务平台
    PCT_05("05", "信息技术服务平台"),
    //06	跨境电商平台
    PCT_06("06", "跨境电商平台"),


    //  异常
    MT_99("99", "未匹配");

    private final String code;
    private final String value;

    PlatformCategoryType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static PlatformCategoryType getEnumsByCode(String code) {
        PlatformCategoryType[] baseTypes = values();
        for (PlatformCategoryType baseType : baseTypes) {
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
    public static PlatformCategoryType getEnumsByValue(String value) {
        PlatformCategoryType[] baseTypes = values();
        for (PlatformCategoryType baseType : baseTypes) {
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
        PlatformCategoryType[] baseTypes = values();
        for (PlatformCategoryType baseType : baseTypes) {
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
        for (PlatformCategoryType typeEnum : PlatformCategoryType.values()) {
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

        return PlatformCategoryType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }
}
