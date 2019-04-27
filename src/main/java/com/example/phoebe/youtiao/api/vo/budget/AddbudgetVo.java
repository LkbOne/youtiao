package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AddbudgetVo extends BaseArg {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;

    @ApiModelProperty(name = "budget", notes = "预算金额")
    Float budget;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;

    @ApiModelProperty(name = "warnMoney", notes = "提示金额")
    Float warnMoney;

    @ApiModelProperty(name = "classification", notes = "分类")
    Integer classification;
}
