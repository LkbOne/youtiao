package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.api.vo.BasePageVo;
import lombok.Data;

@Data
public class ListExpensesVo extends BasePageVo {
    String accountBookId;

    Integer recentDay;

}
