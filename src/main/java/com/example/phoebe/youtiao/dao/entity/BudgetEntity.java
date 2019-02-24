package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BudgetEntity implements Serializable {
    String id;
    String accountBookId;
    Float budget;
    Integer status;
    Integer type;
    Date beginTime;
    Date endTime;
    Date createTime;
    Date lastModifyTime;
}
