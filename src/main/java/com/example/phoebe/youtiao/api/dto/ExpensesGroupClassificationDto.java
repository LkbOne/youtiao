package com.example.phoebe.youtiao.api.dto;

import com.example.phoebe.youtiao.dao.entity.ExpensesEntity;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExpensesGroupClassificationDto implements Serializable {
    Integer classification;
    Float total;
}
