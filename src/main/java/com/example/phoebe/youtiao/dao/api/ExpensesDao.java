package com.example.phoebe.youtiao.dao.api;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesDao {
    int addExpenses(ExpensesEntity expensesEntity);

    int updateExpenses(ExpensesEntity expensesEntity);

    int deleteExpensesById(@Param("id") String id);

    ExpensesEntity queryExpensesById(@Param("id") String id);
}
