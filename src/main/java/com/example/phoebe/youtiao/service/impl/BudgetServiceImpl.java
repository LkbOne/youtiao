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
import com.example.phoebe.youtiao.dao.api.ExpensesDao;
import com.example.phoebe.youtiao.dao.api.TotalBudgetDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.BudgetEntity;
import com.example.phoebe.youtiao.dao.entity.TotalBudgetEntity;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("budgetService")
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetDao budgetDao;

    @Autowired
    AccountBookDao accountBookDao;
    @Autowired
    TotalBudgetDao totalBudgetDao;

    @Autowired
    ExpensesDao expensesDao;

    @Override
    public ModelResult addBudget(AddbudgetVo vo) {
        AccountBookEntity accountBookEntity = accountBookDao.queryAccountBookById(vo.getAccountBookId());
        if (null == accountBookEntity) {
            log.warn("BudgetServiceImpl.addBudget");
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        TotalBudgetEntity totalBudget = totalBudgetDao.queryTotalBudgetByAccountBookId(vo.getAccountBookId());

        if (totalBudget == null) {
            totalBudget = new TotalBudgetEntity();
            totalBudget.setAccountBookId(vo.getAccountBookId());
            totalBudget.setBeginTime(vo.getBeginTime());
            totalBudget.setEndTime(vo.getEndTime());
            totalBudget.setTotalBudget(vo.getBudget());
            totalBudget.setWarnMoney(vo.getWarnMoney());
            totalBudget.setId(UUIDUtil.getUUID());
            totalBudgetDao.addTotalBudget(totalBudget);
        }

        if(vo.getBeginTime().getTime() > totalBudget.getEndTime().getTime() || vo.getEndTime().getTime() < totalBudget.getBeginTime().getTime()){
            log.warn("BudgetServiceImpl.addBudget vo.getBeginTime():{}, vo.getEndTime():{}, " +
                    "totalBudget.getBeginTime():{},  totalBudget.getEndTime():{}", vo.getBeginTime(), vo.getEndTime(), totalBudget.getBeginTime(), totalBudget.getEndTime());
            return new ModelResult(SHErrorCode.NO_IN_TOTAL_BUDGET_TIME);
        }

        Float totalHasBudget = budgetDao.sumBudgetByTotalBudgetId(totalBudget.getId());
        totalHasBudget = totalHasBudget == null ? new Float(0) : totalHasBudget;
        if(totalHasBudget + vo.getBudget() > totalBudget.getTotalBudget()){
            log.warn("BudgetServiceImpl.addBudget vo:{}, totalHasBudget:{}, totalBudget:{}", vo, totalHasBudget, totalBudget);
            return new ModelResult(SHErrorCode.ADD_FAIL_MORE_THAN_TOTAL_BUDGET);
        }

        BudgetEntity budgetEntity = BeanUtil.copy(vo, BudgetEntity.class);
        budgetEntity.setId(UUIDUtil.getUUID());
        budgetEntity.setTotalBudgetId(totalBudget.getId());
        budgetDao.sumBudgetByTotalBudgetId(totalBudget.getId());
        if (budgetDao.addBudget(budgetEntity) != 1) {
            log.warn("BudgetServiceImpl.addBudget");
            return new ModelResult(SHErrorCode.ADD_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateBudget(UpdateBudgetVo vo) {
        BudgetEntity budgetEntity = budgetDao.queryBudgetById(vo.getId());
        if (null == budgetEntity) {
            log.warn("BudgetServiceImpl.updateBudget");
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        budgetEntity.setBudget(vo.getBudget());
        budgetEntity.setBeginTime(vo.getBeginTime());
        budgetEntity.setEndTime(vo.getEndTime());
        if (budgetDao.updateBudget(budgetEntity) != 1) {
            log.warn("BudgetServiceImpl.updateBudget");
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);

    }

    @Override
    public ModelResult deleteBudgetById(DeleteBudgetVo vo) {
        BudgetEntity budgetEntity = budgetDao.queryBudgetById(vo.getId());
        if (budgetDao.deleteBudgetById(vo.getId()) != 1) {
            log.warn("BudgetServiceImpl.deleteBudgetById vo:{}", vo);
            return new ModelResult(SHErrorCode.DEL_FAIL);
        }
        if (budgetDao.countBudgetByTotalBudgetId(budgetEntity.getTotalBudgetId()) == 0) {
            if (totalBudgetDao.deleteTotalBudgetById(budgetEntity.getTotalBudgetId()) != 1) {
                log.warn("BudgetServiceImpl.deleteBudgetById vo:{} ,budgetEntity:{}", vo, budgetEntity);
            }
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<QueryBudgetByIdResult> queryBudgetById(QueryBudgetByIdVo vo) {
        BudgetEntity budgetEntity = budgetDao.queryBudgetById(vo.getId());
        if (null == budgetEntity) {
            log.warn("BudgetServiceImpl.queryBudgetById");
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
    public ModelResult<PageResult<ListBudgetByAccountBookIdResult>> listBudgetByAccountBookId(ListBudgetVo vo) {
        Page page = new Page(vo.getPageNum(), vo.getPageSize(), true);
        List<BudgetEntity> budgetList = budgetDao.listBudgetByAccountBookId(vo.getAccountBookId(), page);

        List<ListBudgetByAccountBookIdResult> listBudgetResults = Lists.newArrayList();

        TotalBudgetEntity totalBudgetEntity = totalBudgetDao.queryTotalBudgetByAccountBookId(vo.getAccountBookId());
        ListBudgetByAccountBookIdResult totalBudgetByAccountIdResult = BeanUtil.copy(totalBudgetEntity, ListBudgetByAccountBookIdResult.class);
        Float spentMoney = expensesDao.sumExpenses(vo.getAccountBookId(), 1,null,totalBudgetEntity.getBeginTime(), totalBudgetEntity.getEndTime());
        totalBudgetByAccountIdResult.setSpentMoney(spentMoney != null ? spentMoney : 0);
        totalBudgetByAccountIdResult.setBudget(totalBudgetEntity.getTotalBudget());
        totalBudgetByAccountIdResult.setClassification(-1);
        totalBudgetByAccountIdResult.setBeginTime(totalBudgetEntity.getBeginTime().getTime());
        totalBudgetByAccountIdResult.setEndTime(totalBudgetEntity.getEndTime().getTime());
        totalBudgetByAccountIdResult.setCreateTime(totalBudgetEntity.getCreateTime().getTime());
        totalBudgetByAccountIdResult.setLastModifyTime(totalBudgetEntity.getLastModifyTime().getTime());
        listBudgetResults.add(totalBudgetByAccountIdResult);

        for (BudgetEntity budget : budgetList) {
            ListBudgetByAccountBookIdResult listBudgetByAccountIdResult = BeanUtil.copy(budget, ListBudgetByAccountBookIdResult.class);
            listBudgetByAccountIdResult.setBeginTime(budget.getBeginTime().getTime());
            listBudgetByAccountIdResult.setEndTime(budget.getEndTime().getTime());
            listBudgetByAccountIdResult.setCreateTime(budget.getCreateTime().getTime());
            spentMoney = expensesDao.sumExpenses(vo.getAccountBookId(), 1, budget.getClassification(), budget.getBeginTime(), budget.getEndTime());
            listBudgetByAccountIdResult.setSpentMoney(spentMoney != null ? spentMoney : 0);
            listBudgetByAccountIdResult.setLastModifyTime(budget.getLastModifyTime().getTime());
            listBudgetResults.add(listBudgetByAccountIdResult);
        }
        PageResult<ListBudgetByAccountBookIdResult> pageResult = new PageResult<ListBudgetByAccountBookIdResult>();
        pageResult.setPageNum(vo.getPageNum());
        pageResult.setPageSize(vo.getPageSize());
        pageResult.setTotalCount(page.getPages());
        pageResult.setResult(listBudgetResults);
        return new ModelResult<>(SHErrorCode.SUCCESS, pageResult);
    }


    public ModelResult updateTotalBudget(UpdateTotalBudgetVo vo){
        TotalBudgetEntity totalBudget = totalBudgetDao.queryTotalBudgetById(vo.getId());
        if(null == totalBudget){
            log.warn("BudgetServiceImpl.updateTotalBudget vo:{}, totalBudget{}", vo, totalBudget);
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        totalBudget.setTotalBudget(vo.getTotalBudget());
        totalBudget.setWarnMoney(vo.getWarnMoney());
        totalBudget.setEndTime(vo.getEndTime());
        totalBudget.setBeginTime(vo.getBeginTime());
        if(totalBudgetDao.updateTotalBudget(totalBudget)!=1){
            log.warn("BudgetServiceImpl.updateTotalBudget vo:{}, totalBudget{}", vo, totalBudget);
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    public ModelResult addTotalBudget(AddTotalBudgetVo vo){
        TotalBudgetEntity totalBudget = totalBudgetDao.queryTotalBudgetByAccountBookId(vo.getAccountBookId());
        if(null != totalBudget){
            log.warn("BudgetServiceImpl.addTotalBudget vo:{}, totalBudget{}", vo, totalBudget);
            return new ModelResult<>(SHErrorCode.EXIST_TOTAL_BUDGET);
        }

        TotalBudgetEntity newTotalBudget = BeanUtil.copy(vo, TotalBudgetEntity.class);
        newTotalBudget.setId(UUIDUtil.getUUID());

        if(totalBudgetDao.addTotalBudget(newTotalBudget)!= 1){
            log.warn("BudgetServiceImpl.addTotalBudget vo:{}, totalBudget{}", vo, newTotalBudget);
            return new ModelResult<>(SHErrorCode.ADD_FAIL);
        }

        return new ModelResult(SHErrorCode.SUCCESS);
    }
}
