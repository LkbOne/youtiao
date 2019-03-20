package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.result.*;
import com.example.phoebe.youtiao.api.vo.SumInAndOutExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.annotion.TokenCheckTrigger;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controller.arg.Expenses.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@Slf4j
public class ExpensesController {
    @Autowired
    ExpensesService expensesService;

    @TokenCheckTrigger
    @ApiOperation(value = "添加花费")
    @RequestMapping(value = "addExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addExpenses(@RequestHeader String token, @RequestBody AddExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.addExpenses params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        AddExpensesVo vo = BeanUtil.copy(arg, AddExpensesVo.class);
        return expensesService.addExpenses(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "更新花费")
    @RequestMapping(value = "updateExpenses", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ModelResult updateExpenses(@RequestHeader String token, @RequestBody UpdateExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.updateExpenses params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        UpdateExpensesVo vo = BeanUtil.copy(arg, UpdateExpensesVo.class);
        return expensesService.updateExpenses(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "删除花费")
    @RequestMapping(value = "deleteExpensesById", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ModelResult deleteExpensesById(@RequestHeader String token, @RequestBody DeleteExpensesById arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.deleteExpensesById params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        DeleteExpensesVo vo = BeanUtil.copy(arg, DeleteExpensesVo.class);
        return expensesService.deleteExpensesById(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "通过id获得花费详情")
    @RequestMapping(value = "QueryExpensesById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<QueryExpensesByIdResult> getExpensesById(@RequestHeader String token, @RequestBody QueryExpensesByIdArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.QueryExpensesById params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        QueryExpensesByIdVo vo = BeanUtil.copy(arg, QueryExpensesByIdVo.class);

        return expensesService.queryExpensesById(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "展示所有的花费")
    @RequestMapping(value = "listExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listExpenses(@RequestHeader String token, @RequestBody ListExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.listExpensesByAccountBookId params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        ListExpensesVo vo = BeanUtil.copy(arg, ListExpensesVo.class);
        return expensesService.listExpenses(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "统计某一个区间内的总支出与总收入")
    @RequestMapping(value = "sumInAndOutExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<SumInAndOutExpensesResult> sumInAndOutExpenses(@RequestHeader String token, @RequestBody SumInAndOutExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.sumInAndOutExpenses params is error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        SumInAndOutExpensesVo vo = BeanUtil.copy(arg, SumInAndOutExpensesVo.class);
        return expensesService.sumInAndOutExpenses(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "统计所选日期费用")
    @RequestMapping(value = "sumThisDayExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<SumThisDayExpensesResult> sumThisDayExpenses(@RequestHeader String token, @RequestBody SumThisDayExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.sumThisDayExpenses params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        SumThisDayExpensesVo vo = BeanUtil.copy(arg, SumThisDayExpensesVo.class);
        return expensesService.sumThisDayExpenses(vo);
    }
    @TokenCheckTrigger
    @ApiOperation(value = "分数组展示每一天的所有费用")
    @RequestMapping(value = "showEveryDayExpensesDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<List<List<EveryDayExpensesDetailResult>>> showEveryDayExpensesDetail(@RequestHeader String token, @RequestBody EveryDayExpensesDetailArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.showEveryDayExpensesDetail params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        EveryDayExpensesDetailVo vo = BeanUtil.copy(arg, EveryDayExpensesDetailVo.class);
        return expensesService.showEveryDayExpensesDetail(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "报表的展示")
    @RequestMapping(value = "expensesGroupClassificationByTypeStatistic", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<ExpensesGroupClassificationByTypeStatisticResult> expensesGroupClassificationByTypeStatistic(@RequestHeader String token, @RequestBody ExpensesGroupClassificationByTypeStatisticArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.expensesGroupClassificationByTypeStatistic params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        ExpensesGroupClassificationByTypeStatisticVo vo = BeanUtil.copy(arg, ExpensesGroupClassificationByTypeStatisticVo.class);
        return expensesService.expensesGroupClassificationByTypeStatistic(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "展示某区间内的费用（收入，支出，结余）")
    @RequestMapping(value = "showExpensesTrendBetweenIntervalByAccountBookId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<ShowExpensesTreadResult> showExpensesTrendBetweenIntervalByAccountBookId(@RequestHeader String token, @RequestBody ShowExpensesTrendBetweenIntervalByAccountBookIdArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.showExpensesTrendBetweenIntervalByAccountBookId params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        ShowExpensesTrendBetweenIntervalByAccountBookIdVo vo = BeanUtil.copy(arg, ShowExpensesTrendBetweenIntervalByAccountBookIdVo.class);
        return expensesService.showExpensesTrendBetweenIntervalByAccountBookId(vo);
    }

}
