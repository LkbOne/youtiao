package com.example.phoebe.youtiao.controller.arg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
@Data
//@ApiModel
public class AddAccountBookArg extends BaseArg {

//    @ApiModelProperty(name = "name", notes = "名字")
    String name;
//
//    @ApiModelProperty(name = "color", notes = "颜色")
    Integer color;

//    @ApiModelProperty(name = "status", notes = "", allowEmptyValue = true)
    Integer status;

//    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if (StringUtils.isEmpty(name) || StringUtils.isBlank(getAccountId())){
            return true;
        }
        return false;
    }
}
