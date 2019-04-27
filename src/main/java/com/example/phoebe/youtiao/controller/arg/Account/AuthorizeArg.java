package com.example.phoebe.youtiao.controller.arg.Account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class AuthorizeArg extends BaseArg {
    @ApiModelProperty(name = "code", notes = "小程序返回的code")
    String code;

    @ApiModelProperty(name = "wxName", notes = "微信名", allowEmptyValue = true)
    String wxName;

    @ApiModelProperty(name = "avatarUrl", notes = "头像url", allowEmptyValue = true)
    String avatarUrl;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams() {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(wxName) || StringUtils.isEmpty(avatarUrl)) {
            return true;
        }
        return false;
    }

}
