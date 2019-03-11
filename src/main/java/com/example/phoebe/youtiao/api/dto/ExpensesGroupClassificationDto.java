package com.example.phoebe.youtiao.api.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExpensesGroupClassificationDto implements Serializable {
    Integer classification;
    Float total;
}
