package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BasePageArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ListBudgetArg extends BasePageArg {

    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        return StringUtils.isBlank(accountBookId);
    }
}
