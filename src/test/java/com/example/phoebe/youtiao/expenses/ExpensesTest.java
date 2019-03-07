package com.example.phoebe.youtiao.expenses;

import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.result.ListExpensesByAccountBookIdResult;
import com.example.phoebe.youtiao.api.result.QueryExpensesByIdResult;
import com.example.phoebe.youtiao.api.vo.expenses.AddExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.ListExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.QueryExpensesByIdVo;
import com.example.phoebe.youtiao.api.vo.expenses.UpdateExpensesVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
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
@Ignore
public class ExpensesTest implements Serializable {
    @Autowired
    ExpensesService expensesService;
    @Test
    public void addExpenses(){
        AddExpensesVo vo = new AddExpensesVo();
        vo.setAccountBookId("4e980d3a8b174e79960fae19cf0c62f3");
        vo.setExpenses(new Float(123));
        vo.setType(6);
        vo.setClassification(6);
        vo.setDescription("123456789");
        vo.setName("one");
        expensesService.addExpenses(vo);
    }
    @Test
    public void updateExpenses(){
        UpdateExpensesVo vo = new UpdateExpensesVo();
        vo.setId("867c4220c22d4b769eae401b6d6c3646");
        vo.setExpenses(new Float(300));
        vo.setType(7);
        vo.setClassification(7);
        vo.setDescription("987654321");
        vo.setName("two");
        expensesService.updateExpenses(vo);
    }
    @Test
    public void queryExpensesById(){
        QueryExpensesByIdVo vo = new QueryExpensesByIdVo();
        vo.setId("867c4220c22d4b769eae401b6d6c3646");
        ModelResult<QueryExpensesByIdResult> a = expensesService.queryExpensesById(vo);
        System.out.println();
    }

    @Test
    public void listExpenses(){
        ListExpensesVo vo = new ListExpensesVo();
        vo.setPageNum(1);
        vo.setPageSize(10);
        vo.setAccountId("4e980d3a8b174e79960fae19cf0c62f3");
        ModelResult<PageResult<ListExpensesByAccountBookIdResult>> a = expensesService.listExpenses(vo);
        System.out.println();
    }
}
