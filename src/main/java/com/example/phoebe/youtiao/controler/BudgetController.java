package com.example.phoebe.youtiao.controler;

import com.example.phoebe.youtiao.commmon.ModelResult;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
@Slf4j
public class BudgetController  {
    @ApiOperation(value = "添加预算")
    @RequestMapping(value = "addBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addBudget(){

    }

    @ApiOperation(value = "更新预算")
    @RequestMapping(value = "updateBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult updateBudget(){

    }

    @ApiOperation(value = "删除预算")
    @RequestMapping(value = "deleteBudgetById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult deleteBudgetById(){

    }

    @ApiOperation(value = "通过id获得预算详情")
    @RequestMapping(value = "getBudgetById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult getBudgetById(){

    }

    @ApiOperation(value = "展示所有的预算")
    @RequestMapping(value = "listBudget", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listBudget(){

    }
}
