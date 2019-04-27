package com.example.phoebe.youtiao.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorizeResult implements Serializable {
    String token;
    String accountId;
}
