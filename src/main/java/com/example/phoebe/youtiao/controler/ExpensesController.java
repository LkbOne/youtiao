package com.example.phoebe.youtiao.controler;

import com.example.phoebe.youtiao.api.BudgetService;
import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.controler.arg.Expenses.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
@Slf4j
public class ExpensesController {
    @Autowired
    ExpensesService expensesService;

    @ApiOperation(value = "添加花费")
    @RequestMapping(value = "addExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addExpenses(AddExpensesArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        return expensesService.addExpenses();
    }

    @ApiOperation(value = "更新花费")
    @RequestMapping(value = "updateExpenses", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ModelResult updateExpenses(UpdateExpensesArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return expensesService.updateExpenses();
    }

    @ApiOperation(value = "删除花费")
    @RequestMapping(value = "deleteExpensesById", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ModelResult deleteExpensesById(DeleteExpensesById arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return expensesService.deleteExpensesById();
    }

    @ApiOperation(value = "通过id获得花费详情")
    @RequestMapping(value = "getExpensesById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult getExpensesById(GetExpensesByIdArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return expensesService.getExpensesById();
    }

    @ApiOperation(value = "展示所有的花费")
    @RequestMapping(value = "listExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listExpenses(ListExpensesArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return expensesService.listExpenses();
    }
}
