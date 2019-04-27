package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;


@Data
public class AddExpensesArg extends BaseArg {

    String budgetId;

    String accountBookId;

    Date expenseDate;

    String description;
    Float expenses;

    Integer type;

    Integer classification;


    public boolean isWrongParams(){
        if (StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }
}
