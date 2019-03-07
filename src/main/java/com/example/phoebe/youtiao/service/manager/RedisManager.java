package com.example.phoebe.youtiao.service.manager;

import com.example.phoebe.youtiao.commmon.model.RedisLoginEntity;
import com.example.phoebe.youtiao.commmon.util.RedisUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisManager {
    private RedisUtil redisUtil = new RedisUtil();
    final static String PRE_TOKEN = "pre_token";
    final static Integer TOKEN_TIME = 2 * 60 * 60;
    Gson gs = new Gson();

    public void setToken2Redis(String key, String value){
        key = PRE_TOKEN + key;
        System.out.println("setToken2Redis key:" + key);
        System.out.println("setToken2Redis value:" + value);
        redisUtil.setex(key, value, TOKEN_TIME);
    }

    public RedisLoginEntity getLoginFromRedis(String key){
        key = PRE_TOKEN + key;
        String value = redisUtil.get(key, 0);
        if (value == null){
            return null;
        }
        System.out.println("getLoginFromRedis key:" +key);
        System.out.println("getLoginFromRedis value:" + value);
        RedisLoginEntity redisLoginEntity = gs.fromJson(value, RedisLoginEntity.class);
        return redisLoginEntity;
    }
}
