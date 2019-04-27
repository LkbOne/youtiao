package com.example.phoebe.youtiao.api.vo.account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;

import java.io.Serializable;

@Data
public class YouTiaoBindVo extends BaseArg {
    String code;
    String wxName;
    String avatarUrl;
}
