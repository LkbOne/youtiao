package com.example.phoebe.youtiao.controller.arg;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseArg implements Serializable {
//    @ApiModelProperty(hidden = true)
    private String token;

//    @ApiModelProperty(hidden = true)
    private String sessionKey;

//    @ApiModelProperty(hidden = true)
    private String openid;

//    @ApiModelProperty(hidden = true)
    private String accountId;

//    @ApiModelProperty(hidden = true)
    private Double miniAppVersion;

//    @ApiModelProperty(hidden = true)
    private Integer miniAppSource;
}
