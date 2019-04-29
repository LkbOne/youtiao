package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateBudgetArg extends BaseArg {
    String id;
    Float budget;

    Integer classification;

    Date beginTime;

    Date endTime;

    Float warnMoney;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
