package com.example.phoebe.youtiao.api.vo.account;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
//    @ApiModelProperty(name = "code", notes = "小程序返回的code")
    String code;

//    @ApiModelProperty(name = "wxName", notes = "微信名")
    String wxName;

//    @ApiModelProperty(name = "avatarUrl", notes = "头像url")
    String avatarUrl;
}
