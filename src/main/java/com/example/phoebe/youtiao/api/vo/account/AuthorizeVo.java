package com.example.phoebe.youtiao.api.vo.account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorizeVo implements Serializable {
    String code;
    String wxName;
    String avatarUrl;
}
