package com.example.phoebe.youtiao.service.manager;


import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.model.RedisLoginEntity;
import com.example.phoebe.youtiao.commmon.util.GsonUtil;
import com.example.phoebe.youtiao.commmon.util.TokenUtil;
import com.example.phoebe.youtiao.controller.arg.BaseArg;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenManager {

    @Autowired
    private RedisManager redisManager;
    private String loginBlackListJson;

    public ModelResult<BaseArg> getUserInfo(String token) {
        BaseArg baseArg = new BaseArg();
        baseArg.setToken(token);

        TokenUtil.TokenEntity tokenEntity = TokenUtil.getTokenEntity(token);
        if (null == tokenEntity) {
            log.error("get tokenEntity failed, token={}", token);
            System.out.printf("get tokenEntity failed, toke");
            return new ModelResult<>(SHErrorCode.LOGIN_TOKEN_INVALID);
        }

        RedisLoginEntity redisLoginEntity = redisManager.getLoginFromRedis(tokenEntity.getOpenid());
        if (redisLoginEntity != null) {
            System.out.println("redisLoginEntity:" + redisLoginEntity.toString());
            System.out.println("tokenEntity:" + tokenEntity.toString());
            if (!tokenEntity.getSessionKey().equals(redisLoginEntity.getSessionKey())) {
                log.info("tokenEntity.sessionKey different with redisLoginEntity.sessionKey, tokenEntity.getSessionKey()={}, redisLoginEntity.getSessionKey()={}", tokenEntity.getSessionKey(), redisLoginEntity.getSessionKey());
                return new ModelResult<>(SHErrorCode.LOGIN_TOKEN_INVALID);
            }

            baseArg.setOpenid(tokenEntity.getOpenid());
            baseArg.setSessionKey(tokenEntity.getSessionKey());
            baseArg.setAccountId(tokenEntity.getAccountId());
            log.info("token:{} openid:{} sessionkey:{} uid:{}", token, tokenEntity.getOpenid(), tokenEntity.getSessionKey(), tokenEntity.getAccountId());
            log.info("base arg :{}", baseArg);


//            // 黑名单禁止登录
//            List<String> loginBlackList = GsonUtil.getGson().fromJson(loginBlackListJson, ArrayList.class);
//            if (CollectionUtils.isNotEmpty(loginBlackList)) {
//
//                for (String blackOpenId : loginBlackList) {
//                    if (StringUtils.isBlank(blackOpenId)) {
//                        continue;
//                    }
//
//                    if (blackOpenId.equals(tokenEntity.getOpenid())) {
//                        log.info("authorize be refused, openid={}", tokenEntity.getOpenid());
//                        return new ModelResult<>(SHErrorCode.LOGIN_BLACK_LIST);
//                    }
//                }
//            }

        } else {
            log.info("token expired need authorize again token:{}", token);
            return new ModelResult<>(SHErrorCode.LOGIN_TOKEN_INVALID);
        }

        return new ModelResult<>(SHErrorCode.SUCCESS, baseArg);
    }

}
