package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QueryBudgetByIdResult implements Serializable {
    String id;
    Float budget;
    Long beginTime;
    Long endTime;
    Long createTime;
    Long lastModifyTime;
    Integer classification;
    Float warmMoney;
}
