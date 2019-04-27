package com.example.phoebe.youtiao.service.manager;

import com.example.phoebe.youtiao.api.dto.ExpensesGroupClassificationDto;
import com.example.phoebe.youtiao.api.dto.SumInAndOutExpensesDto;
import com.example.phoebe.youtiao.commmon.util.DateUtil;
import com.example.phoebe.youtiao.dao.api.ExpensesDao;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpensesManager {
    @Autowired
    ExpensesDao expensesDao;

    private void doInitDate(Date beginDate, Date endDate, Integer distance){
        if(null == endDate){
            endDate = new Date();
        }
        if(null == beginDate && distance != null){
            beginDate =  DateUtil.getBeginDate(endDate, distance);
        }
    }
    public List<ExpensesEntity> listExpensesFromDayToDay(String accountBookId, Date beginDate, Date endDate, Integer distance, Page page){
        doInitDate(beginDate, endDate, distance);

        List<ExpensesEntity> expensesEntityList = expensesDao.listExpensesByAccountBookIdByExpenseDay(accountBookId, beginDate, endDate, page);
        if(CollectionUtils.isEmpty(expensesEntityList)){
            return Lists.newArrayList();
        }
        return expensesEntityList;
    }

    public SumInAndOutExpensesDto sumInAndOutExpenses(String accountBookId, Date beginDate, Date endDate, Integer distance){
        doInitDate(beginDate, endDate, distance);
        Float totalInExpenses = expensesDao.sumExpenses(accountBookId, 0, null,beginDate, endDate);
        Float totalOutExpenses = expensesDao.sumExpenses(accountBookId, 1, null,beginDate, endDate);
        if(null == totalInExpenses){
            totalInExpenses = new Float(0);
        }
        if(null == totalOutExpenses){
            totalOutExpenses = new Float(0);
        }
        return new SumInAndOutExpensesDto(totalInExpenses, totalOutExpenses);
    }

    public List<ExpensesGroupClassificationDto> queryExpensesGroupClassificationByType(String accountBookId, Integer type, Date beginDate, Date endDate){
        List<ExpensesGroupClassificationDto> list = expensesDao.queryExpensesGroupClassificationByType(accountBookId, type, beginDate, endDate);
        return CollectionUtils.isEmpty(list) ? Lists.newArrayList() : list;
    }
}
