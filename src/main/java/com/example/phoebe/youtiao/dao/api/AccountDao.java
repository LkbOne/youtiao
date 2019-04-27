package com.example.phoebe.youtiao.dao.api;

import com.example.phoebe.youtiao.dao.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {

    int addAccount(AccountEntity accountEntity);

    int updateAccount(AccountEntity accountEntity);

    int deleteAccountById(@Param("id") String id);

    AccountEntity queryAccountById(@Param("id") String id);

    int queryAccountByAccountAndPassword(@Param("account") String account,
                                        @Param("password") String password);
}
