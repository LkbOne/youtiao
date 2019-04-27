package com.example.phoebe.youtiao.dao.api;

import com.example.phoebe.youtiao.dao.entity.TotalBudgetEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalBudgetDao {

    int addTotalBudget(TotalBudgetEntity totalBudgetEntity);

    int updateTotalBudget(TotalBudgetEntity totalBudgetEntity);

    int deleteTotalBudgetById(@Param("id") String id);

    TotalBudgetEntity queryTotalBudgetById(@Param("id") String id);

    TotalBudgetEntity queryTotalBudgetByAccountBookId(@Param("accountBookId") String accountBookId);
}
