package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class EveryDayExpensesDetailResult implements Serializable {
    Long time;
    Float expenses;
    Integer type;
    Integer classification;
    String description;
    String id;
}
