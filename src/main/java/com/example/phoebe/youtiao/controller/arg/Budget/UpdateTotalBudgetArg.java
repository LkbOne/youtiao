package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateTotalBudgetArg extends BaseArg {
    String id;
    Float totalBudget;
    Float warnMoney;
    Date beginTime;
    Date endTime;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }

}
