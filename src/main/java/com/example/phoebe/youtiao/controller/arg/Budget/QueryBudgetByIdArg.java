package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryBudgetByIdArg extends BaseArg {
//    @ApiModelProperty(name = "budgetId", notes = "预算id")
    String id;

//    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return false;
        }
        return true;
    }
}
