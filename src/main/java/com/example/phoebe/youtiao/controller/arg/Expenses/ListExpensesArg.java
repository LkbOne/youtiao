package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import com.example.phoebe.youtiao.controller.arg.BasePageArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;


@Data
@EqualsAndHashCode(callSuper = false)
public class ListExpensesArg extends BasePageArg {
//    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;

//    @ApiModelProperty(name = "recentDay", notes = "最近几天，如果不传就是全部")
    Integer recentDay;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }

//    可以获得预算下的所有费用
//    @ApiModelProperty(name = "budgetId", notes = "budgetId", allowEmptyValue = true)
//    String budgetId;
}
