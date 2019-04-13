package com.example.phoebe.youtiao.controller.arg.Clock;

import com.example.phoebe.youtiao.controller.arg.BaseArg;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryClockByIdArg extends BaseArg {
    String id;
}
