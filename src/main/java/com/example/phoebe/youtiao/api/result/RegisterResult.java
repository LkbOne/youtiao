package com.example.phoebe.youtiao.api.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterResult implements Serializable {
    @ApiModelProperty(name = "token", notes = "返回的token")
    String token;
    @ApiModelProperty(name = "accountId", notes = "用户id")
    String accountId;
}
