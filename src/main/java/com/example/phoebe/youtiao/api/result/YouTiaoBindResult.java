package com.example.phoebe.youtiao.api.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class YouTiaoBindResult implements Serializable {
    @ApiModelProperty(name = "token")
    String token;
}
