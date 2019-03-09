package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.api.vo.BasePageVo;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class SumThisDayExpensesVo extends BasePageVo {
    String accountBookId;

    Date searchDay;
}
