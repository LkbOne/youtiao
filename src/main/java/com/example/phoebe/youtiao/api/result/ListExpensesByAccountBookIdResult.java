package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListExpensesByAccountBookIdResult implements Serializable {
    String id;
    String budgetId;
    String accountBookId;
    Long expenseDate;
    Float expenses;
    Integer type;
    Integer classification;
    Long createTime;
    Long lastModifyTime;
    String description;
}
