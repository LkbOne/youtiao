package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.api.vo.BasePageVo;
import com.example.phoebe.youtiao.controller.arg.BaseArg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ListBudgetVo extends BasePageVo {
    String accountBookId;
}
