package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.AccountService;
import com.example.phoebe.youtiao.api.result.YouTiaoBindResult;
import com.example.phoebe.youtiao.api.vo.account.LoginVo;
import com.example.phoebe.youtiao.api.vo.account.YouTiaoBindVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.TokenUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.AccountDao;
import com.example.phoebe.youtiao.dao.api.WxAccountDao;
import com.example.phoebe.youtiao.dao.entity.AccountEntity;
import com.example.phoebe.youtiao.dao.entity.WxAccountEntity;
import com.example.phoebe.youtiao.service.manager.AccountManager;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("accountService")
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountManager accountManager;

    @Autowired
    AccountDao accountDao;

    @Autowired
    WxAccountDao wxAccountDao;

    @Override
    public ModelResult login(LoginVo vo) {
        Map<String, String> claims = Maps.newHashMap();
        claims.put("one","two");
        String token = TokenUtil.genToken(claims);
        System.out.println("token:"+token);
        Map<String, String> verifyToken = TokenUtil.verifyToken(token);
        System.out.println("value:"+ verifyToken.get("one"));
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<YouTiaoBindResult> youTiaoBind(YouTiaoBindVo vo) {
        System.out.println("vo:" + vo.getCode());
        AccountManager.WxAuth wxAuth = accountManager.getWxSession(vo.getCode());
        if(wxAuth == null || wxAuth.getOpenid() == null ){
            log.warn("AccountServiceImpl.youTiaoBind get openid by code fail code:{} ", vo.getCode());
            return new ModelResult<>(SHErrorCode.WX_USER_NOFOUND);
        }
        WxAccountEntity exsitWxAccountEntity = wxAccountDao.queryWxAccountByOpenid(wxAuth.getOpenid());
        if(null != exsitWxAccountEntity && StringUtils.isNotEmpty(exsitWxAccountEntity.getAccountId())){
            exsitWxAccountEntity.setWxName(vo.getWxName());
            exsitWxAccountEntity.setAvatarUrl(vo.getAvatarUrl());
            if(wxAccountDao.updateWxAccount(exsitWxAccountEntity) != 1){
                log.warn("AccountServiceImpl.youTiaoBind update WxAccount fail exsitWxAccountEntity:{}", exsitWxAccountEntity);
            }
            YouTiaoBindResult result = new YouTiaoBindResult();
            result.setToken(TokenUtil.getTokenSetAccountId(exsitWxAccountEntity.getAccountId(), wxAuth.getSessionKey()));
            return new ModelResult<>(SHErrorCode.SUCCESS, result);
        }

        String accountId = UUIDUtil.getUUID();

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountId);
        accountEntity.setStatus(0);
        if(accountDao.addAccount(accountEntity) != 1){
            log.warn("AccountServiceImpl.youTiaoBind bind account by openid fail accountEntity:{}", accountEntity);
            return new ModelResult<>(SHErrorCode.USER_ADD_FAIL);
        }

        WxAccountEntity wxAccountEntity = BeanUtil.copy(vo, WxAccountEntity.class);
        wxAccountEntity.setAccountId(accountId);
        wxAccountEntity.setId(UUIDUtil.getUUID());
        wxAccountEntity.setOpenid(wxAuth.getOpenid());
        if(wxAccountDao.addWxAccount(wxAccountEntity)!=1){
            log.warn("AccountServiceImpl.youTiaoBind  bind account by openid fail wxAccountEntity:{}", wxAccountEntity);
            return new ModelResult<>(SHErrorCode.USER_ADD_FAIL);
        }

        YouTiaoBindResult result = new YouTiaoBindResult();
        result.setToken(TokenUtil.getTokenSetAccountId(accountId, wxAuth.getSessionKey()));
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }
}
