package com.example.phoebe.youtiao.controller.arg.Expenses;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShowExpensesTrendBetweenIntervalByAccountBookIdArg implements Serializable {

    // @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;
    /**
     *  year 1 month 2 day 3
     */

    // @ApiModelProperty(name = "interval", notes = "year 1 month 2 day 3")
    Integer interval;


    //  @ApiModelProperty(name = "date", notes = "interval为1的时候，计算年，为2的时候计算月")
    Date date;

    public boolean isWrongParams(){
        if(null == interval || null == date || Strings.isBlank(accountBookId)){
            return true;
        }
        return false;
    }
}
