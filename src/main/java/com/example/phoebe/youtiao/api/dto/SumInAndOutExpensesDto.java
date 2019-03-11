package com.example.phoebe.youtiao.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SumInAndOutExpensesDto implements Serializable {
    Float totalInExpenses;
    Float totalOutExpenses;
}
