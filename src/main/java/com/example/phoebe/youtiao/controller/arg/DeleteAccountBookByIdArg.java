package com.example.phoebe.youtiao.controller.arg;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;


@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteAccountBookByIdArg extends BaseArg {
    String id;
    public boolean isWrongParams(){
        if(StringUtils.isEmpty(id)){
            return true;
        }
        return false;
    }
}
