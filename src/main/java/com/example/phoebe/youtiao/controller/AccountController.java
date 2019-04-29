package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.AccountService;
import com.example.phoebe.youtiao.api.result.AuthorizeResult;
import com.example.phoebe.youtiao.api.vo.account.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.annotion.TokenCheckTrigger;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controller.arg.Account.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "hello", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ModelResult hello(){
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @RequestMapping(value = "authorize", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<AuthorizeResult> authorize(@RequestBody AuthorizeArg arg){
        if(arg.isWrongParams()){
            log.warn("AccountController.authorize params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        AuthorizeVo vo = BeanUtil.copy(arg, AuthorizeVo.class);
        return accountService.authorize(vo);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult login(@RequestBody LoginArg arg){
        if(arg.isWrongParams()){
            log.warn("AccountController.login params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        LoginVo vo = BeanUtil.copy(arg, LoginVo.class);
        return accountService.login(vo);
    }


    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult register(@RequestBody RegisterArg arg){
        if(arg.isWrongParams()){
            log.warn("AccountController.register params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        RegisterVo vo = BeanUtil.copy(arg, RegisterVo.class);
        return accountService.register(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "updateCustomData", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ModelResult updateCustomData(@RequestHeader String token, @RequestBody UpdateCustomDataArg arg){
        UpdateCustomDataVo vo = BeanUtil.copy(arg, UpdateCustomDataVo.class);
        return accountService.UpdateCustomData(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "queryCustomDataById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult queryCustomDataById(@RequestHeader String token, @RequestBody QueryCustomDataByIdArg arg){
        QueryCustomDataByIdVo vo = BeanUtil.copy(arg, QueryCustomDataByIdVo.class);
        return accountService.queryCustomDataById(vo);
    }
}
