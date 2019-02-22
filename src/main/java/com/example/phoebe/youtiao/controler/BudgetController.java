package com.example.phoebe.youtiao.controler;

import com.example.phoebe.youtiao.api.BudgetService;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.controler.arg.AddAccountBookArg;
import com.example.phoebe.youtiao.controler.arg.Budget.*;
import com.example.phoebe.youtiao.controler.arg.DeleteAccountBookByIdArg;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
@Slf4j
public class BudgetController  {
    @Autowired
    BudgetService budgetService;

    @ApiOperation(value = "添加预算")
    @RequestMapping(value = "addBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addBudget(AddBudgetArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return budgetService.addBudget();
    }

    @ApiOperation(value = "更新预算")
    @RequestMapping(value = "updateBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult updateBudget(UpdateBudgetArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return budgetService.updateBudget();
    }

    @ApiOperation(value = "删除预算")
    @RequestMapping(value = "deleteBudgetById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult deleteBudgetById(DeleteBudgetArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return budgetService.deleteBudgetById();
    }

    @ApiOperation(value = "通过id获得预算详情")
    @RequestMapping(value = "getBudgetById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult getBudgetById(GetBudgetByIdArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return budgetService.getBudgetById();
    }

    @ApiOperation(value = "展示所有的预算")
    @RequestMapping(value = "listBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listBudget(ListBudgetArg arg){
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        return budgetService.listBudget();
    }
}
