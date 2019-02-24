package com.example.phoebe.youtiao.api.vo.budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteBudgetVo implements Serializable {
    @ApiModelProperty(name = "id", notes = "预算id")
    String id;
}
