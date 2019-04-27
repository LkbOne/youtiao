package com.example.phoebe.youtiao.api.result;

import com.example.phoebe.youtiao.commmon.util.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ShowExpensesTreadResult implements Serializable {

    List<TreadResult> inExpenses;
    List<TreadResult> outExpenses;
    List<TreadResult> surplus;

    @Data
    public static class TreadResult{

        private Float number;
        private String date;

        public TreadResult(Float number, Date date){
            this.number = number;
            this.date = DateUtil.formatNoHour(date);
        }
    }
}
