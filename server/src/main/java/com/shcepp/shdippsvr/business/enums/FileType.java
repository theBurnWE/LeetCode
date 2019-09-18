package com.shcepp.shdippsvr.business.enums;

import com.shcepp.shdippsvr.business.service.FileService;

/**
 * @author BrunE
 * @date 2019-08-29 14:10
 * @Description: 文件类型
 */
public enum FileType implements BaseEnums {
    FT_101("101", "企业Logo的原图"),
    FT_102("102", "企业Logo的缩略图"),
    FT_103("103", "企业图片横图"),
    FT_104("104", "企业图片竖图"),
    FT_105("105", "企业视频"),
    FT_201("201", "平台Logo的原图"),
    FT_202("202", "平台L的缩略图"),
    FT_203("203", "平台图片横图"),
    FT_204("204", "平台图片竖图"),
    FT_303("303", "产品图片横图"),
    FT_304("304", "产品图片竖图"),
    MT_99("99", "未匹配");

    private final String code;
    private final String value;

    FileType(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 获取枚举类型值
     *
     * @param code code值
     * @return value值
     */
    public static FileType getEnumsByCode(String code) {
        FileType[] baseTypes = values();
        for (FileType baseType : baseTypes) {
            if (baseType.code.equals(code)) {
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
        FileType[] baseTypes = values();
        for (FileType baseType : baseTypes) {
            if (baseType.code.equals(code)) {
                return baseType.value;
            }
        }
        return MT_99.value;
    }
    /**
     * 获取枚举类型值
     *
     * @param value value值
     * @return value值
     */
    public static FileType getEnumsByValue(String value) {
        FileType[] baseTypes = values();
        for (FileType baseType : baseTypes) {
            if (baseType.value.equals(value)) {
                return baseType;
            }
        }
        return MT_99;
    }

    /**
     * 判断是是合法的操作符
     *
     * @param code 传入code值
     * @return 合法为true，非法为false
     */
    public static boolean contains(String code) {
        for (FileType typeEnum : FileType.values()) {
            if (typeEnum.code.equals(code)) {
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
        return FileType.class.getName() + FileService.FILE_URL_PATTEN + this.code;
    }

    @Override
    public Object getValues() {
        return code;
    }


}
