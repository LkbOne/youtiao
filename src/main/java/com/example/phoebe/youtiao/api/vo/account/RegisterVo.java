package com.example.phoebe.youtiao.api.vo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVo implements Serializable {

    String code;
    String wxName;
    String avatarUrl;
    String avatarTPath;
    String account;
    String password;
    String phone;
    String name;
    String realName;
    String signature;
}
