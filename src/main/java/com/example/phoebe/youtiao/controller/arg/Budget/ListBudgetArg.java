package com.example.phoebe.youtiao.controller.arg.Budget;

import com.example.phoebe.youtiao.controller.arg.BasePageArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListBudgetArg extends BasePageArg {

    String accountBookId;

    public boolean isWrongParams(){
        return StringUtils.isBlank(accountBookId);
    }
}
