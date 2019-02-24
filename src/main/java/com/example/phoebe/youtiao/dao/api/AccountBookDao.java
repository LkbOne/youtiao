package com.example.phoebe.youtiao.dao.api;

import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface AccountBookDao {
    int addAccountBook(AccountBookEntity accountBookEntity);

    int updateAccountBook(AccountBookEntity accountBookEntity);

    int deleteAccountBookById(@Param("id") String accountBookId);

    AccountBookEntity queryAccountBookById(@Param("id") String accountBookId);
}
