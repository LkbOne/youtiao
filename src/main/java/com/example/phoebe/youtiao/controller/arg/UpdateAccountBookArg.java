package com.example.phoebe.youtiao.controller.arg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class UpdateAccountBookArg extends BaseArg {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String id;

    @ApiModelProperty(name = "name", notes = "名字")
    String name;

    @ApiModelProperty(name = "color", notes = "颜色")
    Integer color;

    @ApiModelProperty(name = "status", notes = "状态")
    Integer status;

    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
