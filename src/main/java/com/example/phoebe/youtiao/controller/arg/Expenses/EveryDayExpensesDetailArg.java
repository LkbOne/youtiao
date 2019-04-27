package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EveryDayExpensesDetailArg extends BaseArg {

    // @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;


    //  @ApiModelProperty(name = "recentDay", notes = "前面几天")
    Integer recentDay;

    public boolean isWrongParams(){
        if(null == accountBookId || null == recentDay){
            return true;
        }
        return false;
    }
}
