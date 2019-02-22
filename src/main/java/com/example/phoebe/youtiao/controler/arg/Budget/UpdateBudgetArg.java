package com.example.phoebe.youtiao.controler.arg.Budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateBudgetArg implements Serializable {

    @ApiModelProperty(name = "budgetId", notes = "预算id")
    String budgetId;

    @ApiModelProperty(name = "budget", notes = "预算")
    Float budget;

    @ApiModelProperty(name = "type", notes = "预算类型（旅游，吃喝等）")
    Integer type;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(budgetId)){
            return false;
        }
        return true;
    }
}
