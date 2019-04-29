package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import java.util.Date;

@Data
public class AddBudgetVo extends BaseArg {
    String accountBookId;

    Float budget;

    Date beginTime;

    Date endTime;

    Float warnMoney;

    Integer classification;
}
