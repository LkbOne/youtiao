package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class WxAccountEntity implements Serializable {
    String id;
    String openid;
    String accountId;
    String phone;
    String wxName;
    String avatarUrl;
    Date createTime;
    Date lastModifyTime;
}
