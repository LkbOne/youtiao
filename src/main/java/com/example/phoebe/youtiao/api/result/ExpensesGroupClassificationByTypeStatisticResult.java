package com.example.phoebe.youtiao.api.result;

import com.example.phoebe.youtiao.api.dto.ExpensesGroupClassificationDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExpensesGroupClassificationByTypeStatisticResult implements Serializable {

    Float totalInExpenses;

    Float totalOutExpenses;

    Float surplus;

    List<ExpensesGroupClassificationDto> inExpensesList;

    List<ExpensesGroupClassificationDto> outExpensesList;


}
