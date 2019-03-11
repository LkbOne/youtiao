package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExpensesGroupClassificationByTypeStatisticArg extends BaseArg {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;
    @ApiModelProperty(name = "year", notes = "年份")
    Integer year;
    @ApiModelProperty(name = "month", notes = "月份", allowEmptyValue = true)
    Integer month;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(null == accountBookId || null == year){
            return true;
        }
        return false;
    }
}
