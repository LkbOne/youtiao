package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import com.example.phoebe.youtiao.controller.arg.BasePageArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;

@Data
public class SumThisDayExpensesArg extends BasePageArg {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;
    @ApiModelProperty(name = "searchDay", notes = "查询当天的日期")
    Date searchDay;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }
}
