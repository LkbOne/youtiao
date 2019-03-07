package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
public class UpdateExpensesVo extends BaseArg {
    @ApiModelProperty(value="id", notes = "id")
    String id;

//    @ApiModelProperty(name = "budgetId", notes = "budgetId", allowEmptyValue = true)
//    String budgetId;
//
//    @ApiModelProperty(name = "accountBookId", notes = "accountBookId")
//    String accountBookId;

    @ApiModelProperty(name = "name", notes = "名字", allowEmptyValue = true)
    String name;

    @ApiModelProperty(name = "description", notes = "备注", allowEmptyValue = true)
    String description;

    @ApiModelProperty(name = "expenses", notes = "金额")
    Float expenses;

    @ApiModelProperty(name = "type", notes = "0 为收入  1 为支出")
    Integer type;

    @ApiModelProperty(name = "classification", notes = "你标注的收入支出类型")
    Integer classification;
}
