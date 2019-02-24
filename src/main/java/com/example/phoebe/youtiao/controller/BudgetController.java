package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.BudgetService;
import com.example.phoebe.youtiao.api.vo.budget.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controller.arg.Budget.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        AddbudgetVo vo = BeanUtil.copy(arg, AddbudgetVo.class);
        return budgetService.addBudget(vo);
    }

    @ApiOperation(value = "更新预算")
    @RequestMapping(value = "updateBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult updateBudget(UpdateBudgetArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        UpdateBudgetVo vo = BeanUtil.copy(arg, UpdateBudgetVo.class);
        return budgetService.updateBudget(vo);
    }

    @ApiOperation(value = "删除预算")
    @RequestMapping(value = "deleteBudgetById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult deleteBudgetById(DeleteBudgetArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        DeleteBudgetVo vo = BeanUtil.copy(arg, DeleteBudgetVo.class);
        return budgetService.deleteBudgetById(vo);
    }

    @ApiOperation(value = "通过id获得预算详情")
    @RequestMapping(value = "queryBudgetById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult queryBudgetById(QueryBudgetByIdArg arg){
        if(arg.isWrongParams()){

            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        QueryBudgetByIdVo vo = BeanUtil.copy(arg, QueryBudgetByIdVo.class);
        return budgetService.queryBudgetById(vo);
    }

    @ApiOperation(value = "展示所有的预算")
    @RequestMapping(value = "listBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listBudget(ListBudgetArg arg){
        if(arg.isWrongParams()){
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        ListBudgetVo vo = BeanUtil.copy(arg, ListBudgetVo.class);
        return budgetService.listBudget(vo);
    }
}
