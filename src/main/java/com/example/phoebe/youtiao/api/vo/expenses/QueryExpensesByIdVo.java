package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;

@Data
public class QueryExpensesByIdVo extends BaseArg {
    String id;
}
