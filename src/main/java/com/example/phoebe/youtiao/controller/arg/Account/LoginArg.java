package com.example.phoebe.youtiao.controller.arg.Account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class LoginArg implements Serializable {
//    @ApiModelProperty(name = "account", notes = "账户")
    String account;
//    @ApiModelProperty(name = "password", notes = "密码")
    String password;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
            return true;
        }
        return false;
    }
}
