package com.example.phoebe.youtiao.dao.api;
import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpensesDao {
    int addExpenses(ExpensesEntity expensesEntity);

    int updateExpenses(ExpensesEntity expensesEntity);

    int deleteExpensesById(@Param("id") String id);

    ExpensesEntity queryExpensesById(@Param("id") String id);

    List<ExpensesEntity> listExpensesByAccountBookId(@Param("accountBookId") String accountBookId, @Param("beginDate")DateTime beginDate,@Param("endDate") DateTime endDate, @Param("page") Page page);

    Float sumExpenses(@Param("accountBookId") String accountBookId, @Param("type") Integer type, @Param("beginDate")DateTime beginDate, @Param("endDate") DateTime endDate);
}
