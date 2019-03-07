package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.result.QueryExpensesByIdResult;
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
            log.warn("ExpensesController.addExpenses");
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
            log.warn("ExpensesController.updateExpenses");
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
            log.warn("ExpensesController.deleteExpensesById");
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
            log.warn("ExpensesController.QueryExpensesById");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        QueryExpensesByIdVo vo = BeanUtil.copy(arg, QueryExpensesByIdVo.class);

        return expensesService.queryExpensesById(vo);
    }

    @TokenCheckTrigger
    @ApiOperation(value = "展示所有的花费")
    @RequestMapping(value = "listExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listExpenses(@RequestHeader String token, @RequestBody ListExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.listExpensesByAccountBookId");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        ListExpensesVo vo = BeanUtil.copy(arg, ListExpensesVo.class);
        return expensesService.listExpenses(vo);
    }
}
