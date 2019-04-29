package com.example.phoebe.youtiao.controller.arg.Account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;


@Data
@EqualsAndHashCode(callSuper = false)
public class YouTiaoBindArg extends BaseArg {
//    @ApiModelProperty(name = "code", notes = "小程序返回的code")
    String code;
//
//    @ApiModelProperty(name = "wxName", notes = "微信名")
    String wxName;

//    @ApiModelProperty(name = "avatarUrl", notes = "头像url")
    String avatarUrl;
    public boolean isWrongParams() {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(wxName) || StringUtils.isEmpty(avatarUrl)) {
            return true;
        }
        return false;
    }
}
