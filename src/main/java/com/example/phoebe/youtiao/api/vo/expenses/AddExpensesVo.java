package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import java.util.Date;

@Data
public class AddExpensesVo extends BaseArg {
    String budgetId;

    String accountBookId;

    Date expenseDate;

    String description;

    Float expenses;

    Integer type;

    Integer classification;

}
