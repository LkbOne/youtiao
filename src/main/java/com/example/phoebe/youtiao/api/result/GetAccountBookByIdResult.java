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
    Long createTime;
    Long lastModifyTime;

    String totalBudgetId;
    Float totalBudgetMoney;

    @ApiModelProperty(name = "修改历史", notes = "1为这一次打开， 0为正常状态")
    Integer openHistory;
}
