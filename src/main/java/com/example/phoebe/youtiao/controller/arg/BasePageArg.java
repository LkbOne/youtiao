package com.example.phoebe.youtiao.controller.arg;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author hugh
 */
@Data
public class BasePageArg extends BaseArg{
    @ApiModelProperty(value = "要查询的页号", allowableValues = "range[1, infinity]")
    private Integer pageNum;
    @ApiModelProperty(value = "分页大小", allowableValues = "range[1, 50]")
    private Integer pageSize;

    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}
