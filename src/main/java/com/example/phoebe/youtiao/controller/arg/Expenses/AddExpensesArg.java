package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
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
