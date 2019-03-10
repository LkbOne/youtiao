package com.example.phoebe.youtiao.api.vo;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.ToString;

/**
 * Created By hugh, 2019/03/04
 **/
@Data
@ToString(callSuper = true)
public class BasePageVo extends BaseArg {
    // 当前页
//    @ApiModelProperty("当前页")
    private Integer pageNum;
    // 分页大小
//    @ApiModelProperty("分页大小")
    private Integer pageSize;

    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}