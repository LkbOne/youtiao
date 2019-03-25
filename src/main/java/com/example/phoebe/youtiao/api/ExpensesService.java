package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.*;
import com.example.phoebe.youtiao.api.vo.SumInAndOutExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;

import java.util.List;

public interface ExpensesService {
    ModelResult addExpenses(AddExpensesVo vo);

    ModelResult updateExpenses(UpdateExpensesVo vo);

    ModelResult deleteExpensesById(DeleteExpensesVo vo);

    ModelResult<QueryExpensesByIdResult> queryExpensesById(QueryExpensesByIdVo vo);

    ModelResult<PageResult<ListExpensesByAccountBookIdResult>> listExpenses(ListExpensesVo vo);

    ModelResult<SumInAndOutExpensesResult> sumInAndOutExpenses(SumInAndOutExpensesVo vo);

    ModelResult<SumThisDayExpensesResult> sumThisDayExpenses(SumThisDayExpensesVo vo);

    ModelResult<List<List<EveryDayExpensesDetailResult>>> showEveryDayExpensesDetail(EveryDayExpensesDetailVo vo);

    ModelResult<ExpensesGroupClassificationByTypeStatisticResult> expensesGroupClassificationByTypeStatistic(ExpensesGroupClassificationByTypeStatisticVo vo);

    ModelResult<ShowExpensesTreadResult> showExpensesTrendBetweenIntervalByAccountBookId(ShowExpensesTrendBetweenIntervalByAccountBookIdVo vo);

    ExportExpensesInfoResult exportExpensesInfoToExcel(ExportExcelVo vo);
}