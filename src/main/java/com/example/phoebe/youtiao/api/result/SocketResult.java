package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocketResult implements Serializable {
    String message;
    int type;
}