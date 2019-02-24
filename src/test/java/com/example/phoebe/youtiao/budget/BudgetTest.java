package com.example.phoebe.youtiao.budget;

import com.example.phoebe.youtiao.api.BudgetService;
import com.example.phoebe.youtiao.api.vo.budget.AddbudgetVo;
import com.example.phoebe.youtiao.api.vo.budget.UpdateBudgetVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetTest {

    @Autowired
    BudgetService budgetService;

    @Test
    public void addBudget(){
        AddbudgetVo vo = new AddbudgetVo();
        vo.setBudget(new Float(123.03));
        vo.setType(1);
        vo.setAccountBookId("278a66b2adce4a64863db9d62177b0ec");
        budgetService.addBudget(vo);
    }

    @Test
    public void updateBudget(){
        UpdateBudgetVo vo = new UpdateBudgetVo();

        vo.setBudget(new Float(200));
        vo.setType(2);
        vo.setId("8abdcae1194e49178169de513fb824a9");
        budgetService.updateBudget(vo);
    }
}
