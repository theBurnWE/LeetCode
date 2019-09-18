package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * Created by shcepp on 2019/9/9.
 */
public enum StatusType implements BaseEnums{
    // 审核状态
    STATUS_0("0", "未上架"),
    STATUS_1("1", "正常"),
    STATUS_2("2", "待审核"),
    STATUS_9("9", "未上架"),
    MT_99("99", "未匹配");


    private final String code;
    private final String value;

    StatusType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static StatusType getEnumsByCode(String code) {
        StatusType[] baseTypes = values();
        for (StatusType baseType : baseTypes) {
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
    public static StatusType getEnumsByValue(String value) {
        StatusType[] baseTypes = values();
        for (StatusType baseType : baseTypes) {
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
        StatusType[] baseTypes = values();
        for (StatusType baseType : baseTypes) {
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

        return StatusType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }
}
