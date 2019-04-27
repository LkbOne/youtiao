package com.example.phoebe.youtiao.api.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryCustomDataByIdResult implements Serializable {
    @ApiModelProperty(name = "id", notes = "用户id")
    String id;
    @ApiModelProperty(name = "name", notes = "账号名")
    String name;
    @ApiModelProperty(name = "realName", notes = "真实名字")
    String realName;
    @ApiModelProperty(name = "phone", notes = "电话号码")
    String phone;
    @ApiModelProperty(name = "signature", notes = "签名")
    String signature;
    @ApiModelProperty(name = "avatarFPath", notes = "头像永久PATH")
    String avatarFPath;
    @ApiModelProperty(name = "account", notes = "账号")
    String account;
}
