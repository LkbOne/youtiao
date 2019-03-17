package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateBudgetArg extends BaseArg {

    @ApiModelProperty(name = "id", notes = "预算id")
    String id;

    @ApiModelProperty(name = "budget", notes = "预算")
    Float budget;

    @ApiModelProperty(name = "classification", notes = "预算类型（旅游，吃喝等）")
    Integer classification;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return false;
        }
        return true;
    }
}
