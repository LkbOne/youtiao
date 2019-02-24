package com.example.phoebe.youtiao.controler;

import com.example.phoebe.youtiao.api.AccountBookService;
import com.example.phoebe.youtiao.api.vo.accountBook.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controler.arg.*;
import com.example.phoebe.youtiao.service.impl.AccountBookServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountBook")
@Slf4j
public class AccountBookController {
    @Autowired
    AccountBookService accountBookService;

    @ApiOperation(value = "添加账本")
    @RequestMapping(value = "addAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addAccountBook(@RequestBody AddAccountBookArg arg){
        if(arg.isWrongParams()){
            log.warn("");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        AddAccountBookVo vo = BeanUtil.copy(arg, AddAccountBookVo.class);
        return accountBookService.addAccountBook(vo);
    }

    @ApiOperation(value = "更新账本")
    @RequestMapping(value = "updateAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult updateAccountBook(@RequestBody UpdateAccountBookArg arg){
        if(arg.isWrongParams()){
            log.warn("");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        UpdateAccountBookVo vo = BeanUtil.copy(arg, UpdateAccountBookVo.class);
        return accountBookService.updateAccountBook(vo);
    }

    @ApiOperation(value = "删除账本")
    @RequestMapping(value = "deleteAccountBookById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult deleteAccountBookById(@RequestBody DeleteAccountBookByIdArg arg){
        if(arg.isWrongParams()){
            log.warn("");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        DeleteAccountBookVo vo = BeanUtil.copy(arg, DeleteAccountBookVo.class);
        return accountBookService.deleteAccountBookById(vo);
    }

    @ApiOperation(value = "通过id获得账本详情")
    @RequestMapping(value = "getAccountBookById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult getAccountBookById(@RequestBody GetAccountBookByIdArg arg){
        if(arg.isWrongParams()){
            log.warn("");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        GetAccountBookByIdVo vo = BeanUtil.copy(arg, GetAccountBookByIdVo.class);
        return accountBookService.getAccountBookById(vo);
    }

    @ApiOperation(value = "展示所有的账本")
    @RequestMapping(value = "listAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listAccountBook(@RequestBody ListAccountBookArg arg){
        if(arg.isWrongParams()){
            log.warn("");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        ListAccountBookVo vo = BeanUtil.copy(arg, ListAccountBookVo.class);
        return accountBookService.listAccountBook(vo);
    }

}
