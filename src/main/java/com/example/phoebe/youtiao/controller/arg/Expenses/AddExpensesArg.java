package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class AddExpensesArg extends BaseArg {

    @ApiModelProperty(name = "budgetId", notes = "budgetId", allowEmptyValue = true)
    String budgetId;

    @ApiModelProperty(name = "accountBookId", notes = "accountBookId")
    String accountBookId;

    @ApiModelProperty(name = "name", notes = "名字", allowEmptyValue = true)
    String name;

    @ApiModelProperty(name = "description", notes = "备注", allowEmptyValue = true)
    String description;


    @ApiModelProperty(name = "expenses", notes = "金额")
    Float expenses;

    @ApiModelProperty(name = "type", notes = "0 为收入  1 为支出", allowEmptyValue = false)
    Integer type;

    @ApiModelProperty(name = "classification", notes = "你标注的收入支出类型", allowEmptyValue = false)
    Integer classification;


    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if (StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }
}
