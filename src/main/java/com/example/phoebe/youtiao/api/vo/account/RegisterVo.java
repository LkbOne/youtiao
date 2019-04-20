package com.example.phoebe.youtiao.api.vo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVo implements Serializable {

    @ApiModelProperty(name = "code", notes = "小程序code")
    String code;

    @ApiModelProperty(name = "wxName", notes = "微信名")
    String wxName;

    @ApiModelProperty(name = "avatarUrl", notes = "头像url")
    String avatarUrl;
}
