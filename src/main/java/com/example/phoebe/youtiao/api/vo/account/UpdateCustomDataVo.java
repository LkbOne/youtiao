package com.example.phoebe.youtiao.api.vo.account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateCustomDataVo extends BaseArg {

    String name;
    String realName;
    String phone;
    String password;
    String newPassword;
    String signature;
    String path;
}
