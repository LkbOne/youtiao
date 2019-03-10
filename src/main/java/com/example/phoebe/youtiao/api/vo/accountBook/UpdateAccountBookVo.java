package com.example.phoebe.youtiao.api.vo.accountBook;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;


@Data
public class UpdateAccountBookVo extends BaseArg {
//    @ApiModelProperty(value = "id", notes = "账本id")
    String id;

//    @ApiModelProperty(value = "name", notes = "名字")
    String name;

//    @ApiModelProperty(value = "color", notes = "颜色")
    Integer color;

//    @ApiModelProperty(value = "status", notes = "状态")
    Integer status;
}
