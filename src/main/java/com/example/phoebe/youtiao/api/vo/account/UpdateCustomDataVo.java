package com.example.phoebe.youtiao.api.vo.account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCustomDataVo extends BaseArg {

    String name;
    String realName;
    String phone;
    String password;
    String newPassword;
    String signature;
    String path;
}
