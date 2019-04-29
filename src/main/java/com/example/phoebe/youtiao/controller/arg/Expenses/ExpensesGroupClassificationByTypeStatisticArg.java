package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;


@Data
public class ExpensesGroupClassificationByTypeStatisticArg extends BaseArg {
    String accountBookId;
    Integer year;
    Integer month;

    public boolean isWrongParams(){
        if(null == accountBookId || null == year){
            return true;
        }
        return false;
    }
}
