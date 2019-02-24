package com.example.phoebe.youtiao.api.vo.expenses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryExpensesByIdVo implements Serializable {
    @ApiModelProperty(value = "id", notes = "budgetId")
    String id;
}
