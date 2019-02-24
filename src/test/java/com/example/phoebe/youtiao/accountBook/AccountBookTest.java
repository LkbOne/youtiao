package com.example.phoebe.youtiao.accountBook;

import com.example.phoebe.youtiao.api.AccountBookService;
import com.example.phoebe.youtiao.api.vo.accountBook.AddAccountBookVo;
import com.example.phoebe.youtiao.api.vo.accountBook.DeleteAccountBookVo;
import com.example.phoebe.youtiao.api.vo.accountBook.GetAccountBookByIdVo;
import com.example.phoebe.youtiao.api.vo.accountBook.UpdateAccountBookVo;
import com.example.phoebe.youtiao.commmon.ModelResult;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class AccountBookTest {
    @Autowired
    AccountBookService accountBookService;

    @Test
    public void addAccountBook() {
        AddAccountBookVo vo = new AddAccountBookVo();
        vo.setColor(1);
        vo.setName("hello world");
        vo.setStatus(1);
        ModelResult  aa = accountBookService.addAccountBook(vo);
        System.out.println();
    }

    @Test
    public void updateAccountBook() {
        UpdateAccountBookVo vo = new UpdateAccountBookVo();
        vo.setId("278a66b2adce4a64863db9d62177b0ec");
        vo.setColor(3);
        vo.setName("hello life");
        vo.setStatus(3);
        ModelResult  aa = accountBookService.updateAccountBook(vo);
        System.out.println();
    }

    @Test
    public void getAccountBookById(){
        GetAccountBookByIdVo vo = new GetAccountBookByIdVo();
        vo.setId("278a66b2adce4a64863db9d62177b0ec");

        ModelResult aa = accountBookService.getAccountBookById(vo);
        System.out.println();
    }

    @Test
    public void deleteAccountBookById(){
        DeleteAccountBookVo vo = new DeleteAccountBookVo();
        vo.setId("137a3243d59d4398bc045142dccf5dc2");

        ModelResult aa = accountBookService.deleteAccountBookById(vo);
        System.out.println();
    }
}
