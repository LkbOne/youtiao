package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QueryAccountBookByAbIdResult implements Serializable {
    List<QueryBudgetByIdResult> queryBudgetByIdResultList;
}
