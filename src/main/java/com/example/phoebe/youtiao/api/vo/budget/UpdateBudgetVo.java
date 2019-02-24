package com.example.phoebe.youtiao.api.vo.budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateBudgetVo implements Serializable {
    @ApiModelProperty(name = "id", notes = "预算id")
    String id;

    @ApiModelProperty(name = "budget", notes = "预算")
    Float budget;

    @ApiModelProperty(name = "type", notes = "预算类型（旅游，吃喝等）")
    Integer type;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;
}
