package com.example.phoebe.youtiao.api.vo.accountBook;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class AddAccountBookVo extends BaseArg {
    String name;

    Integer color;

    Integer status;
}
