package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class ExpensesEntity implements Serializable {
    String id;
    String budgetId;
    String accountBookId;
    String name;
    Float expenses;
    Integer type;
    Integer classification;
    Integer status;
    Date createTime;
    Date lastModifyTime;
    String description;
}
