package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class AccountBookEntity implements Serializable {
    String id;
    String aid;
    String name;
    Integer color;
    Integer status;
    Date createTime;
    Date lastModifyTime;
    Integer openHistory;
}
