package com.example.phoebe.youtiao.commmon.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RedisLoginEntity implements Serializable {
    private String sessionKey;
}
