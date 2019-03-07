package com.example.phoebe.youtiao.dao.api;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesDao {
    int addExpenses(ExpensesEntity expensesEntity);

    int updateExpenses(ExpensesEntity expensesEntity);

    int deleteExpensesById(@Param("id") String id);

    ExpensesEntity queryExpensesById(@Param("id") String id);

    List<ExpensesEntity> listExpensesByAccountBookId(@Param("accountBookId") String accountBookId,  @Param("page") Page page);
}
