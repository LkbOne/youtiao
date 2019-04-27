package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TotalBudgetEntity implements Serializable {
    String id;
    String accountBookId;
    Float totalBudget;
    Float warnMoney;
    Date createTime;
    Date lastModifyTime;
    Date endTime;
    Date beginTime;

}
