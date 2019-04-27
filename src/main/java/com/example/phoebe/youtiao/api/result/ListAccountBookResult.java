package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListAccountBookResult implements Serializable {
    String id;
    String name;
    Integer color;
    Long createTime;
    Long lastModifyTime;

    Integer openHistory;
}
