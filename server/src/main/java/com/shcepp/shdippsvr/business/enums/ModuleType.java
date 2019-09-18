package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * @author BrunE
 * @date 2019-07-18 14:10
 * @Description: 业务类型
 */
public enum ModuleType implements BaseEnums {
    // 首页
    MT_00("00", "首页"),
    // 平台
    MT_01("01", "平台"),
    // 企业
    MT_02("02", "企业"),
    // 产品
    MT_03("03", "产品"),
    //产品推荐位：数字视听、游戏娱乐、数字教育服务、云服务、信息技术服务、跨境电商
    MT_P1("P1","数字视听"),
    MT_P2("P2","游戏娱乐"),
    MT_P3("P3","数字教育服务"),
    MT_P4("P4","云服务"),
    MT_P5("P5","信息技术服务"),
    MT_P6("P6","跨境电商"),
    //平台推荐位
    PT_P1("Platform1","数字视听平台"),
    PT_P2("Platform2","游戏娱乐平台 "),
    PT_P3("Platform3","数字教育服务平台"),
    PT_P4("Platform4","云服务平台"),
    PT_P5("Platform5","信息技术服务平台"),
    PT_P6("Platform6","跨境电商平台"),
    //企业推荐位
    ET_E1("Enterprise1","云服务"),
    ET_E2("Enterprise2","数字视听 "),
    ET_E3("Enterprise3","游戏娱乐"),
    ET_E4("Enterprise4","数字阅读"),
    ET_E5("Enterprise5","行业应用"),
    ET_E6("Enterprise6","跨境电商"),
        //  异常
    MT_99("99", "未匹配");

    private final String code;
    private final String value;

    ModuleType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static ModuleType getEnumsByCode(String code) {
        ModuleType[] baseTypes = values();
        for (ModuleType baseType : baseTypes) {
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
    public static ModuleType getEnumsByValue(String value) {
        ModuleType[] baseTypes = values();
        for (ModuleType baseType : baseTypes) {
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
        ModuleType[] baseTypes = values();
        for (ModuleType baseType : baseTypes) {
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
        for (ModuleType typeEnum : ModuleType.values()) {
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

        return ModuleType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }
}
