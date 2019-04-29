package com.example.phoebe.youtiao.api.result;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryCustomDataByIdResult implements Serializable {
    String id;
    String name;
    String realName;
    String phone;
    String signature;
    String avatarFPath;
    String account;
}
