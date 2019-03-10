package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class DeleteExpensesById extends BaseArg {
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
