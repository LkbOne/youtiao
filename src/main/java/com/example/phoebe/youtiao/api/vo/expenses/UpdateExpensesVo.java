package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import java.sql.Date;

@Data
public class UpdateExpensesVo extends BaseArg {
//    @ApiModelProperty(value="id", notes = "id")
    String id;

//    @ApiModelProperty(name = "budgetId", notes = "budgetId", allowEmptyValue = true)
//    String budgetId;
//
//    @ApiModelProperty(name = "accountBookId", notes = "accountBookId")
//    String accountBookId;

//    @ApiModelProperty(name = "expensesDate", notes = "消费时间")
    Date expensesDate;

//    @ApiModelProperty(name = "description", notes = "备注", allowEmptyValue = true)
    String description;

//    @ApiModelProperty(name = "expenses", notes = "金额")
    Float expenses;

//    @ApiModelProperty(name = "type", notes = "0 为收入  1 为支出")
    Integer type;

//    @ApiModelProperty(name = "classification", notes = "你标注的收入支出类型")
    Integer classification;
}
