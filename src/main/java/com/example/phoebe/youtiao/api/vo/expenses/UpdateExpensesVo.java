package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import java.sql.Date;

@Data
public class UpdateExpensesVo extends BaseArg {
    String id;

    Date expensesDate;

    String description;

    Float expenses;

    Integer type;

    Integer classification;
}
