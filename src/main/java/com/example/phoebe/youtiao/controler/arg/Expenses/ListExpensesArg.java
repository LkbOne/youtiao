package com.example.phoebe.youtiao.controler.arg.Expenses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class ListExpensesArg implements Serializable {
    @ApiModelProperty(value = "name", notes = "名字")
    String name;

    @ApiModelProperty(value = "expenses", notes = "金额")
    Float expenses;

    @ApiModelProperty(value = "inType", notes = "收入类型(工资、兼职）")
    Integer intType;

    @ApiModelProperty(value = "outType", notes = "该花费的状态（已经花费，计划花费，正在花费）")
    Integer outType;

    public boolean isWrongParams(){
        if (StringUtils.isEmpty(name)){
            return true;
        }
        return false;
    }
}
