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

//    @ApiModelProperty(name = "修改历史", notes = "1为这一次打开， 0为正常状态")
    Integer openHistory;
}
