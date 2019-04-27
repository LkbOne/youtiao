package com.example.phoebe.youtiao.controller.arg.Account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class RegisterArg implements Serializable {
//    @ApiModelProperty(name = "code", notes = "小程序返回的code")
    String code;

//    @ApiModelProperty(name = "wxName", notes = "微信名", allowEmptyValue = true)
    String wxName;

//    @ApiModelProperty(name = "avatarUrl", notes = "头像url", allowEmptyValue = true)
    String avatarUrl;

//    @ApiModelProperty(name = "avatarTPath", notes = "头像的暂时key", allowEmptyValue = true)
    String avatarTPath;

//    @ApiModelProperty(name = "account", notes = "账号")
    String account;

//    @ApiModelProperty(name = "password", notes = "密码")
    String password;

//    @ApiModelProperty(name = "phone", notes = "电话")
    String phone;

//    @ApiModelProperty(name = "name", notes = "名称", allowEmptyValue = true)
    String name;
//    @ApiModelProperty(name = "realName", notes = "真实名称", allowEmptyValue = true)
    String realName;

//    @ApiModelProperty(name = "signature", notes = "签名", allowEmptyValue = true)
    String signature;

//    @ApiModelProperty(hidden = true)
    public boolean isWrongParams() {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(wxName) || StringUtils.isEmpty(avatarUrl)) {
            return true;
        }
        return StringUtils.isEmpty(account) || StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(phone);
    }
}
