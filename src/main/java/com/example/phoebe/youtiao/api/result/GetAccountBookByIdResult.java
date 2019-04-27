package com.example.phoebe.youtiao.api.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class GetAccountBookByIdResult implements Serializable {
    String id;
    String aid;
    String name;
    Integer color;
    Integer status;
    String totalBudgetId;
    Float leftBudgetMoney;
    Float warnMoney;
    Integer openHistory;
}
