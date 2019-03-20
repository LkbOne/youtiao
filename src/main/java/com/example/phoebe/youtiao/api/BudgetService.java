package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.ListBudgetByAccountBookIdResult;
import com.example.phoebe.youtiao.api.result.QueryBudgetByIdResult;
import com.example.phoebe.youtiao.api.vo.budget.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;

public interface BudgetService {
    ModelResult addBudget(AddbudgetVo vo);

    ModelResult updateBudget(UpdateBudgetVo vo);

    ModelResult deleteBudgetById(DeleteBudgetVo vo);

    ModelResult<QueryBudgetByIdResult> queryBudgetById(QueryBudgetByIdVo vo);

    ModelResult<PageResult<ListBudgetByAccountBookIdResult>> listBudgetByAccountBookId(ListBudgetVo vo);
}