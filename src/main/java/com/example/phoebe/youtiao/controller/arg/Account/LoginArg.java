package com.example.phoebe.youtiao.controller.arg.Account;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class LoginArg implements Serializable {
    String account;
    String password;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
            return true;
        }
        return false;
    }
}
