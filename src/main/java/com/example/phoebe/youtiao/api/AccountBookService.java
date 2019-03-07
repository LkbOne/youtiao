package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.api.result.GetAccountBookByIdResult;
import com.example.phoebe.youtiao.api.result.ListAccountBookResult;
import com.example.phoebe.youtiao.api.vo.accountBook.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.PageResult;

public interface AccountBookService {
    ModelResult addAccountBook(AddAccountBookVo vo);

    ModelResult updateAccountBook(UpdateAccountBookVo vo);

    ModelResult deleteAccountBookById(DeleteAccountBookVo vo);

    ModelResult<GetAccountBookByIdResult> getAccountBookById(GetAccountBookByIdVo vo);

    ModelResult<PageResult<ListAccountBookResult>> listAccountBook(ListAccountBookVo vo);

    ModelResult resetOpenHistory(ResetOpenHistoryVo vo);
}
