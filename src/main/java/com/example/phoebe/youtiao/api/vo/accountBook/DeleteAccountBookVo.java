package com.example.phoebe.youtiao.api.vo.accountBook;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteAccountBookVo extends BaseArg {

    @ApiModelProperty(value = "id", notes = "账本id")
    String id;
}
