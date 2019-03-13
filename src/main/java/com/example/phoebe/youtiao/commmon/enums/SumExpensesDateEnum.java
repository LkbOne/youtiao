package com.example.phoebe.youtiao.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum SumExpensesDateEnum {
    YEAR(1),
    MONTH(2),
    WEEK(3),
    DAY(4);
    Integer type;
}
