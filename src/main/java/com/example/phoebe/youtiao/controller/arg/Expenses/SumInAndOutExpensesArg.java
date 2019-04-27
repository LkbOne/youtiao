package com.example.phoebe.youtiao.controller.arg.Expenses;


import com.example.phoebe.youtiao.commmon.enums.SumExpensesDateEnum;
import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;

@Data
public class SumInAndOutExpensesArg extends BaseArg {

    //  @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;
    /**
     * {@link com.example.phoebe.youtiao.commmon.enums.SumExpensesDateEnum}
     **/
/*    @ApiModelProperty(name = "type", notes = "    YEAR(1),\n" +
            "    MONTH(2),\n" +
            "    WEEK(3),\n" +
            "    DAY(4);")*/
    Integer type;
    //    @ApiModelProperty(name = "date", notes = "时间")
    Date date;

    public boolean isWrongParams(){
        if(null == date || null == accountBookId){
            return true;
        }
        return Arrays.stream(SumExpensesDateEnum.values()).noneMatch(data -> data.getType().equals(type));
    }
}
