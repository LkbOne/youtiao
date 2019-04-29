package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.AccountService;
import com.example.phoebe.youtiao.api.result.AuthorizeResult;
import com.example.phoebe.youtiao.api.result.QueryCustomDataByIdResult;
import com.example.phoebe.youtiao.api.result.RegisterResult;
import com.example.phoebe.youtiao.api.vo.account.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.model.RedisLoginEntity;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.TokenUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.AccountDao;
import com.example.phoebe.youtiao.dao.api.WxAccountDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.AccountEntity;
import com.example.phoebe.youtiao.dao.entity.WxAccountEntity;
import com.example.phoebe.youtiao.service.manager.AccountBookManager;
import com.example.phoebe.youtiao.service.manager.AccountManager;
import com.example.phoebe.youtiao.service.manager.QiNiuManager;
import com.example.phoebe.youtiao.service.manager.RedisManager;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    QiNiuManager qiNiuManager;

    Gson gs = new Gson();

    @Override
    public ModelResult<AuthorizeResult> authorize(AuthorizeVo vo) {
        AccountManager.WxAuth wxAuth = accountManager.getWxSession(vo.getCode());
        if(wxAuth == null || wxAuth.getOpenid() == null){
            log.warn("AccountServiceImpl.authorize get openid by code fail code:{} ", vo.getCode());
            return new ModelResult<>(SHErrorCode.WX_USER_NOFOUND);
        }

        WxAccountEntity existWxAccountEntity = wxAccountDao.queryWxAccountByOpenid(wxAuth.getOpenid());
        if(null != existWxAccountEntity && StringUtils.isNotEmpty(existWxAccountEntity.getAccountId())) {
            existWxAccountEntity.setWxName(vo.getWxName());
            existWxAccountEntity.setAvatarUrl(vo.getAvatarUrl());
            if (wxAccountDao.updateWxAccount(existWxAccountEntity) != 1) {
                log.warn("AccountServiceImpl.authorize update WxAccount fail existWxAccountEntity:{}", existWxAccountEntity);
                return new ModelResult<>(SHErrorCode.LOGIN_NEED_RELOGIN);
            }

            String token = doInitToken(wxAuth, existWxAccountEntity.getAccountId());

            AuthorizeResult result = new AuthorizeResult();
            if (StringUtils.isBlank(token)) {
                log.warn("AccountServiceImpl.authorize token is empty  wxAuth:{} existWxAccountEntity:{}", wxAuth, existWxAccountEntity);
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

        if(accountDao.countAccountByAccount(vo.getAccount()) == 1){
            log.warn("AccountServiceImpl.register account existed account:{} ", vo.getAccount());
            return new ModelResult<>(SHErrorCode.ACCOUNT_EXIST);
        }

        String accountId = UUIDUtil.getUUID();
        AccountEntity accountEntity = BeanUtil.copy(vo, AccountEntity.class);


        String fPath = qiNiuManager.changeQiNiuFileName(vo.getAvatarTPath());
        accountEntity.setAvatarFPath(fPath);
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
            log.warn("AccountServiceImpl.authorize insert account book fail accountBookEntity:{}", accountBookEntity);
            return new ModelResult<>(SHErrorCode.REGISTER_NEED_AGAIN);
        }
        RegisterResult result = new RegisterResult();
        result.setToken(token);
        result.setAccountId(wxAccountEntity.getAccountId());

        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult login(LoginVo vo) {
        int userCount = accountDao.queryAccountByAccountAndPassword(vo.getAccount(),
                vo.getPassword());

        if(userCount == 0){
            return new ModelResult(SHErrorCode.LOGIN_FAIL);
        }

        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult UpdateCustomData(UpdateCustomDataVo vo){
        AccountEntity entity = accountDao.queryAccountById(vo.getAccountId());
        if(null == entity){
            log.warn("AccountServiceImpl.UpdateCustomData entity is empty vo:{}", vo);
            return new ModelResult(SHErrorCode.USER_ACCOUNT_NOT_EXIST);
        }
        if(accountDao.queryAccountByAccountAndPassword(entity.getAccount(), vo.getPassword()) == 0){
            return new ModelResult(SHErrorCode.CHANGE_PASSWORD_FAIL);
        }
        if(vo.getPath().startsWith("TE")){
            String fPath = qiNiuManager.changeQiNiuFileName(vo.getPath());
            if(StringUtils.isEmpty(fPath)){
                log.warn("AccountServiceImpl.UpdateCustomData fPath:{}", fPath);
                return new ModelResult(SHErrorCode.UPDATE_FAIL);
            }
            if(!qiNiuManager.deleteFile(entity.getAvatarFPath())){
                log.warn("AccountServiceImpl.UpdateCustomData fPath:{}, " +
                        "entity.getAvatarFPath:{}", fPath, entity.getAvatarFPath());
                return new ModelResult(SHErrorCode.UPDATE_FAIL);
            }
            entity.setAvatarFPath(fPath);
        }

        entity.setPassword(vo.getNewPassword());
        entity.setName(vo.getName());
        entity.setRealName(vo.getRealName());
        entity.setPhone(vo.getPhone());
        entity.setSignature(vo.getSignature());
        accountDao.updateAccount(entity);
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
