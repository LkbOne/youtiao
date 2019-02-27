package com.example.phoebe.youtiao.api.vo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    @ApiModelProperty(name = "code", notes = "小程序返回的code")
    String code;
}
