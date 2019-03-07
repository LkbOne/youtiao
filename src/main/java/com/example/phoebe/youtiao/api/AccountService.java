package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.LoginResult;
import com.example.phoebe.youtiao.api.vo.account.LoginVo;
import com.example.phoebe.youtiao.api.vo.account.YouTiaoBindVo;
import com.example.phoebe.youtiao.commmon.ModelResult;

public interface AccountService {
    ModelResult<LoginResult> login(LoginVo vo);
}
