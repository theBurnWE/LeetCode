package com.shcepp.shdippsvr.business.enums;

/**
 * @Auther: ChihWu
 * @Date: 2019/2/22 11:00
 * @Description:
 */
public enum BaseType {

    // 进口
    ONE("1", "进口"),
    // 出口
    TWO("2", "出口"),
    // 外汇
    THREE("3", "外汇"),
    MT_99("99", "未匹配");

    private final String code;
    private final String value;

    BaseType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     * @param code
     * @return
     */
    public static String getValue(String code) {
        BaseType[] baseTypes = values();
        for (BaseType baseType : baseTypes) {
            if (baseType.code.equals(code)) {
                return baseType.value;
            }
        }
        return "";
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


    public static String getCode(BaseType apprType) {
        return apprType.code;
    }


    /**
     * 判断是包含
     * @param type
     * @return
     */
    public static boolean contains(String type) {
        for (BaseType typeEnum : BaseType.values()) {
            if (typeEnum.name().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
