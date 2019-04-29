package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryExpensesByIdVo extends BaseArg {
    String id;
}
