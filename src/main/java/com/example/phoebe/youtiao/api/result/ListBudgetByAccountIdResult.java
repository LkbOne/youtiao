package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListBudgetByAccountIdResult implements Serializable {
    String id;
    Float budget;
    Long beginTime;
    Long endTime;
    Long createTime;
    Long lastModifyTime;
    Float warnMoney;
    Integer classification;
}
