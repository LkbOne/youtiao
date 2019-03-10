package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.BudgetService;
import com.example.phoebe.youtiao.api.result.QueryBudgetByIdResult;
import com.example.phoebe.youtiao.api.vo.budget.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.annotion.TokenCheckTrigger;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controller.arg.Budget.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
@Slf4j
public class BudgetController  {
    @Autowired
    BudgetService budgetService;

    @TokenCheckTrigger
//    @ApiOperation(value = "添加预算")
    @RequestMapping(value = "addBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addBudget(@RequestHeader String token, @RequestBody AddBudgetArg arg){
        if(arg.isWrongParams()){
            log.warn("BudgetController.addBudget");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        AddbudgetVo vo = BeanUtil.copy(arg, AddbudgetVo.class);
        return budgetService.addBudget(vo);
    }

    @TokenCheckTrigger
//    @ApiOperation(value = "更新预算")
    @RequestMapping(value = "updateBudget", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ModelResult updateBudget(@RequestHeader String token, @RequestBody UpdateBudgetArg arg){
        if(arg.isWrongParams()){
            log.warn("BudgetController.updateBudget");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        UpdateBudgetVo vo = BeanUtil.copy(arg, UpdateBudgetVo.class);
        return budgetService.updateBudget(vo);
    }

    @TokenCheckTrigger
//    @ApiOperation(value = "删除预算")
    @RequestMapping(value = "deleteBudgetById", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ModelResult deleteBudgetById(@RequestHeader String token, @RequestBody DeleteBudgetArg arg){
        if(arg.isWrongParams()){
            log.warn("BudgetController.deleteBudgetById");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        DeleteBudgetVo vo = BeanUtil.copy(arg, DeleteBudgetVo.class);
        return budgetService.deleteBudgetById(vo);
    }

    @TokenCheckTrigger
//    @ApiOperation(value = "通过id获得预算详情")
    @RequestMapping(value = "queryBudgetById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<QueryBudgetByIdResult> queryBudgetById(@RequestHeader String token, @RequestBody QueryBudgetByIdArg arg){
        if(arg.isWrongParams()){
            log.warn("BudgetController.queryBudgetById");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        QueryBudgetByIdVo vo = BeanUtil.copy(arg, QueryBudgetByIdVo.class);
        return budgetService.queryBudgetById(vo);
    }

    @TokenCheckTrigger
//    @ApiOperation(value = "展示所有的预算")
    @RequestMapping(value = "listBudgetByAccountBookId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listBudgetByAccountBookId(@RequestHeader String token, @RequestBody ListBudgetArg arg){
        if(arg.isWrongParams()){
            log.warn("BudgetController.listBudget");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        ListBudgetVo vo = BeanUtil.copy(arg, ListBudgetVo.class);
        return budgetService.listBudgetByAccountBookId(vo);
    }
}
