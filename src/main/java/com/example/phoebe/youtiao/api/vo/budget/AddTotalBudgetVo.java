package com.example.phoebe.youtiao.api.vo.budget;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AddTotalBudgetVo implements Serializable {
    String accountBookId;
    Float totalBudget;
    Float warnMoney;
    Date endTime;
    Date beginTime;
}
