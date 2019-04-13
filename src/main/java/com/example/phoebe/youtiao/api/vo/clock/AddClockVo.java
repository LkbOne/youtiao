package com.example.phoebe.youtiao.api.vo.clock;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AddClockVo implements Serializable {
    String aid;
    String name;
    String description;
    Date time;
    Integer status;
    Integer cycle;
}