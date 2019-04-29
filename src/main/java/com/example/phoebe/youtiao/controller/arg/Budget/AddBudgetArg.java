package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;

import java.util.Date;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddBudgetArg extends BaseArg {
    String accountBookId;
    Float budget;
    Date beginTime;
    Date endTime;
    Float warnMoney;
    Integer classification;
    public boolean isWrongParams(){
        if(StringUtils.isBlank(accountBookId)){
            return true;
        }
        return false;
    }

}
