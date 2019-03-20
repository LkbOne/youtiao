package com.example.phoebe.youtiao.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DateIntervalEnum {

    YEAR(1),
    MONTH(2),
    DAY(3);
    Integer interval;
}
