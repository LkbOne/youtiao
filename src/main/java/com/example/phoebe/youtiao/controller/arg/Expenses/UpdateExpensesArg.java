package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UpdateExpensesArg extends BaseArg {
    //  @ApiModelProperty(name = "id", notes = "id")
    String id;

    //  @ApiModelProperty(name = "expensesDate", notes = "消费时间")
    Date expensesDate;

    //  @ApiModelProperty(name = "description", notes = "备注", allowEmptyValue = true)
    String description;

    //  @ApiModelProperty(name = "expenses", notes = "金额")
    Float expenses;

    //  @ApiModelProperty(name = "type", notes = "0 为收入  1 为支出")
    Integer type;

    //  @ApiModelProperty(name = "classification", notes = "你标注的收入支出类型")
    Integer classification;

    //  @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if (StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
