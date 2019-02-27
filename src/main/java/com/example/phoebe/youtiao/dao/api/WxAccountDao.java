package com.example.phoebe.youtiao.dao.api;

import com.example.phoebe.youtiao.dao.entity.WxAccountEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WxAccountDao {
    int addWxAccount(WxAccountEntity wxAccountEntity);

    int updateWxAccount(WxAccountEntity wxAccountEntity);

    int deleteWxAccountByOpenid(@Param("openid") String openid);

    WxAccountEntity queryWxAccountByOpenid(@Param("openid") String openid);

    String queryAccountIdByOpenid(@Param("openid") String openid);

    String queryOpenidByAccountId(@Param("accountId") String accountId);
}
