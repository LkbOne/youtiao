package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class QueryBudgetByIdArg extends BaseArg {
    @ApiModelProperty(name = "budgetId", notes = "预算id")
    String id;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return false;
        }
        return true;
    }
}