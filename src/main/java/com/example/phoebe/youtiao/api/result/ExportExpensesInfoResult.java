package com.example.phoebe.youtiao.api.result;

import com.example.phoebe.youtiao.commmon.util.DateUtil;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ExportExpensesInfoResult implements Serializable {
    Date beginTime;
    Date endTime;
    Float totalInExpenses;
    Float totalOutExpenses;
    Float totalSurplus;
    String accountBookName;
    List<ExpensesInfo> expensesInfoList;

    @Data
    public static class ExpensesInfo{

        private Float inExpenses;
        private Float outExpenses;
        private Float surplus;
        private String date;
        public ExpensesInfo(Float inExpenses, Float outExpenses, Date date){
            this.inExpenses = inExpenses;
            this.outExpenses = outExpenses;
            this.surplus = inExpenses - outExpenses;
            this.date = DateUtil.formatNoHour(date);
        }
    }

}
