package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
public class AddTotalBudgetArg extends BaseArg {

    @ApiModelProperty(name = "账本id")
    String accountBookId;
    @ApiModelProperty(name = "总预算金额")
    Float totalBudget;
    @ApiModelProperty(name = "提醒金额")
    Float warnMoney;
    @ApiModelProperty(name = "开始时间")
    Date beginTime;
    @ApiModelProperty(name = "结束时间")
    Date endTime;

    @ApiModelProperty(hidden = true)
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