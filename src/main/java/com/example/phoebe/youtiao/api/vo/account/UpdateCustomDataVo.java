package com.example.phoebe.youtiao.api.vo.account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCustomDataVo extends BaseArg {

    @ApiModelProperty(name = "name", notes = "账号名")
    String name;
    @ApiModelProperty(name = "realName", notes = "真实名字")
    String realName;
    @ApiModelProperty(name = "phone", notes = "电话号码")
    String phone;

    @ApiModelProperty(name = "password", notes = "密码")
    String password;

    @ApiModelProperty(name = "newPassword", notes = "新密码")
    String newPassword;

    @ApiModelProperty(name = "signature", notes = "签名")
    String signature;
    @ApiModelProperty(name = "path", notes = "path")
    String path;
}
