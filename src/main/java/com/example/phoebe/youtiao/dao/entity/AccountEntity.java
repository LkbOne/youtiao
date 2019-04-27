package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountEntity implements Serializable {
    String id;
    String account;
    String password;
    Integer status;
    String openHistory;
    Date createTime;
    Date lastModifyTime;
    String name;
    String realName;
    String signature;
    String phone;
    String avatarFPath;

}
