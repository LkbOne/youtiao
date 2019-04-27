package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BudgetEntity implements Serializable {
    String id;
    String accountBookId;
    Float budget;
    Integer classification;
    Float warnMoney;
    Date beginTime;
    Date endTime;
    Date createTime;
    Date lastModifyTime;

    String totalBudgetId;
}
