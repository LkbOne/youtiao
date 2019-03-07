package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

@Data
@ApiModel
public class AddBudgetArg extends BaseArg {
    @ApiModelProperty(name = "accountBookId",notes = "账本id")
    String accountBookId;

    @ApiModelProperty(name = "budget", notes = "预算金额")
    Float budget;

    @ApiModelProperty(name = "type", notes = "预算类型（旅游，吃喝等）")
    Integer type;

    @ApiModelProperty(name = "beginTime", notes = "开始时间")
    Date beginTime;

    @ApiModelProperty(name = "endTime", notes = "结束时间")
    Date endTime;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isBlank(accountBookId)){
            return true;
        }
        return false;
    }

}
