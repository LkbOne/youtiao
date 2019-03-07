package com.example.phoebe.youtiao.controller.arg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ResetOpenHistoryArg extends BaseArg {
    @ApiModelProperty(name = "accountBookId", notes = "账本id")
    String accountBookId;
}
