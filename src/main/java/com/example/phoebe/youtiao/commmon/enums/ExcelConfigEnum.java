package com.example.phoebe.youtiao.commmon.enums;

import lombok.Getter;
@Getter
public enum ExcelConfigEnum {
    /**
     * 文件名
     */
    FILE_NAME("template"),
    CONTENT_TYPE("application/octet-stream"),
    /**
     * 表名
     */
    SHEET_NAME("sheet1");
    private Object defaultValue;

    ExcelConfigEnum(Object propertyName) {
        this.defaultValue = propertyName;
    }
}
