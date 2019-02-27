package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountEntity implements Serializable {
    String id;
    String account;
    Integer status;
    String openHistory;
    Date createTime;
    Date updateTime;
}
