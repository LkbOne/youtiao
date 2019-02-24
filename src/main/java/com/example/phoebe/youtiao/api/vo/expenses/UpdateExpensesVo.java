package com.example.phoebe.youtiao.api.vo.expenses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
public class UpdateExpensesVo implements Serializable {
    @ApiModelProperty(value="id", notes = "id")
    String id;

    @ApiModelProperty(value = "name", notes = "名字")
    String name;

    @ApiModelProperty(value = "expenses", notes = "金额")
    Float expenses;

    @ApiModelProperty(value = "inType", notes = "收入类型(工资、兼职）")
    Integer inType;

    @ApiModelProperty(value = "outType", notes = "该花费的状态（已经花费，计划花费，正在花费）")
    Integer outType;
}
