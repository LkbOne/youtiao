package com.example.phoebe.youtiao.controller.arg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class GetAccountBookByIdArg implements Serializable {
    @ApiModelProperty(value = "token", notes = "token")
    String token;

    public boolean isWrongParams(){
        if(StringUtils.isEmpty(token)){
            return true;
        }
        return false;
    }
}
