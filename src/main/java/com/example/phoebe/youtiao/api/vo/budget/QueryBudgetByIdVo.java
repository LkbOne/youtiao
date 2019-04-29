package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryBudgetByIdVo extends BaseArg {
    String id;
}
