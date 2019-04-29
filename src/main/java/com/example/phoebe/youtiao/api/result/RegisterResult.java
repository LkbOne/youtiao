package com.example.phoebe.youtiao.api.result;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterResult implements Serializable {
    String token;
    String accountId;
}
