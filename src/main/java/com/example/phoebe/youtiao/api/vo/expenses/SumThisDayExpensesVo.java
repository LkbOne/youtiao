package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.api.vo.BasePageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SumThisDayExpensesVo extends BasePageVo {
    String accountBookId;

    Date searchDay;
}
