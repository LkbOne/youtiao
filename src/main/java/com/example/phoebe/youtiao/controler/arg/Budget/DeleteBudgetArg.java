package com.example.phoebe.youtiao.controler.arg.Budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class DeleteBudgetArg implements Serializable {
    @ApiModelProperty(name = "budgetId", notes = "预算id")
    String budgetId;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(budgetId)){
            return false;
        }
        return true;
    }
}
