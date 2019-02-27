package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.vo.account.LoginVo;
import com.example.phoebe.youtiao.api.vo.account.YouTiaoBindVo;
import com.example.phoebe.youtiao.commmon.ModelResult;

public interface AccountService {
    ModelResult login(LoginVo vo);
    ModelResult youTiaoBind(YouTiaoBindVo vo);
}
