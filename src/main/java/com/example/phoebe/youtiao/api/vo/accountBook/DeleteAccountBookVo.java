package com.example.phoebe.youtiao.api.vo.accountBook;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteAccountBookVo implements Serializable {

    @ApiModelProperty(value = "id", notes = "账本id")
    String id;
}
