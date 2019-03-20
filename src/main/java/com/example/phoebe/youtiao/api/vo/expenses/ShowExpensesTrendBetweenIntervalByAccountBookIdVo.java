package com.example.phoebe.youtiao.api.vo.expenses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShowExpensesTrendBetweenIntervalByAccountBookIdVo implements Serializable {

    String accountBookId;
    /**
     *  year 1 month 2 day 3
     */
    Integer interval;

    Date date;

}
