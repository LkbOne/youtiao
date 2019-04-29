package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateBudgetVo extends BaseArg {
    String id;

    Float budget;

    Date beginTime;

    Date endTime;

    Float warnMoney;

    Integer classification;
}
