package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * @author BrunE
 * @date 2019-07-18 14:10
 * @Description: 业务类型
 */
public enum LanType implements BaseEnums {
    // 首页
    MT_EN("EN", "英文"),
    // 平台
    MT_CN("CN", "中文"),
    MT_99("99", "未匹配");
    private final String code;
    private final String value;

    LanType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static LanType getEnumsByCode(String code) {
        LanType[] baseTypes = values();
        for (LanType baseType : baseTypes) {
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
    public static LanType getEnumsByValue(String value) {
        LanType[] baseTypes = values();
        for (LanType baseType : baseTypes) {
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
        LanType[] baseTypes = values();
        for (LanType baseType : baseTypes) {
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
        for (LanType typeEnum : LanType.values()) {
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

        return LanType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }
}
