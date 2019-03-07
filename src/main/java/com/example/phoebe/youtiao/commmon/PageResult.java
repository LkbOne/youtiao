package com.example.phoebe.youtiao.commmon;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 6941956017537570294L;
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalCount;
    private List<T> result;
    private Long time;

}
