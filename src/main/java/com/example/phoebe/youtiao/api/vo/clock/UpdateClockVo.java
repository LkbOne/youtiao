package com.example.phoebe.youtiao.api.vo.clock;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateClockVo implements Serializable {
    String id;
    String aid;
    String name;
    String description;
    Date time;
    Integer status;
    Integer cycle;
}
