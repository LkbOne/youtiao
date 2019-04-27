package com.example.phoebe.youtiao.api.vo.clock;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteClockVo implements Serializable {
    String uid;
    String id;
}
