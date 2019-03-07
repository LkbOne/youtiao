package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.api.vo.BasePageVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListExpensesVo extends BasePageVo {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;

//    @ApiModelProperty(name = "budgetId", notes = "budgetId", allowEmptyValue = true)
//    String budgetId;
}