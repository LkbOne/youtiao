package com.example.phoebe.youtiao.api.vo.expenses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddExpensesVo implements Serializable {
    @ApiModelProperty(value = "budgetId", notes = "budgetId")
    String budgetId;

    @ApiModelProperty(value = "accountBookId", notes = "accountBookId")
    String accountBookId;

    @ApiModelProperty(value = "name", notes = "名字")
    String name;

    @ApiModelProperty(value = "expenses", notes = "金额")
    Float expenses;

    @ApiModelProperty(value = "inType", notes = "收入类型(工资、兼职）")
    Integer inType;

    @ApiModelProperty(value = "outType", notes = "该花费的状态（已经花费，计划花费，正在花费）")
    Integer outType;

}
