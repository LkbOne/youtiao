package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.GetAccountBookByIdResult;
import com.example.phoebe.youtiao.api.result.ListAccountBookResult;
import com.example.phoebe.youtiao.api.vo.accountBook.*;
import com.example.phoebe.youtiao.commmon.ModelResult;

public interface AccountBookService {
    ModelResult addAccountBook(AddAccountBookVo vo);

    ModelResult updateAccountBook(UpdateAccountBookVo vo);

    ModelResult deleteAccountBookById(DeleteAccountBookVo vo);

    ModelResult<GetAccountBookByIdResult> getAccountBookById(GetAccountBookByIdVo vo);

    ModelResult<ListAccountBookResult> listAccountBook(ListAccountBookVo vo);
}
