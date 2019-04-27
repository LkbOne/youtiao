package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateBudgetVo extends BaseArg {
    @ApiModelProperty(name = "id", notes = "预算id")
    String id;

    @ApiModelProperty(name = "budget", notes = "预算")
    Float budget;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;

    @ApiModelProperty(name = "warnMoney", notes = "提醒金额")
    Float warnMoney;

    Integer classification;
}
