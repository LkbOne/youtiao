package com.example.phoebe.youtiao.api.vo.accountBook;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddAccountBookVo implements Serializable {
    @ApiModelProperty(value = "name", notes = "名字")
    String name;

    @ApiModelProperty(value = "color", notes = "颜色")
    Integer color;

    @ApiModelProperty(value = "status", notes = "")
    Integer status;
}
