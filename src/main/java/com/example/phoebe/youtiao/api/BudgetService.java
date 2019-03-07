package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.ListBudgetByAccountIdResult;
import com.example.phoebe.youtiao.api.result.QueryAccountBookByAbIdResult;
import com.example.phoebe.youtiao.api.result.QueryBudgetByIdResult;
import com.example.phoebe.youtiao.api.vo.budget.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import com.example.phoebe.youtiao.dao.entity.BudgetEntity;

import java.util.List;

public interface BudgetService {
    ModelResult addBudget(AddbudgetVo vo);

    ModelResult updateBudget(UpdateBudgetVo vo);

    ModelResult deleteBudgetById(DeleteBudgetVo vo);

    ModelResult<QueryBudgetByIdResult> queryBudgetById(QueryBudgetByIdVo vo);

    ModelResult<PageResult<ListBudgetByAccountIdResult>> listBudgetByAccountBookId(ListBudgetVo vo);
}