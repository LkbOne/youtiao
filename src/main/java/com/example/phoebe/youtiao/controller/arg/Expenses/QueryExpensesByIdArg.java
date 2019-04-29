package com.example.phoebe.youtiao.controller.arg.Expenses;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryExpensesByIdArg extends BaseArg {
    private String id;

    public boolean isWrongParams(){
        if (StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
