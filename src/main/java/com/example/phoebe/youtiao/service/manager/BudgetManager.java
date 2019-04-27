package com.example.phoebe.youtiao.service.manager;

import com.example.phoebe.youtiao.api.dto.ListIntervalOfBudgetDto;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.enums.JudgeBudgetEnum;
import com.example.phoebe.youtiao.dao.api.BudgetDao;
import com.example.phoebe.youtiao.dao.entity.BudgetEntity;
import com.example.phoebe.youtiao.dao.entity.TotalBudgetEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BudgetManager {

    @Autowired
    BudgetDao budgetDao;
    /**
     * {@link JudgeBudgetEnum}
    */
    public SHErrorCode judgeBudget(TotalBudgetEntity totalBudget, BudgetEntity budget){
        // 都没问题 0
        // 不在总预算的时间内 1
        // 超过了总预算 2
        // 与该种类的预算有时间重叠 3
        if(null == totalBudget || null == budget){
            return SHErrorCode.SYSTEM_ERROR;
        }
        if (budget.getBeginTime().getTime() < totalBudget.getBeginTime().getTime() || budget.getEndTime().getTime() > totalBudget.getEndTime().getTime()) {
             return SHErrorCode.NO_IN_TOTAL_BUDGET_TIME;
        }

        Float totalHasBudget = budgetDao.sumBudgetByTotalBudgetId(totalBudget.getId());
        totalHasBudget = totalHasBudget == null ? new Float(0) : totalHasBudget;
        if (totalHasBudget + budget.getBudget() > totalBudget.getTotalBudget()) {
            log.warn("BudgetManager.judgeBudget vo:{}, totalHasBudget:{}, totalBudget:{}", budget, totalHasBudget, totalBudget);
            return SHErrorCode.MORE_THAN_TOTAL_BUDGET;
        }

        List<ListIntervalOfBudgetDto> timeIntervalList = budgetDao.listTimeByTotalBudgetIdAndClassification(totalBudget.getId(), budget.getClassification(), budget.getId());
        if(CollectionUtils.isEmpty(timeIntervalList)){
            return SHErrorCode.SUCCESS;
        }

        boolean flag = false;
        Long leftMargin = totalBudget.getBeginTime().getTime();
        Long rightMargin = null;
        ListIntervalOfBudgetDto timeInterval = null;
        for(int i = 0; i <= timeIntervalList.size(); i++){
            if(i != timeIntervalList.size()) {
                timeInterval = timeIntervalList.get(i);
                rightMargin = timeInterval.getBeginTime().getTime();
            }else {
                rightMargin = totalBudget.getEndTime().getTime();
            }
            if(budget.getBeginTime().getTime()>= leftMargin && budget.getEndTime().getTime() <= rightMargin){
                flag = true;
                break;
            }
            if(i != timeIntervalList.size()) {
                leftMargin = timeInterval.getEndTime().getTime();
            }
        }
        if(!flag){
            return SHErrorCode.HAS_MIXED_THIS_CLASSIFICATION_BUDGET_TIME_INTERVAL;
        }

        return SHErrorCode.SUCCESS;
    }

    public SHErrorCode judgeTotalBudget(TotalBudgetEntity totalBudget){
        if(null == totalBudget){
            return SHErrorCode.SYSTEM_ERROR;
        }
        Float totalHasBudget = budgetDao.sumBudgetByTotalBudgetId(totalBudget.getId());
        totalHasBudget = totalHasBudget == null ? new Float(0) : totalHasBudget;
        if (totalHasBudget > totalBudget.getTotalBudget()) {
            log.warn("BudgetManager.judgeTotalBudget totalHasBudget:{}, totalBudget:{}", totalHasBudget, totalBudget);
            return SHErrorCode.LESS_THAN_TOTAL_BUDGET;
        }

        Date beginTime = budgetDao.queryEarliestBeginTimeByTotalBudgetId(totalBudget.getId(), 1);
        if(beginTime != null && beginTime.getTime() < totalBudget.getBeginTime().getTime()){
            log.warn("BudgetManager.judgeTotalBudget beginTime:{}, totalBudget:{}", beginTime.getTime(), totalBudget);
            return SHErrorCode.LESS_THAN_TOTAL_BUDGET_TIME;
        }

        Date endTime = budgetDao.queryLatestEndTimeByTotalBudgetId(totalBudget.getId(), 1);
        if(endTime != null && endTime.getTime() > totalBudget.getEndTime().getTime()){
            log.warn("BudgetManager.judgeTotalBudget endTime:{}, totalBudget:{}", endTime, totalBudget);
            return SHErrorCode.MORE_THAN_TOTAL_BUDGET_TIME;
        }
        return SHErrorCode.SUCCESS;
    }
}
