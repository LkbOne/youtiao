package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.dto.ExpensesGroupClassificationDto;
import com.example.phoebe.youtiao.api.dto.SumInAndOutExpensesDto;
import com.example.phoebe.youtiao.api.result.*;
import com.example.phoebe.youtiao.api.vo.expenses.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.DateUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.AccountBookDao;
import com.example.phoebe.youtiao.dao.api.ExpensesDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import com.example.phoebe.youtiao.service.manager.ExpensesManager;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.joda.time.DateTime;

import javax.jws.WebParam;
import java.util.Date;
import java.util.List;

@Service("expensesService")
@Slf4j
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    ExpensesDao expensesDao;

    @Autowired
    AccountBookDao accountBookDao;

    @Autowired
    ExpensesManager expensesManager;

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
        expensesEntity.setExpenseDate(vo.getExpensesDate());
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
        result.setExpenseDate(expensesEntity.getExpenseDate().getTime());
        result.setCreateTime(expensesEntity.getCreateTime().getTime());
        result.setLastModifyTime(expensesEntity.getLastModifyTime().getTime());
        return new ModelResult<QueryExpensesByIdResult>(SHErrorCode.SUCCESS, result);

    }

    @Override
    public ModelResult<PageResult<ListExpensesByAccountBookIdResult>> listExpenses(ListExpensesVo vo) {
        Page page = new Page(vo.getPageNum(), vo.getPageSize(), true);
        List<ExpensesEntity> expensesEntities = expensesManager.listExpensesFromDayToDay(vo.getAccountBookId(), null,null, vo.getRecentDay(), page);

        List<ListExpensesByAccountBookIdResult> expensesByAccountBookIdResultList = Lists.newArrayList();
        for (ExpensesEntity expensesEntity : expensesEntities) {
            ListExpensesByAccountBookIdResult expensesResult = BeanUtil.copy(expensesEntity, ListExpensesByAccountBookIdResult.class);
            expensesResult.setExpenseDate(expensesEntity.getExpenseDate().getTime());
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


    public ModelResult<SumThisDayExpensesResult> sumThisDayExpenses(SumThisDayExpensesVo vo){
        Date beginDate =  DateUtil.getBeginDate(vo.getSearchDay(), 1);
        Page page = new Page(vo.getPageNum(), vo.getPageSize(), true);
        Float sumOutExpenses = expensesDao.sumExpenses(vo.getAccountBookId(),1, beginDate, vo.getSearchDay());
        Float sumInExpenses = expensesDao.sumExpenses(vo.getAccountBookId(),0, beginDate, vo.getSearchDay());

        List<ExpensesEntity> expensesEntities = expensesDao.listExpensesByAccountBookId(vo.getAccountBookId(), beginDate, new Date(), page);

        List<ListExpensesByAccountBookIdResult> expensesByAccountBookIdResultList = Lists.newArrayList();
        for (ExpensesEntity expensesEntity : expensesEntities) {
            System.out.println("accountBookList:" + expensesEntity.toString());
            ListExpensesByAccountBookIdResult expensesResult = BeanUtil.copy(expensesEntity, ListExpensesByAccountBookIdResult.class);
            expensesResult.setCreateTime(expensesEntity.getExpenseDate().getTime());
            expensesResult.setCreateTime(expensesEntity.getCreateTime().getTime());
            expensesResult.setLastModifyTime(expensesEntity.getLastModifyTime().getTime());
            expensesByAccountBookIdResultList.add(expensesResult);
        }

        SumThisDayExpensesResult result = new SumThisDayExpensesResult();
        result.setSumInExpenses(sumInExpenses);
        result.setSumOutExpenses(sumOutExpenses);
        result.setListExpenses(expensesByAccountBookIdResultList);
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    public ModelResult<List<List<EveryDayExpensesDetailResult>>> showEveryDayExpensesDetail(EveryDayExpensesDetailVo vo){
        Page page = new Page(1, 100, true);
        List<ExpensesEntity> expensesEntityList = expensesManager.listExpensesFromDayToDay(vo.getAccountBookId(), null,null, vo.getRecentDay(), page);
        List<List<EveryDayExpensesDetailResult>> resultLists = Lists.newArrayList();
        Date endDate = DateUtil.getBeginDate(new Date(), 0);
        for(int i = 0; i < vo.getRecentDay(); i++){
            Date current = DateUtil.getTimesMorning(DateUtil.getBeginDate(new Date(), i));
            List<EveryDayExpensesDetailResult> resultList = Lists.newArrayList();
            if(CollectionUtils.isNotEmpty(expensesEntityList)) {
                for (ExpensesEntity expensesEntity : expensesEntityList) {
                    if (expensesEntity.getExpenseDate().getTime() >= current.getTime() && expensesEntity.getExpenseDate().getTime() < endDate.getTime()) {
                        EveryDayExpensesDetailResult detailResult = BeanUtil.copy(expensesEntity, EveryDayExpensesDetailResult.class);
                        detailResult.setTime(expensesEntity.getExpenseDate().getTime());
                        resultList.add(detailResult);
//                        expensesEntityList.remove(expensesEntity);
                        // 不能remove
                    }
                }
            }
            endDate = current;
            resultLists.add(resultList);
        }
        return new ModelResult<>(SHErrorCode.SUCCESS, resultLists);
    }

    public ModelResult<ExpensesGroupClassificationByTypeStatisticResult> expensesGroupClassificationByTypeStatistic(ExpensesGroupClassificationByTypeStatisticVo vo){

        Date beginDate = null;
        Date endDate = null;
        if(null == vo.getMonth()){
            beginDate = DateUtil.getTimesThisYear(vo.getYear());
            endDate = DateUtil.getTimesThisYear(vo.getYear() + 1);
        }else{
            beginDate = DateUtil.getTimesThisMonth(vo.getYear(), vo.getMonth());
            // 需要知道 month == 12 的时候的特殊情况
            endDate = DateUtil.getTimesThisMonth(vo.getYear(), vo.getMonth() + 1);
        }
        SumInAndOutExpensesDto sumDto = expensesManager.sumInAndOutExpenses(vo.getAccountBookId(), beginDate, endDate, null);

        ExpensesGroupClassificationByTypeStatisticResult result = BeanUtil.copy(sumDto, ExpensesGroupClassificationByTypeStatisticResult.class);

        result.setInExpensesList(expensesManager.queryExpensesGroupClassificationByType(vo.getAccountBookId(), 0, beginDate, endDate));
        result.setOutExpensesList(expensesManager.queryExpensesGroupClassificationByType(vo.getAccountBookId(), 1, beginDate, endDate));
        result.setSurplus(sumDto.getTotalInExpenses() - sumDto.getTotalOutExpenses());

        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }


}
