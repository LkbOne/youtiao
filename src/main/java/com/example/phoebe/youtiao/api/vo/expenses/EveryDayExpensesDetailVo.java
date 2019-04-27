package com.example.phoebe.youtiao.api.vo.expenses;

import lombok.Data;

import java.io.Serializable;

@Data
public class EveryDayExpensesDetailVo implements Serializable {
    String accountBookId;

    Integer recentDay;
}
