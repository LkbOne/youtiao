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

    private Integer pageNum;

    private Integer pageSize;

    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}