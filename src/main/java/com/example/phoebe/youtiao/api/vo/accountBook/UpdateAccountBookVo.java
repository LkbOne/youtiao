package com.example.phoebe.youtiao.api.vo.accountBook;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;


@Data
public class UpdateAccountBookVo extends BaseArg {
    String id;

    String name;

    Integer color;

    Integer status;
}
