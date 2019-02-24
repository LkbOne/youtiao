package com.example.phoebe.youtiao.controller.arg.Budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class QueryBudgetByIdArg implements Serializable {
    @ApiModelProperty(name = "budgetId", notes = "预算id")
    String id;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return false;
        }
        return true;
    }
}
