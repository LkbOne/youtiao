package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.AccountBookService;
import com.example.phoebe.youtiao.api.result.GetAccountBookByIdResult;
import com.example.phoebe.youtiao.api.result.ListAccountBookResult;
import com.example.phoebe.youtiao.api.vo.accountBook.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.annotion.TokenCheckTrigger;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controller.arg.*;
import com.example.phoebe.youtiao.service.manager.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountBook")
@Slf4j
public class AccountBookController {
    @Autowired
    AccountBookService accountBookService;

    @TokenCheckTrigger
    @RequestMapping(value = "addAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addAccountBook(@RequestHeader String token, @RequestBody AddAccountBookArg arg){
        if(arg.isWrongParams()){
            log.warn("AccountBookController.addAccountBook");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        AddAccountBookVo vo = BeanUtil.copy(arg, AddAccountBookVo.class);
        return accountBookService.addAccountBook(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "updateAccountBook", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ModelResult updateAccountBook(@RequestHeader String token, @RequestBody UpdateAccountBookArg arg){
        if(arg.isWrongParams()){
            log.warn("AccountBookController.updateAccountBook");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        UpdateAccountBookVo vo = BeanUtil.copy(arg, UpdateAccountBookVo.class);
        return accountBookService.updateAccountBook(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "deleteAccountBookById", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ModelResult deleteAccountBookById(@RequestHeader String token, @RequestBody DeleteAccountBookByIdArg arg){
        if(arg.isWrongParams()){
            log.warn("");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        DeleteAccountBookVo vo = BeanUtil.copy(arg, DeleteAccountBookVo.class);
        return accountBookService.deleteAccountBookById(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "getAccountBookById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<GetAccountBookByIdResult> getAccountBookById(@RequestHeader String token, @RequestBody GetAccountBookByIdArg arg){
        if(arg.isWrongParams()){
            log.warn("");
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        GetAccountBookByIdVo vo = BeanUtil.copy(arg, GetAccountBookByIdVo.class);
        return accountBookService.getAccountBookById(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "listAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<PageResult<ListAccountBookResult>> listAccountBook(@RequestHeader String token, @RequestBody ListAccountBookArg arg){
        System.out.println("arg size:" + arg.getPageSize());
        System.out.println("arg number:" + arg.getPageNum());
        ListAccountBookVo vo = BeanUtil.copy(arg, ListAccountBookVo.class);
        return accountBookService.listAccountBook(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "resetOpenHistory", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ModelResult resetOpenHistory(@RequestHeader String token, @RequestBody ResetOpenHistoryArg arg){
        ResetOpenHistoryVo vo = BeanUtil.copy(arg, ResetOpenHistoryVo.class);
        return accountBookService.resetOpenHistory(vo);
    }

}
