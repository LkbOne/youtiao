package com.example.phoebe.youtiao.api.result;
import lombok.Data;
import java.io.Serializable;

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
