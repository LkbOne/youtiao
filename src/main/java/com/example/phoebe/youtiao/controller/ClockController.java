package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.ClockService;
import com.example.phoebe.youtiao.api.result.ShowExpensesTreadResult;
import com.example.phoebe.youtiao.api.vo.clock.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.annotion.TokenCheckTrigger;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controller.arg.Clock.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clock")
@Slf4j
public class ClockController  {

    @Autowired
    ClockService clockService;

    @TokenCheckTrigger
    @RequestMapping(value = "addClock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult addClock(@RequestHeader String token, @RequestBody AddClockArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.showExpensesTrendBetweenIntervalByAccountBookId params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        AddClockVo vo = BeanUtil.copy(arg, AddClockVo.class);
        vo.setAid(arg.getAccountId());
        return clockService.addClock(vo);
    }
    @TokenCheckTrigger
    @RequestMapping(value = "updateClock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult updateClock(@RequestHeader String token, @RequestBody UpdateClockArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.showExpensesTrendBetweenIntervalByAccountBookId params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        UpdateClockVo vo = BeanUtil.copy(arg, UpdateClockVo.class);
        return clockService.updateClock(vo);
    }
    @TokenCheckTrigger
    @RequestMapping(value = "deleteClock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult deleteClock(@RequestHeader String token, @RequestBody DeleteClockArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.showExpensesTrendBetweenIntervalByAccountBookId params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        DeleteClockVo vo = BeanUtil.copy(arg, DeleteClockVo.class);
        return clockService.deleteClock(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "listClock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult listClock(@RequestHeader String token, @RequestBody ListClockArg arg){
        ListClockVo vo = BeanUtil.copy(arg, ListClockVo.class);
        vo.setAid(arg.getAccountId());
        return clockService.listClock(vo);
    }
    @TokenCheckTrigger
    @RequestMapping(value = "queryClockById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult queryClockById(@RequestHeader String token, @RequestBody QueryClockByIdArg arg){
        QueryClockByIdVo vo = BeanUtil.copy(arg, QueryClockByIdVo.class);
        return clockService.queryClockById(vo);
    }
}
