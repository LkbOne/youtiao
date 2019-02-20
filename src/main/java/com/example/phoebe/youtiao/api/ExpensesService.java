package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.commmon.ModelResult;

public interface ExpensesService {
    ModelResult addExpenses();

    ModelResult updateExpenses();

    ModelResult deleteExpensesById();

    ModelResult getExpensesById();

    ModelResult listExpenses();
}
