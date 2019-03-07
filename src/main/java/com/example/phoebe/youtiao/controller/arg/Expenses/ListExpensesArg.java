package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import com.example.phoebe.youtiao.controller.arg.BasePageArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class ListExpensesArg extends BasePageArg {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }

//    可以获得预算下的所有费用
//    @ApiModelProperty(name = "budgetId", notes = "budgetId", allowEmptyValue = true)
//    String budgetId;
}
