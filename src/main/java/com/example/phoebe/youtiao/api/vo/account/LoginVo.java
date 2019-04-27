package com.example.phoebe.youtiao.api.vo.account;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    String account;
    String password;
}
