package com.example.phoebe.youtiao.controller.arg;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class GetTPathArg implements Serializable {
    /**
     * {@link com.example.phoebe.youtiao.commmon.enums.FileTypeEnum}
    * */

    Integer type;
    MultipartFile file;

    public boolean isWrongParams(){
        if(null == file){
            return true;
        }
        return false;
    }
}
