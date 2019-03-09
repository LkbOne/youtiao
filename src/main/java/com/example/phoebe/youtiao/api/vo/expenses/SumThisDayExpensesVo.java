package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.api.vo.BasePageVo;
import lombok.Data;
import java.util.Date;

@Data
public class SumThisDayExpensesVo extends BasePageVo {
    String accountBookId;

    Date searchDay;
}
