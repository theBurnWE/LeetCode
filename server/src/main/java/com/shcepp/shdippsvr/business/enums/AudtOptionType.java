package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * @author BrunE
 * @date 2019-07-18 14:10
 * @Description: 审计枚举类
 */
public enum AudtOptionType implements BaseEnums {
    AOT_ListAll("ListAll", "全表查询"),
    AOT_Query("Query", "查询"),
    AOT_Insert("Insert", "插入"),
    AOT_Delete("Delete", "删除"),
    AOT_Modify("Modify", "修改"),
    AOT_Print("Print", "打印"),
    AOT_Import("Import", "导入"),
    AOT_OutPort("OutPort", "导出"),
    AOT_ReStore("ReStore", "还原"),
    MT_99("99", "未匹配");
    private final String code;
    private final String value;

    AudtOptionType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static AudtOptionType getEnumsByCode(String code) {
        AudtOptionType[] baseTypes = values();
        for (AudtOptionType baseType : baseTypes) {
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
    public static AudtOptionType getEnumsByValue(String value) {
        AudtOptionType[] baseTypes = values();
        for (AudtOptionType baseType : baseTypes) {
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
        AudtOptionType[] baseTypes = values();
        for (AudtOptionType baseType : baseTypes) {
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
        for (AudtOptionType typeEnum : AudtOptionType.values()) {
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

        return AudtOptionType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }
    }
