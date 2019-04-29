package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BasePageArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SumThisDayExpensesArg extends BasePageArg {
    String accountBookId;
    Date searchDay;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }
}
