package com.example.phoebe.youtiao.commmon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Map;

@Slf4j
public class TokenUtil {
    private static final String SECRET = "session_secret";

    //发布者 后面一块去校验
    private static final String ISSUER = "phoebe";

    //生成token的操作
    public static String genToken(Map<String, String> claims){
        try {
            //签名算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER).withExpiresAt(DateUtils.addDays(new Date(), 1));
            //相当于将claims存储在token中
            claims.forEach((k,v) -> builder.withClaim(k, v));
            return builder.sign(algorithm);
        } catch (IllegalArgumentException e) {
            log.warn("");
            return null;
        }
    }

    public static String getTokenSetAccountId(String accountId, String session_key){
        Map<String, String> claims = Maps.newHashMap();
        claims.put("accountId", accountId);
        claims.put("session_key", session_key);
        return genToken(claims);
    }

    //验证token
    public static Map<String, String> verifyToken(String token)  {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (IllegalArgumentException e) {
            log.warn("");
            return null;
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt =  verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = Maps.newHashMap();
        map.forEach((k,v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }
    public static String getAccountIdByToken(String token){
        Map<String, String> claims = verifyToken(token);
        if(null == claims){
            return null;
        }
        return claims.get("accountId");
    }
}
