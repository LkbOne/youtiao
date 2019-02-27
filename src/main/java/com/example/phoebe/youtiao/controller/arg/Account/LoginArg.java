package com.example.phoebe.youtiao.controller.arg.Account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class LoginArg implements Serializable {
    @ApiModelProperty(name = "code", notes = "小程序返回的code")
    String code;

    public boolean isWrongParams() {
        if (StringUtils.isEmpty(code)) {
            return true;
        }
        return false;
    }
}
