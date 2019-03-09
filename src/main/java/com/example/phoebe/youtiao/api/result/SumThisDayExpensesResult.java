package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SumThisDayExpensesResult implements Serializable {
    Float sumInExpenses = new Float(0);
    Float sumOutExpenses = new Float(0);

    List<ListExpensesByAccountBookIdResult> listExpenses;
}
