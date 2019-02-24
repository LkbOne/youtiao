package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.AccountBookService;
import com.example.phoebe.youtiao.api.result.GetAccountBookByIdResult;
import com.example.phoebe.youtiao.api.result.ListAccountBookResult;
import com.example.phoebe.youtiao.api.vo.accountBook.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.AccountBookDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountBookService")
public class AccountBookServiceImpl implements AccountBookService {

    @Autowired
    AccountBookDao accountBookDao;

    @Override
    public ModelResult addAccountBook(AddAccountBookVo vo) {
        AccountBookEntity accountBookEntity = BeanUtil.copy(vo, AccountBookEntity.class);
        String id = UUIDUtil.getUUID();
        accountBookEntity.setId(id);
        accountBookEntity.setAid(UUIDUtil.getUUID());  // 需要传进来一个，要写一个aop
        if(accountBookDao.addAccountBook(accountBookEntity) != 1){
            return new ModelResult(SHErrorCode.ADD_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateAccountBook(UpdateAccountBookVo vo) {
        AccountBookEntity accountBookEntity = accountBookDao.queryAccountBookById(vo.getId());
        if(null == accountBookEntity){
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        accountBookEntity.setColor(vo.getColor());
        accountBookEntity.setName(vo.getName());
        accountBookEntity.setStatus(vo.getStatus());
        if(accountBookDao.updateAccountBook(accountBookEntity) != 1){
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult deleteAccountBookById(DeleteAccountBookVo vo) {
        if(accountBookDao.deleteAccountBookById(vo.getId()) != 1){
            return new ModelResult(SHErrorCode.DEL_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<GetAccountBookByIdResult> getAccountBookById(GetAccountBookByIdVo vo) {
        AccountBookEntity accountBookEntity = accountBookDao.queryAccountBookById(vo.getId());
        if(null == accountBookEntity){
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        GetAccountBookByIdResult result = BeanUtil.copy(accountBookEntity, GetAccountBookByIdResult.class);
        result.setCreateTime(accountBookEntity.getCreateTime().getTime());
        result.setLastModifyTime(accountBookEntity.getLastModifyTime().getTime());
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult<ListAccountBookResult> listAccountBook(ListAccountBookVo vo) {
        return null;
    }
}
