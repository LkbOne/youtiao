package com.example.phoebe.youtiao.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ListIntervalOfBudgetDto implements Serializable {
    String id;
    Date endTime;
    Date beginTime;
}
