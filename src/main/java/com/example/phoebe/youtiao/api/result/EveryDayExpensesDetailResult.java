package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class EveryDayExpensesDetailResult implements Serializable {
    Long time;
    Long expense;
    Integer type;
    Integer classification;
}
