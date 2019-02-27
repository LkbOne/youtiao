package com.example.phoebe.youtiao.controller.arg.Account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class YouTiaoBindArg implements Serializable {
    @ApiModelProperty(name = "code", notes = "小程序返回的code")
    String code;

    @ApiModelProperty(name = "wxName", notes = "微信名")
    String wxName;

    @ApiModelProperty(name = "avatarUrl", notes = "头像url")
    String avatarUrl;
    public boolean isWrongParams() {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(wxName) || StringUtils.isEmpty(avatarUrl)) {
            return true;
        }
        return false;
    }
}
