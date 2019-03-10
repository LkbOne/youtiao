package com.example.phoebe.youtiao.api.vo.accountBook;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddAccountBookVo extends BaseArg {
//    @ApiModelProperty(value = "name", notes = "名字")
    String name;

//    @ApiModelProperty(value = "color", notes = "颜色")
    Integer color;

//    @ApiModelProperty(value = "status", notes = "")
    Integer status;
}
