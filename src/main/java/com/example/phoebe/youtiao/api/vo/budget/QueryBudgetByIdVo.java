package com.example.phoebe.youtiao.api.vo.budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryBudgetByIdVo implements Serializable {
    @ApiModelProperty(name = "budgetId", notes = "预算id")
    String id;
}
