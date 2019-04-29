package com.example.phoebe.youtiao.controller.arg;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;


@Data
@EqualsAndHashCode(callSuper = false)
public class GetAccountBookByIdArg extends BaseArg {
//    @ApiModelProperty(name = "id", notes = "账本id")
    String id;

//    @ApiModelProperty(hidden = true)
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
