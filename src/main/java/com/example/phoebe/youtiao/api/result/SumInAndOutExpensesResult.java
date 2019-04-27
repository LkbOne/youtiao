package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class SumInAndOutExpensesResult implements Serializable {
    Float sumInExpenses = new Float(0);
    Float sumOutExpenses = new Float(0);
}
