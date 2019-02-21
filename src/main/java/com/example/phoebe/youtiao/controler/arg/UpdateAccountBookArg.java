package com.example.phoebe.youtiao.controler.arg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class UpdateAccountBookArg implements Serializable {
    @ApiModelProperty(value = "accountBookId", notes = "账本id")
    String accountBookId;

    @ApiModelProperty(value = "name", notes = "名字")
    String name;

    @ApiModelProperty(value = "color", notes = "颜色")
    Integer color;

    public boolean isWrongParams(){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(accountBookId)){
            return true;
        }
        return false;
    }
}
