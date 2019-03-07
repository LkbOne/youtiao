package com.example.phoebe.youtiao.dao.api;

import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import com.github.pagehelper.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountBookDao {
    int addAccountBook(AccountBookEntity accountBookEntity);

    int updateAccountBook(AccountBookEntity accountBookEntity);

    int deleteAccountBookById(@Param("id") String accountBookId);

    AccountBookEntity queryAccountBookById(@Param("id") String accountBookId);

    List<AccountBookEntity> listAccountBookByAccountId(@Param("aid") String accountBookId, @Param("page") Page page);

    void updateAccountBookOpenHistory(@Param("beforeStatue") Integer beforeStatue, @Param("nowStatue") Integer nowStatue);

    void updateAccountBookOpenHistoryById(@Param("nowStatue") Integer nowStatue, @Param("id") String id);
}
