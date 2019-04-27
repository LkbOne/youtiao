package com.example.phoebe.youtiao.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JudgeBudgetEnum {
    ALL_RIGHT(0),
    NOT_IN_TOTAL_BUDGET_TIME_INTERVAL(1), //不在总预算的时间内
    MORE_THAN_TOTAL_BUDGET(2),  //超过了总预算
    HAS_MIXED_THIS_CLASSIFICATION_BUDGET_TIME_INTERVAL(3); // 与该种类的预算有时间重叠
    Integer code;
}
