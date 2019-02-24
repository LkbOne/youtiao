package com.example.phoebe.youtiao.api.vo.budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ListBudgetVo implements Serializable {
    @ApiModelProperty(name = "abId", notes = "账本id")
    String abId;

    @ApiModelProperty(name = "BudgetZoneLeft", notes = "预算最小值")
    Float BudgetZoneLeft;

    @ApiModelProperty(name = "BudgetZoneRight", notes = "预算最大值")
    Float BudgetZoneRight;

    @ApiModelProperty(name = "type", notes = "预算类型（旅游，吃喝等）")
    Integer type;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;
}
