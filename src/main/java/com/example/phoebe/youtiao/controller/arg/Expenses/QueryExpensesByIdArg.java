package com.example.phoebe.youtiao.controller.arg.Expenses;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

public class QueryExpensesByIdArg {
    @ApiModelProperty(name = "id", notes = "花费id")
    String id;

    public boolean isWrongParams(){
        if (StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
