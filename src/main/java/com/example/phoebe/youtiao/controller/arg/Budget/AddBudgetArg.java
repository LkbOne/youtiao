package com.example.phoebe.youtiao.controller.arg.Budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AddBudgetArg implements Serializable {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;

    @ApiModelProperty(name = "budget", notes = "预算金额")
    Float budget;

    @ApiModelProperty(name = "type", notes = "预算类型（旅游，吃喝等）")
    Integer type;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;

    public boolean isWrongParams(){
        return true;
    }

}
