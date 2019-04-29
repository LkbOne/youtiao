package com.example.phoebe.youtiao.controller.arg.Account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;


@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorizeArg extends BaseArg {
    String code;

    String wxName;

    String avatarUrl;

    public boolean isWrongParams() {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(wxName) || StringUtils.isEmpty(avatarUrl)) {
            return true;
        }
        return false;
    }

}
