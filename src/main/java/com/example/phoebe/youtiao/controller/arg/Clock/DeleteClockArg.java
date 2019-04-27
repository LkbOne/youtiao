package com.example.phoebe.youtiao.controller.arg.Clock;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class DeleteClockArg extends BaseArg {
    String id;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}

