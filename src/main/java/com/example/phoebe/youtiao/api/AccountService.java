package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.LoginResult;
import com.example.phoebe.youtiao.api.result.QueryCustomDataByIdResult;
import com.example.phoebe.youtiao.api.result.RegisterResult;
import com.example.phoebe.youtiao.api.vo.account.*;
import com.example.phoebe.youtiao.commmon.ModelResult;

public interface AccountService {
    ModelResult<LoginResult> login(LoginVo vo);

    ModelResult UpdateCustomData(UpdateCustomDataVo vo);

    ModelResult<QueryCustomDataByIdResult> queryCustomDataById(QueryCustomDataByIdVo vo);

    ModelResult<RegisterResult> register(RegisterVo vo);
}
