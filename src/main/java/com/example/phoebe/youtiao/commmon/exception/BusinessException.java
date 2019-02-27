package com.example.phoebe.youtiao.commmon.exception;

import lombok.*;

/**
 * Created by zhangjh on 2017/5/15.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 5662952072771160931L;

    private int errCode;

    private String errMsg;

    private String errDescription;

    public BusinessException(int errCode, String errDescription) {
        super(errCode + " : " + errDescription);
        this.errCode = errCode;
        this.errMsg = "";
        this.errDescription = errDescription;
    }
}
