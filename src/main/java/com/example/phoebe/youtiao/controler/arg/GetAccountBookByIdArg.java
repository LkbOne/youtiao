package com.example.phoebe.youtiao.controler.arg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class GetAccountBookByIdArg implements Serializable {
    @ApiModelProperty(value = "accountBookId", notes = "账本id")
    String accountBookId;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }
}
