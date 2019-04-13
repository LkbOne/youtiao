package com.example.phoebe.youtiao.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClockEntity {
    String id;
    String aid;
    Date time;
    Integer status;
    Integer cycle;
    String name;
    String description;
    Date createTime;
    Date lastModifyTime;
}
