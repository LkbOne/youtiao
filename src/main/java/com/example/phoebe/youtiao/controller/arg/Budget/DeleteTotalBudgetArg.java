package com.example.phoebe.youtiao.controller.arg.Budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class DeleteTotalBudgetArg implements Serializable {
    // @ApiModelProperty(name = "id", notes = "总预算id")
    String id;

    //  @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
