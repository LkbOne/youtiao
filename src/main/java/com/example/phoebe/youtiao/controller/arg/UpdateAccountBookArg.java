package com.example.phoebe.youtiao.controller.arg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class UpdateAccountBookArg implements Serializable {
    @ApiModelProperty(value = "accountBookId", notes = "账本id")
    String id;

    @ApiModelProperty(value = "name", notes = "名字")
    String name;

    @ApiModelProperty(value = "color", notes = "颜色")
    Integer color;

    @ApiModelProperty(value = "status", notes = "状态")
    Integer status;
    public boolean isWrongParams(){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
