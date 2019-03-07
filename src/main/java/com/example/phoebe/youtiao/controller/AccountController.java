package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.AccountService;
import com.example.phoebe.youtiao.api.result.LoginResult;
import com.example.phoebe.youtiao.api.vo.account.LoginVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.controller.arg.Account.LoginArg;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "登陆")
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<LoginResult> login(@RequestBody LoginArg arg){
        if(arg.isWrongParams()){
            log.warn("AccountController.login");
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        LoginVo vo = BeanUtil.copy(arg, LoginVo.class);
        return accountService.login(vo);
    }
}
