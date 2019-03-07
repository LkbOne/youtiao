package com.example.phoebe.youtiao.api.vo.accountBook;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResetOpenHistoryVo extends BaseArg {
    String accountBookId;
}
