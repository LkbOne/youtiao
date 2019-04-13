package com.example.phoebe.youtiao.api.result;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
public class ListClockResult implements Serializable {

    String id;
    String name;
    String description;
    Integer status;
    Integer cycle;
    long time;
}
