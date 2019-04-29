package com.example.phoebe.youtiao.controller.arg.Account;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;

@Data
public class UpdateCustomDataArg extends BaseArg {
    String name;
    String realName;
    String phone;
    String signature;
    String path;
    String password;
    String newPassword;
}
