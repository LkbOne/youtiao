package com.example.phoebe.youtiao.api;

import com.example.phoebe.youtiao.commmon.ModelResult;

public interface AccountBookService {
    ModelResult addAccountBook();

    ModelResult updateAccountBook();

    ModelResult deleteAccountBookById();

    ModelResult getAccountBookById();

    ModelResult listAccountBook();
}
