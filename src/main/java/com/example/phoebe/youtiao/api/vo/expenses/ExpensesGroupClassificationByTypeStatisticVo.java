package com.example.phoebe.youtiao.api.vo.expenses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExpensesGroupClassificationByTypeStatisticVo implements Serializable {

    String accountBookId;
    Integer year;
    Integer month;
}
