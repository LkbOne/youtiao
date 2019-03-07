package com.example.phoebe.youtiao.service.manager;

import com.example.phoebe.youtiao.api.vo.accountBook.AddAccountBookVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.util.UUIDUtil;
import com.example.phoebe.youtiao.dao.api.AccountBookDao;
import com.example.phoebe.youtiao.dao.entity.AccountBookEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountBookManager {
    @Autowired
    AccountBookDao accountBookDao;

    @Transactional
    public boolean addAccountBook(AccountBookEntity entity) {
        String id = UUIDUtil.getUUID();
        entity.setId(id);
        entity.setOpenHistory(0);
        if(accountBookDao.addAccountBook(entity) != 1){
            return false;
        }
        return true;
    }
}
