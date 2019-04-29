package com.example.phoebe.youtiao.api.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExpensesGroupClassificationDto implements Serializable {
    Integer classification;
    Float total;
}
