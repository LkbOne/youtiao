package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
public class AddTotalBudgetArg extends BaseArg {

    String accountBookId;
    Float totalBudget;
    Float warnMoney;
    Date beginTime;
    Date endTime;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(accountBookId)){
            return true;
        }
        if(null == beginTime || null == endTime){
            return true;
        }

        return beginTime.getTime() > endTime.getTime();

    }
}
