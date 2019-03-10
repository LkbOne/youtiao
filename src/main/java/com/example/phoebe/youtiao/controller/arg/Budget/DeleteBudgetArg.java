package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class DeleteBudgetArg extends BaseArg {
//    @ApiModelProperty(name = "id", notes = "预算id")
    String id;

//    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return false;
        }
        return true;
    }
}
