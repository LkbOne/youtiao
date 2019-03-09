package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class AddExpensesVo extends BaseArg {
    @ApiModelProperty(value = "budgetId", notes = "budgetId")
    String budgetId;

    @ApiModelProperty(value = "accountBookId", notes = "accountBookId")
    String accountBookId;

    @ApiModelProperty(name = "expensesDate", notes = "消费日期")
    Date expensesDate;

    @ApiModelProperty(value = "description", notes = "名字")
    String description;

    @ApiModelProperty(value = "expenses", notes = "金额")
    Float expenses;

    @ApiModelProperty(value = "type", notes = "0 为收入  1 为支出")
    Integer type;

    @ApiModelProperty(value = "classification", notes = "该花费的状态（已经花费，计划花费，正在花费）")
    Integer classification;

}
