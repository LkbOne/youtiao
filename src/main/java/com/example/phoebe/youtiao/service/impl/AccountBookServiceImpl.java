package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.AccountBookService;
import com.example.phoebe.youtiao.api.result.GetAccountBookByIdResult;
import com.example.phoebe.youtiao.api.result.ListAccountBookResult;
import com.example.phoebe.youtiao.api.vo.accountBook.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.dao.api.AccountBookDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.service.manager.AccountBookManager;
import com.github.pagehelper.Page;
import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountBookService")
public class AccountBookServiceImpl implements AccountBookService {

    @Autowired
    AccountBookDao accountBookDao;
    @Autowired
    AccountBookManager accountBookManager;

    @Override
    public ModelResult addAccountBook(AddAccountBookVo vo) {
        AccountBookEntity accountBookEntity = BeanUtil.copy(vo, AccountBookEntity.class);
        accountBookEntity.setAid(vo.getAccountId());
        accountBookEntity.setStatus(1);
        if(!accountBookManager.addAccountBook(accountBookEntity)){
            return new ModelResult(SHErrorCode.ADD_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateAccountBook(UpdateAccountBookVo vo) {
        AccountBookEntity accountBookEntity = accountBookDao.queryAccountBookById(vo.getId());
        if(null == accountBookEntity){
            return new ModelResult(SHErrorCode.NO_DATA);
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
            return new ModelResult<>(SHErrorCode.NO_DATA);
        }
        GetAccountBookByIdResult result = BeanUtil.copy(accountBookEntity, GetAccountBookByIdResult.class);
        result.setCreateTime(accountBookEntity.getCreateTime().getTime());
        result.setLastModifyTime(accountBookEntity.getLastModifyTime().getTime());
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ModelResult<PageResult<ListAccountBookResult>> listAccountBook(ListAccountBookVo vo) {
        System.out.println("ListAccountBookVo:" + vo.toString());
        System.out.println("size:" + vo.getPageSize());
        System.out.println("number:" + vo.getPageNum());
        Page page = new Page(vo.getPageNum(), vo.getPageSize(), true);
        List<AccountBookEntity> accountBookLists = accountBookDao.listAccountBookByAccountId(vo.getAccountId(), page);

        List<ListAccountBookResult> accountBookResults = Lists.newArrayList();
        for (AccountBookEntity accountBook : accountBookLists) {
            System.out.println("accountBookList:" + accountBook.toString());
            ListAccountBookResult listAccountBookResult = BeanUtil.copy(accountBook, ListAccountBookResult.class);
            listAccountBookResult.setCreateTime(accountBook.getCreateTime().getTime());
            listAccountBookResult.setLastModifyTime(accountBook.getLastModifyTime().getTime());
            accountBookResults.add(listAccountBookResult);
        }
        PageResult<ListAccountBookResult> pageResult = new PageResult<ListAccountBookResult>();
        pageResult.setPageNum(vo.getPageNum());
        pageResult.setPageSize(vo.getPageSize());
        pageResult.setTotalCount(page.getPages());
        pageResult.setResult(accountBookResults);
        System.out.println("PageResult:" + pageResult.toString());
        return new ModelResult<>(SHErrorCode.SUCCESS, pageResult);
    }

    @Override
    public ModelResult resetOpenHistory(ResetOpenHistoryVo vo){
        System.out.println("resetOpenHistory accountBookId" + vo.getAccountBookId());
        accountBookDao.updateAccountBookOpenHistory(1, 0);
        accountBookDao.updateAccountBookOpenHistoryById(1,vo.getAccountBookId());
        return new ModelResult(SHErrorCode.SUCCESS);
    }
}
