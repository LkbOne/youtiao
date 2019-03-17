package com.example.phoebe.youtiao.account;

import com.example.phoebe.youtiao.api.AccountService;
import com.example.phoebe.youtiao.api.result.QueryCustomDataByIdResult;
import com.example.phoebe.youtiao.api.vo.account.QueryCustomDataByIdVo;
import com.example.phoebe.youtiao.api.vo.account.UpdateCustomDataVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Ignore
public class Account {

    @Autowired
    AccountService accountService;

    @Test
    public void queryCustomDataById(){
        QueryCustomDataByIdVo vo = new QueryCustomDataByIdVo();
        vo.setAccountId("0f197195c0c5447895019597a6305854");
        ModelResult<QueryCustomDataByIdResult> result = accountService.queryCustomDataById(vo);
        System.out.println();
    }

    @Test
    public void updateCustomData(){
        UpdateCustomDataVo vo = new UpdateCustomDataVo();
        vo.setAccountId("0f197195c0c5447895019597a6305854");
        vo.setName("one");
        vo.setPhone("13192265868");
        vo.setRealName("two");
        vo.setSignature("this is a signature");
        ModelResult result = accountService.UpdateCustomData(vo);
        System.out.println();
    }
}
