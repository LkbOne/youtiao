package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClockResult implements Serializable {
    Integer cycle;
    long time;
    String name;
    String description;

}
