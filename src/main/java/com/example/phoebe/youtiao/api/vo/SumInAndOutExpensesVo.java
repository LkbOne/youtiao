package com.example.phoebe.youtiao.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SumInAndOutExpensesVo implements Serializable {

    String accountBookId;
    /**
    * {@link com.example.phoebe.youtiao.commmon.enums.SumExpensesDateEnum}
    **/
    Integer type;
    Date date;
}
