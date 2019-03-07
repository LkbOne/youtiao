package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.result.ListExpensesByAccountBookIdResult;
import com.example.phoebe.youtiao.api.result.QueryExpensesByIdResult;
import com.example.phoebe.youtiao.api.vo.expenses.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.AccountBookDao;
import com.example.phoebe.youtiao.dao.api.ExpensesDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("expensesService")
@Slf4j
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    ExpensesDao expensesDao;

    @Autowired
    AccountBookDao accountBookDao;
    @Override
    public ModelResult addExpenses(AddExpensesVo vo) {
        AccountBookEntity accountBookEntity = accountBookDao.queryAccountBookById(vo.getAccountBookId());
        if(null == accountBookEntity){
            log.warn("ExpensesServiceImpl.addExpenses");
            return new ModelResult(SHErrorCode.NO_DATA);
        }

        ExpensesEntity expensesEntity = BeanUtil.copy(vo, ExpensesEntity.class);
        String id = UUIDUtil.getUUID();
        expensesEntity.setId(id);
        expensesEntity.setStatus(1);
        if(expensesDao.addExpenses(expensesEntity) != 1){
            log.warn("ExpensesServiceImpl.addExpenses");
            return new ModelResult(SHErrorCode.ADD_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateExpenses(UpdateExpensesVo vo) {
        ExpensesEntity expensesEntity = expensesDao.queryExpensesById(vo.getId());
        if(null == expensesEntity){
            log.warn("ExpensesServiceImpl.addExpenses");
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        expensesEntity.setType(vo.getType());
        expensesEntity.setClassification(vo.getClassification());
        expensesEntity.setExpenses(vo.getExpenses());
        expensesEntity.setName(vo.getName());
        expensesEntity.setDescription(vo.getDescription());
        if(expensesDao.updateExpenses(expensesEntity) != 1){
            log.warn("ExpensesServiceImpl.addExpenses");
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);

    }

    @Override
    public ModelResult deleteExpensesById(DeleteExpensesVo vo) {
        if(expensesDao.deleteExpensesById(vo.getId()) != 1){
            log.warn("ExpensesServiceImpl.addExpenses");
            return new ModelResult(SHErrorCode.DEL_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<QueryExpensesByIdResult> queryExpensesById(QueryExpensesByIdVo vo) {
        ExpensesEntity expensesEntity = expensesDao.queryExpensesById(vo.getId());
        if(null == expensesEntity){
            log.warn("ExpensesServiceImpl.addExpenses");
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        QueryExpensesByIdResult result = BeanUtil.copy(expensesEntity, QueryExpensesByIdResult.class);
        result.setCreateTime(expensesEntity.getCreateTime().getTime());
        result.setLastModifyTime(expensesEntity.getLastModifyTime().getTime());
        return new ModelResult<QueryExpensesByIdResult>(SHErrorCode.SUCCESS, result);

    }

    @Override
    public ModelResult<PageResult<ListExpensesByAccountBookIdResult>> listExpenses(ListExpensesVo vo) {
        System.out.println("ListAccountBookVo:" + vo.toString());
        System.out.println("size:" + vo.getPageSize());
        System.out.println("number:" + vo.getPageNum());
        Page page = new Page(vo.getPageNum(), vo.getPageSize(), true);
        List<ExpensesEntity> expensesEntities = expensesDao.listExpensesByAccountBookId(vo.getAccountId(), page);

        List<ListExpensesByAccountBookIdResult> expensesByAccountBookIdResultList = Lists.newArrayList();
        for (ExpensesEntity expensesEntity : expensesEntities) {
            System.out.println("accountBookList:" + expensesEntity.toString());
            ListExpensesByAccountBookIdResult expensesResult = BeanUtil.copy(expensesEntity, ListExpensesByAccountBookIdResult.class);
            expensesResult.setCreateTime(expensesEntity.getCreateTime().getTime());
            expensesResult.setLastModifyTime(expensesEntity.getLastModifyTime().getTime());
            expensesByAccountBookIdResultList.add(expensesResult);
        }
        PageResult<ListExpensesByAccountBookIdResult> pageResult = new PageResult<ListExpensesByAccountBookIdResult>();
        pageResult.setPageNum(vo.getPageNum());
        pageResult.setPageSize(vo.getPageSize());
        pageResult.setTotalCount(page.getPages());
        pageResult.setResult(expensesByAccountBookIdResultList);

        System.out.println("PageResult:" + pageResult.toString());
        return new ModelResult<>(SHErrorCode.SUCCESS, pageResult);
    }
}
