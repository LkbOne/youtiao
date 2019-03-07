package com.example.phoebe.youtiao.commmon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.common.collect.Maps;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
public class TokenUtil {

    @Data
    public static class TokenEntity {
        private String token;
        private String openid;
        private String sessionKey;
        private String accountId;
    }


    private static final String TOKEN_ENCRYPT_KEY = "pP8UOR8oU4SemLF3iPqNmw";
    //发布者 后面一块去校验

    //生成token的操作
    public static String generateToken(String openid, String sessionKey, String accountId) {

        Map<String, Object>map = new HashMap<>();
        map.put("openid", openid);
        map.put("sessionKey", sessionKey);
        map.put("accountId", accountId);

        String json = GsonUtil.getGson().toJson(map);
        if (null == json) {
            log.error("Token serializable failed, map={}", map);
            return null;
        }

        String token = AESUtil.encode(TOKEN_ENCRYPT_KEY, json);
        if (null == token) {
            log.error("encode failed, json={}", json);
            return null;
        }

        return token;
    }

    //验证token
    public static TokenEntity getTokenEntity(String token) {

        try {
            String decJson = AESUtil.decode(TOKEN_ENCRYPT_KEY, token);

            if (null == decJson) {
                log.error("dec toString failed, token={}", token);
                return null;
            }

            TokenEntity tokenEntity = GsonUtil.getGson().fromJson(decJson, TokenEntity.class);
            if(null == tokenEntity) {
                log.error("TokenEntity deserialization failed, decJson={}", decJson);
                return null;
            }
            tokenEntity.setToken(token);

            return tokenEntity;

        } catch (Exception e) {
            log.error("AESUtil decode failed, exception:{}", e);
            return null;
        }

    }
}
