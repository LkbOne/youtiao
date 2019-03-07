package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryBudgetByIdVo extends BaseArg {
    @ApiModelProperty(name = "budgetId", notes = "预算id")
    String id;
}
