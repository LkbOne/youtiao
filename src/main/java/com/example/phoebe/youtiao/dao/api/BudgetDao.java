package com.example.phoebe.youtiao.dao.api;

import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.example.phoebe.youtiao.dao.entity.BudgetEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetDao {
    int addBudget(BudgetEntity budgetEntity);

    int updateBudget(BudgetEntity budgetEntity);

    int deleteBudgetById(@Param("id") String budgetId);

    BudgetEntity queryBudgetById(@Param("id") String budgetId);

    List<BudgetEntity> queryAccountBookByAccountBookId(@Param("accountBookId") String accountBookId);
}
