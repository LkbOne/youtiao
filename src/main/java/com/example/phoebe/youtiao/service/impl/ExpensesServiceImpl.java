package com.example.phoebe.youtiao.service.impl;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.dto.SumInAndOutExpensesDto;
import com.example.phoebe.youtiao.api.result.*;
import com.example.phoebe.youtiao.api.vo.SumInAndOutExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.enums.DateIntervalEnum;
import com.example.phoebe.youtiao.commmon.enums.SumExpensesDateEnum;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.DateUtil;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.commmon.util.result.BeginAndEndDateResult;
import com.example.phoebe.youtiao.dao.api.AccountBookDao;
import com.example.phoebe.youtiao.dao.api.ExpensesDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import com.example.phoebe.youtiao.service.manager.ExpensesManager;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Calendar;
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

    public ModelResult<SumInAndOutExpensesResult> sumInAndOutExpenses(SumInAndOutExpensesVo vo){
        BeginAndEndDateResult date = DateUtil.getBeginAndEndDate(vo.getDate(), vo.getType());
        SumInAndOutExpensesDto sumDto = expensesManager.sumInAndOutExpenses(vo.getAccountBookId(), date.getBeginDate(), date.getEndDate(), null);
        SumInAndOutExpensesResult result = new SumInAndOutExpensesResult();
        result.setSumInExpenses(sumDto.getTotalInExpenses());
        result.setSumOutExpenses(sumDto.getTotalOutExpenses());
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }


    public ModelResult<SumThisDayExpensesResult> sumThisDayExpenses(SumThisDayExpensesVo vo){
        BeginAndEndDateResult date = DateUtil.getBeginAndEndDate(vo.getSearchDay(), SumExpensesDateEnum.DAY.getType());

        Page page = new Page(vo.getPageNum(), vo.getPageSize(), true);
        SumInAndOutExpensesDto sumDto = expensesManager.sumInAndOutExpenses(vo.getAccountBookId(), date.getBeginDate(), date.getEndDate(), null);

        List<ExpensesEntity> expensesEntities = expensesManager.listExpensesFromDayToDay(vo.getAccountBookId(), date.getBeginDate(), date.getEndDate(), null, page);

        List<ListExpensesByAccountBookIdResult> expensesByAccountBookIdResultList = Lists.newArrayList();
        for (ExpensesEntity expensesEntity : expensesEntities) {
            ListExpensesByAccountBookIdResult expensesResult = BeanUtil.copy(expensesEntity, ListExpensesByAccountBookIdResult.class);
            expensesResult.setExpenseDate(expensesEntity.getExpenseDate().getTime());
            expensesResult.setCreateTime(expensesEntity.getCreateTime().getTime());
            expensesResult.setLastModifyTime(expensesEntity.getLastModifyTime().getTime());
            expensesByAccountBookIdResultList.add(expensesResult);
        }

        SumThisDayExpensesResult result = new SumThisDayExpensesResult();
        result.setSumInExpenses(sumDto.getTotalInExpenses());
        result.setSumOutExpenses(sumDto.getTotalOutExpenses());
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

    public ModelResult<ShowExpensesTreadResult> showExpensesTrendBetweenIntervalByAccountBookId(ShowExpensesTrendBetweenIntervalByAccountBookIdVo vo){

        BeginAndEndDateResult dateResult = null;
        ShowExpensesTreadResult result = new ShowExpensesTreadResult();
        List<ShowExpensesTreadResult.TreadResult> inExpensesList = Lists.newArrayList();
        List<ShowExpensesTreadResult.TreadResult> outExpensesList = Lists.newArrayList();
        List<ShowExpensesTreadResult.TreadResult> surplusList = Lists.newArrayList();
        boolean hasInExpenses = false;
        boolean hasOutExpenses = false;
        if(vo.getInterval().equals(DateIntervalEnum.YEAR.getInterval())){
           dateResult = DateUtil.getBeginAndEndDate(vo.getDate(), SumExpensesDateEnum.YEAR.getType());
           Date beginDate = dateResult.getBeginDate();
           Date tmpEndDate = null;
           Calendar tmpEndCal = DateUtil.getTimesThisYearByInteval(beginDate);
            for(int i = 1; i <= 12; i++){
                if(i != 12) {
                    tmpEndCal.set(Calendar.MONTH, i);
                    tmpEndDate = tmpEndCal.getTime();
                }else{
                    tmpEndDate = dateResult.getEndDate();
                }
                SumInAndOutExpensesDto dto = expensesManager.sumInAndOutExpenses(vo.getAccountBookId(), beginDate, tmpEndDate, null);
                Float surplus = dto.getTotalInExpenses() - dto.getTotalOutExpenses();
                hasInExpenses = hasInExpenses || (!hasInExpenses && dto.getTotalInExpenses() != 0);
                hasOutExpenses = hasOutExpenses || (!hasOutExpenses && dto.getTotalOutExpenses() != 0);
                inExpensesList.add(new ShowExpensesTreadResult.TreadResult(dto.getTotalInExpenses(), beginDate));
                outExpensesList.add(new ShowExpensesTreadResult.TreadResult(dto.getTotalOutExpenses(), beginDate));
                surplusList.add(new ShowExpensesTreadResult.TreadResult(surplus, beginDate));
                beginDate = tmpEndDate;
            }
        }
        if(vo.getInterval().equals(DateIntervalEnum.MONTH.getInterval())){
            dateResult = DateUtil.getBeginAndEndDate(vo.getDate(), SumExpensesDateEnum.MONTH.getType());
            Date beginDate = dateResult.getBeginDate();
            Date endDate = dateResult.getEndDate();
            while(beginDate.getTime() < endDate.getTime()){
                Date tmpEndDate = DateUtil.getEndDate(beginDate, 1);

                SumInAndOutExpensesDto dto = expensesManager.sumInAndOutExpenses(vo.getAccountBookId(), beginDate, tmpEndDate, null);
                Float surplus = dto.getTotalInExpenses() - dto.getTotalOutExpenses();
                hasInExpenses = hasInExpenses || (!hasInExpenses && dto.getTotalInExpenses() != 0);
                hasOutExpenses = hasOutExpenses || (!hasOutExpenses && dto.getTotalOutExpenses() != 0);
                inExpensesList.add(new ShowExpensesTreadResult.TreadResult(dto.getTotalInExpenses(), beginDate));
                outExpensesList.add(new ShowExpensesTreadResult.TreadResult(dto.getTotalOutExpenses(), beginDate));
                surplusList.add(new ShowExpensesTreadResult.TreadResult(surplus, beginDate));
                beginDate = tmpEndDate;
            }

        }

        result.setInExpenses(hasInExpenses?inExpensesList:Lists.newArrayList());
        result.setOutExpenses(hasOutExpenses?outExpensesList:Lists.newArrayList());
        result.setSurplus(((!hasInExpenses)&&(!hasOutExpenses))?Lists.newArrayList():surplusList);
        return new ModelResult<>(SHErrorCode.SUCCESS, result);
    }

    @Override
    public ExportExpensesInfoResult exportExpensesInfoToExcel(ExportExcelVo vo) {
        BeginAndEndDateResult dateResult = null;
        ExportExpensesInfoResult result = new ExportExpensesInfoResult();
        List<ExportExpensesInfoResult.ExpensesInfo> expensesInfoList = Lists.newArrayList();
        float totalInExpenses = 0;
        float totalOutExpenses = 0;
        if(vo.getInterval().equals(DateIntervalEnum.YEAR.getInterval())){
            dateResult = DateUtil.getBeginAndEndDate(vo.getDate(), SumExpensesDateEnum.YEAR.getType());
            Date beginDate = dateResult.getBeginDate();
            Date tmpEndDate = null;
            Calendar tmpEndcal = DateUtil.getTimesThisYearByInteval(beginDate);
            for(int i = 1; i <= 12; i++){
                if(i != 12) {
                    tmpEndcal.set(Calendar.MONTH, i);
                    tmpEndDate = tmpEndcal.getTime();
                }else{
                    tmpEndDate = dateResult.getEndDate();
                }
                SumInAndOutExpensesDto dto = expensesManager.sumInAndOutExpenses(vo.getAccountBookId(), beginDate, tmpEndDate, null);
                totalInExpenses += dto.getTotalInExpenses();
                totalOutExpenses += dto.getTotalOutExpenses();
                ExportExpensesInfoResult.ExpensesInfo expensesInfo = new ExportExpensesInfoResult.ExpensesInfo(dto.getTotalInExpenses(), dto.getTotalOutExpenses(), beginDate);
                expensesInfoList.add(expensesInfo);
                beginDate = tmpEndDate;
            }
        }
        if(vo.getInterval().equals(DateIntervalEnum.MONTH.getInterval())){
            dateResult = DateUtil.getBeginAndEndDate(vo.getDate(), SumExpensesDateEnum.MONTH.getType());
            Date beginDate = dateResult.getBeginDate();
            Date endDate = dateResult.getEndDate();
            while(beginDate.getTime() < endDate.getTime()){
                Date tmpEndDate = DateUtil.getEndDate(beginDate, 1);
                SumInAndOutExpensesDto dto = expensesManager.sumInAndOutExpenses(vo.getAccountBookId(), beginDate, tmpEndDate, null);
                totalInExpenses += dto.getTotalInExpenses();
                totalOutExpenses += dto.getTotalOutExpenses();
                ExportExpensesInfoResult.ExpensesInfo expensesInfo = new ExportExpensesInfoResult.ExpensesInfo(dto.getTotalInExpenses(), dto.getTotalOutExpenses(), beginDate);
                expensesInfoList.add(expensesInfo);
                beginDate = tmpEndDate;
            }
        }


        result.setBeginTime(dateResult.getBeginDate());
        result.setEndTime(dateResult.getEndDate());
        result.setAccountBookName(accountBookDao.queryNameById(vo.getAccountBookId()));
        result.setTotalOutExpenses(totalOutExpenses);
        result.setTotalInExpenses(totalInExpenses);

        BigDecimal totalInDecimal = new BigDecimal(
                Float.toString(totalInExpenses));

        BigDecimal totalOutDecimal = new BigDecimal(
                Float.toString(totalOutExpenses));

        result.setTotalSurplus(totalInDecimal.subtract(totalOutDecimal).floatValue());
        result.setExpensesInfoList(expensesInfoList);
        return  result;
    }

}
