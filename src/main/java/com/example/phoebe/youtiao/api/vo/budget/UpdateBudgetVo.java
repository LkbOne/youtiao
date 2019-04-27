package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateBudgetVo extends BaseArg {
    String id;

    Float budget;

    Date beginTime;

    Date endTime;

    Float warnMoney;

    Integer classification;
}
