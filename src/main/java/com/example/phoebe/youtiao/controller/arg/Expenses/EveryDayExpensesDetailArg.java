package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;

@Data
public class EveryDayExpensesDetailArg extends BaseArg {
    String accountBookId;
    Integer recentDay;

    public boolean isWrongParams(){
        if(null == accountBookId || null == recentDay){
            return true;
        }
        return false;
    }
}
