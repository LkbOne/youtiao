package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.ListExpensesByAccountBookIdResult;
import com.example.phoebe.youtiao.api.result.QueryExpensesByIdResult;
import com.example.phoebe.youtiao.api.result.SumThisDayExpensesResult;
import com.example.phoebe.youtiao.api.vo.expenses.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;

public interface ExpensesService {
    ModelResult addExpenses(AddExpensesVo vo);

    ModelResult updateExpenses(UpdateExpensesVo vo);

    ModelResult deleteExpensesById(DeleteExpensesVo vo);

    ModelResult<QueryExpensesByIdResult> queryExpensesById(QueryExpensesByIdVo vo);

    ModelResult<PageResult<ListExpensesByAccountBookIdResult>> listExpenses(ListExpensesVo vo);

    ModelResult<SumThisDayExpensesResult> sumThisDayExpenses(SumThisDayExpensesVo vo);
}
