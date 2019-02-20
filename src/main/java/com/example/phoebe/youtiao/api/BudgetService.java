package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.commmon.ModelResult;

public interface BudgetService {
    ModelResult addBudget();

    ModelResult updateBudget();

    ModelResult deleteBudgetById();

    ModelResult getBudgetById();

    ModelResult listBudget();
}