package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteBudgetVo extends BaseArg {
    String id;
}
