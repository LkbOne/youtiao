package com.example.phoebe.youtiao.api.vo.budget;

import com.example.phoebe.youtiao.api.vo.BasePageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListBudgetVo extends BasePageVo {
    String accountBookId;
}
