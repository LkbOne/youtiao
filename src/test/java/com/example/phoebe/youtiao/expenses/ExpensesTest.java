package com.example.phoebe.youtiao.expenses;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.result.QueryExpensesByIdResult;
import com.example.phoebe.youtiao.api.vo.expenses.AddExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.QueryExpensesByIdVo;
import com.example.phoebe.youtiao.api.vo.expenses.UpdateExpensesVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import lombok.Data;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpensesTest implements Serializable {
    @Autowired
    ExpensesService expensesService;
    @Test
    public void addExpenses(){
        AddExpensesVo vo = new AddExpensesVo();
        vo.setAccountBookId("278a66b2adce4a64863db9d62177b0ec");
        vo.setBudgetId("8abdcae1194e49178169de513fb824a9");
        vo.setExpenses(new Float(123));
        vo.setInType(2);
        vo.setOutType(1);
        vo.setName("one");
        expensesService.addExpenses(vo);
    }
    @Test
    public void updateExpenses(){
        UpdateExpensesVo vo = new UpdateExpensesVo();
        vo.setId("a2fed2417d2a459fb90f3013e3f417c8");
        vo.setInType(6);
        vo.setOutType(6);
        vo.setExpenses(new Float(300));
        expensesService.updateExpenses(vo);
    }
    @Test
    public void queryExpensesById(){
        QueryExpensesByIdVo vo = new QueryExpensesByIdVo();
        vo.setId("a2fed2417d2a459fb90f3013e3f417c8");
        ModelResult<QueryExpensesByIdResult> a = expensesService.queryExpensesById(vo);
        System.out.println();
    }

}
