package com.example.phoebe.youtiao.api.vo.budget;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateTotalBudgetVo implements Serializable {
    String id;
    Float totalBudget;
    Float warnMoney;
    Date endTime;
    Date beginTime;
}
