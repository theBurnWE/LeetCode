package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * @author BrunE
 * @date 2019-07-18 14:10
 * @Description: 业务类型
 */
public enum ProductCategoryType implements BaseEnums {
    //01	云服务
    PCT_01("01", "云服务"),
    //02	数字视听
    PCT_02("02", "数字视听"),
    //03	游戏娱乐
    PCT_03("03", "游戏娱乐"),
    //04	数字阅读
    PCT_04("04", "数字阅读"),
    //05	行业应用
    PCT_05("05", "行业应用"),
    //06	跨境电商
    PCT_06("06", "跨境电商"),

    //  异常
    MT_99("99", "未匹配");

    private final String code;
    private final String value;

    ProductCategoryType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static ProductCategoryType getEnumsByCode(String code) {
        ProductCategoryType[] baseTypes = values();
        for (ProductCategoryType baseType : baseTypes) {
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
    public static ProductCategoryType getEnumsByValue(String value) {
        ProductCategoryType[] baseTypes = values();
        for (ProductCategoryType baseType : baseTypes) {
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
        ProductCategoryType[] baseTypes = values();
        for (ProductCategoryType baseType : baseTypes) {
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
        for (ProductCategoryType typeEnum : ProductCategoryType.values()) {
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

        return ProductCategoryType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }
}
