package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.BudgetService;
import com.example.phoebe.youtiao.api.result.*;
import com.example.phoebe.youtiao.api.vo.budget.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.AccountBookDao;
import com.example.phoebe.youtiao.dao.api.BudgetDao;
import com.example.phoebe.youtiao.dao.api.TotalBudgetDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.BudgetEntity;
import com.example.phoebe.youtiao.dao.entity.TotalBudgetEntity;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.runtime.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("budgetService")
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetDao budgetDao;

    @Autowired
    AccountBookDao accountBookDao;
    @Autowired
    TotalBudgetDao totalBudgetDao;

    @Override
    public ModelResult addBudget(AddbudgetVo vo) {
        AccountBookEntity accountBookEntity = accountBookDao.queryAccountBookById(vo.getAccountBookId());
        if(null == accountBookEntity){
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        TotalBudgetEntity totalBudget  = totalBudgetDao.queryTotalBudgetByAccountBookId(vo.getAccountBookId());

        if(totalBudget == null){
            totalBudget = new TotalBudgetEntity();
            totalBudget.setAccountBookId(vo.getAccountBookId());
            totalBudget.setBeginTime(vo.getBeginTime());
            totalBudget.setEndTime(vo.getEndTime());
            totalBudget.setTotalBudget(vo.getBudget());
            totalBudget.setWarnMoney(vo.getWarnMoney());
            totalBudget.setId(UUIDUtil.getUUID());
            totalBudgetDao.addTotalBudget(totalBudget);
        }

        BudgetEntity budgetEntity = BeanUtil.copy(vo, BudgetEntity.class);
        budgetEntity.setId(UUIDUtil.getUUID());
        budgetEntity.setTotalBudgetId(totalBudget.getId());
        if(budgetDao.addBudget(budgetEntity) != 1){
            return new ModelResult(SHErrorCode.ADD_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateBudget(UpdateBudgetVo vo) {
        BudgetEntity budgetEntity = budgetDao.queryBudgetById(vo.getId());
        if(null == budgetEntity){
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        budgetEntity.setBudget(vo.getBudget());
        budgetEntity.setBeginTime(vo.getBeginTime());
        budgetEntity.setEndTime(vo.getEndTime());
        if(budgetDao.updateBudget(budgetEntity) != 1){
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);

    }

    @Override
    public ModelResult deleteBudgetById(DeleteBudgetVo vo) {
        if(budgetDao.deleteBudgetById(vo.getId()) != 1){
            return new ModelResult(SHErrorCode.DEL_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<QueryBudgetByIdResult> queryBudgetById(QueryBudgetByIdVo vo) {
        BudgetEntity budgetEntity = budgetDao.queryBudgetById(vo.getId());
        if(null == budgetEntity){
            return new ModelResult<>(SHErrorCode.NO_DATA);
        }
        QueryBudgetByIdResult result = BeanUtil.copy(budgetEntity, QueryBudgetByIdResult.class);
        result.setBeginTime(budgetEntity.getBeginTime().getTime());
        result.setEndTime(budgetEntity.getEndTime().getTime());
        result.setCreateTime(budgetEntity.getCreateTime().getTime());
        result.setLastModifyTime(budgetEntity.getLastModifyTime().getTime());
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }


    @Override
    public ModelResult<PageResult<ListBudgetByAccountIdResult>> listBudgetByAccountBookId(ListBudgetVo vo) {
        Page page = new Page(vo.getPageNum(), vo.getPageSize(), true);
        List<BudgetEntity> budgetList = budgetDao.listBudgetByAccountBookId(vo.getAccountBookId(), page);

        List<ListBudgetByAccountIdResult> listBudgetResults = Lists.newArrayList();
        for (BudgetEntity budget : budgetList) {
            ListBudgetByAccountIdResult listBudgetByAccountIdResult = BeanUtil.copy(budget, ListBudgetByAccountIdResult.class);
            listBudgetByAccountIdResult.setBeginTime(budget.getBeginTime().getTime());
            listBudgetByAccountIdResult.setEndTime(budget.getEndTime().getTime());

            listBudgetByAccountIdResult.setCreateTime(budget.getCreateTime().getTime());
            listBudgetByAccountIdResult.setLastModifyTime(budget.getLastModifyTime().getTime());
            listBudgetResults.add(listBudgetByAccountIdResult);
        }
        PageResult<ListBudgetByAccountIdResult> pageResult = new PageResult<ListBudgetByAccountIdResult>();
        pageResult.setPageNum(vo.getPageNum());
        pageResult.setPageSize(vo.getPageSize());
        pageResult.setTotalCount(page.getPages());
        pageResult.setResult(listBudgetResults);
        System.out.println("PageResult:" + pageResult.toString());
        return new ModelResult<>(SHErrorCode.SUCCESS, pageResult);
    }
}
