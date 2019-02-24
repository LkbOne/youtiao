package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.result.QueryBudgetByIdResult;
import com.example.phoebe.youtiao.api.result.QueryExpensesByIdResult;
import com.example.phoebe.youtiao.api.vo.expenses.AddExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.DeleteExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.QueryExpensesByIdVo;
import com.example.phoebe.youtiao.api.vo.expenses.UpdateExpensesVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.ExpensesDao;
import com.example.phoebe.youtiao.dao.entity.BudgetEntity;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("expensesService")
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    ExpensesDao expensesDao;

    @Override
    public ModelResult addExpenses(AddExpensesVo vo) {
        ExpensesEntity expensesEntity = BeanUtil.copy(vo, ExpensesEntity.class);
        String id = UUIDUtil.getUUID();
        expensesEntity.setId(id); // 需要传进来一个，要写一个aop
        if(expensesDao.addExpenses(expensesEntity) != 1){
            return new ModelResult(SHErrorCode.ADD_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult updateExpenses(UpdateExpensesVo vo) {
        ExpensesEntity expensesEntity = expensesDao.queryExpensesById(vo.getId());
        if(null == expensesEntity){
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        expensesEntity.setInType(vo.getInType());
        expensesEntity.setOutType(vo.getOutType());
        expensesEntity.setExpenses(vo.getExpenses());
        expensesEntity.setName(vo.getName());
        if(expensesDao.updateExpenses(expensesEntity) != 1){
            return new ModelResult(SHErrorCode.UPDATE_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);

    }

    @Override
    public ModelResult deleteExpensesById(DeleteExpensesVo vo) {
        if(expensesDao.deleteExpensesById(vo.getId()) != 1){
            return new ModelResult(SHErrorCode.DEL_FAIL);
        }
        return new ModelResult(SHErrorCode.SUCCESS);
    }

    @Override
    public ModelResult<QueryExpensesByIdResult> queryExpensesById(QueryExpensesByIdVo vo) {
        ExpensesEntity expensesEntity = expensesDao.queryExpensesById(vo.getId());
        if(null == expensesEntity){
            return new ModelResult(SHErrorCode.NO_DATA);
        }
        QueryExpensesByIdResult result = BeanUtil.copy(expensesEntity, QueryExpensesByIdResult.class);
        result.setCreateTime(expensesEntity.getCreateTime().getTime());
        result.setLastModifyTime(expensesEntity.getLastModifyTime().getTime());
        return new ModelResult<QueryExpensesByIdResult>(SHErrorCode.SUCCESS, result);

    }

    @Override
    public ModelResult listExpenses() {
        return null;
    }
}
