package com.example.phoebe.youtiao.api.vo.expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryExpensesByIdVo extends BaseArg {
    @ApiModelProperty(value = "id", notes = "费用id")
    String id;
}
