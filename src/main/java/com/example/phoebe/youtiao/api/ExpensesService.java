package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.QueryExpensesByIdResult;
import com.example.phoebe.youtiao.api.vo.expenses.AddExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.DeleteExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.QueryExpensesByIdVo;
import com.example.phoebe.youtiao.api.vo.expenses.UpdateExpensesVo;
import com.example.phoebe.youtiao.commmon.ModelResult;

public interface ExpensesService {
    ModelResult addExpenses(AddExpensesVo vo);

    ModelResult updateExpenses(UpdateExpensesVo vo);

    ModelResult deleteExpensesById(DeleteExpensesVo vo);

    ModelResult<QueryExpensesByIdResult> queryExpensesById(QueryExpensesByIdVo vo);

    ModelResult listExpenses();
}
