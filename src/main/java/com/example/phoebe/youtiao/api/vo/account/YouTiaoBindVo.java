package com.example.phoebe.youtiao.api.vo.account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class YouTiaoBindVo extends BaseArg {
    String code;
    String wxName;
    String avatarUrl;
}
