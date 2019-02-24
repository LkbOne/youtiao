package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class QueryExpensesByIdResult implements Serializable {
    String id;
    String budgetId;
    String accountBookId;
    String name;
    Float expenses;
    Integer inType;
    Integer outType;
    Long createTime;
    Long lastModifyTime;
}
