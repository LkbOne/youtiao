package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import org.apache.commons.lang3.StringUtils;

public class QueryExpensesByIdArg extends BaseArg {
//    @ApiModelProperty(name = "id", notes = "花费id")
    String id;

//    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if (StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
