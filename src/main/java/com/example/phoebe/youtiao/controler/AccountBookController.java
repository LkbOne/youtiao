package com.example.phoebe.youtiao.controler;

import com.example.phoebe.youtiao.commmon.ModelResult;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountBook")
@Slf4j
public class AccountBookController {
    @ApiOperation(value = "添加账本")
    @RequestMapping(value = "addAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addAccountBook(){

    }

    @ApiOperation(value = "更新账本")
    @RequestMapping(value = "updateAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult updateAccountBook(){

    }

    @ApiOperation(value = "删除账本")
    @RequestMapping(value = "deleteAccountBookById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult deleteAccountBookById(){

    }

    @ApiOperation(value = "通过id获得账本详情")
    @RequestMapping(value = "getAccountBookById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult getAccountBookById(){

    }

    @ApiOperation(value = "展示所有的账本")
    @RequestMapping(value = "listAccountBook", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listAccountBook(){

    }

}
