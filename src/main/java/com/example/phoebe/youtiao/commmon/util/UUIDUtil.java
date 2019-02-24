package com.example.phoebe.youtiao.commmon.util;

import java.util.UUID;

/**
 * @Auther: smallfan
 * @Date: created in 下午4:35 2018/4/16
 * @Description:
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
