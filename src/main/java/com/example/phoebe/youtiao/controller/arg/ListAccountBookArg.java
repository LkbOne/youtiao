package com.example.phoebe.youtiao.controller.arg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class ListAccountBookArg implements Serializable {
    @ApiModelProperty(value = "accountId", notes = "账户id")
    String accountId;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(accountId)){
            return true;
        }
        return false;
    }
}
