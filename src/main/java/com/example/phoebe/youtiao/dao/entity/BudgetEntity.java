package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
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
