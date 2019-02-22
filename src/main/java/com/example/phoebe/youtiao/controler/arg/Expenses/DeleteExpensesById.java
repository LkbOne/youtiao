package com.example.phoebe.youtiao.controler.arg.Expenses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class DeleteExpensesById implements Serializable {
    @ApiModelProperty(name = "expensesId", notes = "花费id")
    String expensesId;

    public boolean isWrongParams(){
        if (StringUtils.isEmpty(expensesId)){
            return true;
        }
        return false;
    }
}
