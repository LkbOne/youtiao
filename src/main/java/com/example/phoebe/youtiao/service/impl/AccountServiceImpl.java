package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.AccountService;
import com.example.phoebe.youtiao.api.result.LoginResult;
import com.example.phoebe.youtiao.api.result.QueryCustomDataByIdResult;
import com.example.phoebe.youtiao.api.result.RegisterResult;
import com.example.phoebe.youtiao.api.vo.account.LoginVo;
import com.example.phoebe.youtiao.api.vo.account.QueryCustomDataByIdVo;
import com.example.phoebe.youtiao.api.vo.account.RegisterVo;
import com.example.phoebe.youtiao.api.vo.account.UpdateCustomDataVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.model.RedisLoginEntity;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.RedisUtil;
import com.example.phoebe.youtiao.commmon.util.TokenUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.controller.arg.Account.UpdateCustomDataArg;
import com.example.phoebe.youtiao.dao.api.AccountDao;
import com.example.phoebe.youtiao.dao.api.WxAccountDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.AccountEntity;
import com.example.phoebe.youtiao.dao.entity.WxAccountEntity;
import com.example.phoebe.youtiao.service.manager.AccountBookManager;
import com.example.phoebe.youtiao.service.manager.AccountManager;
import com.example.phoebe.youtiao.service.manager.RedisManager;
import com.google.gson.Gson;
import com.sun.tools.internal.ws.processor.modeler.Modeler;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountManager accountManager;

    @Autowired
    AccountDao accountDao;

    @Autowired
    WxAccountDao wxAccountDao;

    @Autowired
    RedisManager redisManager;

    @Autowired
    AccountBookManager accountBookManager;

    Gson gs = new Gson();

    @Override
    public ModelResult<LoginResult> login(LoginVo vo) {
        AccountManager.WxAuth wxAuth = accountManager.getWxSession(vo.getCode());
        if(wxAuth == null || wxAuth.getOpenid() == null){
            log.warn("AccountServiceImpl.login get openid by code fail code:{} ", vo.getCode());
            return new ModelResult<>(SHErrorCode.WX_USER_NOFOUND);
        }

        WxAccountEntity existWxAccountEntity = wxAccountDao.queryWxAccountByOpenid(wxAuth.getOpenid());
        if(null != existWxAccountEntity && StringUtils.isNotEmpty(existWxAccountEntity.getAccountId())) {
            existWxAccountEntity.setWxName(vo.getWxName());
            existWxAccountEntity.setAvatarUrl(vo.getAvatarUrl());
            if (wxAccountDao.updateWxAccount(existWxAccountEntity) != 1) {
                log.warn("AccountServiceImpl.login update WxAccount fail existWxAccountEntity:{}", existWxAccountEntity);
                return new ModelResult<>(SHErrorCode.LOGIN_NEED_RELOGIN);
            }

            String token = doInitToken(wxAuth, existWxAccountEntity.getAccountId());

            LoginResult result = new LoginResult();
            if (StringUtils.isBlank(token)) {
                log.warn("AccountServiceImpl.login token is empty  wxAuth:{} existWxAccountEntity:{}", wxAuth, existWxAccountEntity);
                return new ModelResult<>(SHErrorCode.LOGIN_NEED_RELOGIN);
            }

            result.setToken(doInitToken(wxAuth, existWxAccountEntity.getAccountId()));
            result.setAccountId(existWxAccountEntity.getAccountId());
            return new ModelResult<>(SHErrorCode.SUCCESS, result);
        }
        return new ModelResult<>(SHErrorCode.LOGIN_FAIL);
    }

    private String doInitToken(AccountManager.WxAuth wxAuth, String accountId){
        String token = TokenUtil.generateToken(wxAuth.getOpenid(), wxAuth.getSession_key(), accountId);
        if(StringUtils.isNotBlank(token)) {
            RedisLoginEntity redisLoginEntity = new RedisLoginEntity();
            redisLoginEntity.setSessionKey(wxAuth.getSession_key());
            System.out.println("redisLoginEntity:"+ redisLoginEntity.toString());
            redisManager.setToken2Redis(wxAuth.getOpenid(), gs.toJson(redisLoginEntity));
        }
        return token;
    }

    @Transactional
    public ModelResult<RegisterResult> register(RegisterVo vo){
        AccountManager.WxAuth wxAuth = accountManager.getWxSession(vo.getCode());
        if(wxAuth == null || wxAuth.getOpenid() == null){
            log.warn("AccountServiceImpl.register get openid by code fail code:{} ", vo.getCode());
            return new ModelResult<>(SHErrorCode.WX_USER_NOFOUND);
        }

        WxAccountEntity existWxAccountEntity = wxAccountDao.queryWxAccountByOpenid(wxAuth.getOpenid());
        if(existWxAccountEntity != null){
            log.warn("AccountServiceImpl.register get openid by code fail code:{} ", vo.getCode());
            return new ModelResult<>(SHErrorCode.USER_ACCOUNT_EXISTED);
        }

        String accountId = UUIDUtil.getUUID();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountId);
        accountEntity.setStatus(0);
        String token = doInitToken(wxAuth, accountId);

        if(StringUtils.isBlank(token)){
            log.warn("AccountServiceImpl.register token is empty  wxAuth:{} wxAccountEntity:{}", wxAuth, accountId);
            return new ModelResult<>(SHErrorCode.REGISTER_NEED_AGAIN);
        }

        if(accountDao.addAccount(accountEntity) != 1){
            log.warn("AccountServiceImpl.register bind account by openid fail accountEntity:{}", accountEntity);
            return new ModelResult<>(SHErrorCode.REGISTER_NEED_AGAIN);
        }
        WxAccountEntity wxAccountEntity = BeanUtil.copy(vo, WxAccountEntity.class);
        wxAccountEntity.setAccountId(accountId);
        wxAccountEntity.setId(UUIDUtil.getUUID());
        wxAccountEntity.setOpenid(wxAuth.getOpenid());

        if(wxAccountDao.addWxAccount(wxAccountEntity) != 1){
            log.warn("AccountServiceImpl.register  bind account by openid fail wxAccountEntity:{}", wxAccountEntity);
            return new ModelResult<>(SHErrorCode.REGISTER_NEED_AGAIN);
        }

        AccountBookEntity accountBookEntity = new AccountBookEntity();
        accountBookEntity.setName("日常账本");
        accountBookEntity.setColor(1);
        accountBookEntity.setAid(accountId);
        accountBookEntity.setStatus(1);
        if(!accountBookManager.addAccountBook(accountBookEntity)){
            log.warn("AccountServiceImpl.login insert account book fail accountBookEntity:{}", accountBookEntity);
            return new ModelResult<>(SHErrorCode.REGISTER_NEED_AGAIN);
        }
        RegisterResult result = new RegisterResult();
        result.setToken(token);
        result.setAccountId(wxAccountEntity.getAccountId());

        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult UpdateCustomData(UpdateCustomDataVo vo){
        AccountEntity entity = accountDao.queryAccountById(vo.getAccountId());
        if(null == entity){
            log.warn("AccountServiceImpl.UpdateCustomData entity is empty vo:{}", vo);
            return new ModelResult(SHErrorCode.USER_ACCOUNT_NOT_EXIST);
        }
        AccountEntity updateEntity = BeanUtil.copy(vo, AccountEntity.class);
        updateEntity.setId(vo.getAccountId());
        accountDao.updateAccount(updateEntity);
        return ModelResult.newSuccess();
    }
    @Override
    public ModelResult<QueryCustomDataByIdResult> queryCustomDataById(QueryCustomDataByIdVo vo){
        AccountEntity entity = accountDao.queryAccountById(vo.getAccountId());
        if(null == entity){
            log.warn("AccountServiceImpl.UpdateCustomData entity is empty vo:{}", vo);
            return new ModelResult<>(SHErrorCode.USER_ACCOUNT_NOT_EXIST);
        }
        QueryCustomDataByIdResult result = BeanUtil.copy(entity, QueryCustomDataByIdResult.class);
        return new ModelResult<QueryCustomDataByIdResult>(SHErrorCode.SUCCESS, result);
    }
}
