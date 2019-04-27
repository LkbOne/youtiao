package com.example.phoebe.youtiao.commmon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocketEnum {

    SUCCESS_CONNECT_CONTENT(1),
    CLOCK_ID(2),
    CLIENT_MESSAGE(3);

    int type;
}
