package com.example.phoebe.youtiao.budget;

import com.example.phoebe.youtiao.api.BudgetService;
import com.example.phoebe.youtiao.api.result.ListBudgetByAccountBookIdResult;
import com.example.phoebe.youtiao.api.vo.budget.AddBudgetVo;
import com.example.phoebe.youtiao.api.vo.budget.ListBudgetVo;
import com.example.phoebe.youtiao.api.vo.budget.UpdateBudgetVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Ignore
public class BudgetTest {

    @Autowired
    BudgetService budgetService;

    @Test
    public void addBudget(){
        AddBudgetVo vo = new AddBudgetVo();
        vo.setBudget(new Float(123.03));
        vo.setWarnMoney(new Float(100));
        vo.setBeginTime(new Date());
        vo.setEndTime(new Date(new Date().getTime() + 3600 * 10 ));
        vo.setAccountBookId("4e980d3a8b174e79960fae19cf0c62f3");
        budgetService.addBudget(vo);
        System.out.println();
    }

    @Test
    public void updateBudget(){
        UpdateBudgetVo vo = new UpdateBudgetVo();

        vo.setBudget(new Float(200));
        vo.setId("8abdcae1194e49178169de513fb824a9");
        budgetService.updateBudget(vo);
    }

    @Test
    public void listBudget(){
        ListBudgetVo vo = new ListBudgetVo();
        vo.setAccountBookId("4e980d3a8b174e79960fae19cf0c62f3");
        vo.setPageNum(1);
        vo.setPageSize(10);

        ModelResult<PageResult<ListBudgetByAccountBookIdResult>> result = budgetService.listBudgetByAccountBookId(vo);
        System.out.println();
    }
}
