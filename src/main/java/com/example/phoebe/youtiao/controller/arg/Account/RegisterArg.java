package com.example.phoebe.youtiao.controller.arg.Account;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class RegisterArg implements Serializable {
    String code;

    String wxName;

    String avatarUrl;

    String avatarTPath;

    String account;

    String password;

    String phone;

    String name;
    String realName;

    String signature;

    public boolean isWrongParams() {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(wxName) || StringUtils.isEmpty(avatarUrl)) {
            return true;
        }
        return StringUtils.isEmpty(account) || StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(phone);
    }
}
