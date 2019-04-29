package com.example.phoebe.youtiao.controller;

import com.example.phoebe.youtiao.api.AccountBookService;
import com.example.phoebe.youtiao.api.ExpensesService;
import com.example.phoebe.youtiao.api.result.*;
import com.example.phoebe.youtiao.api.vo.SumInAndOutExpensesVo;
import com.example.phoebe.youtiao.api.vo.expenses.*;
import com.example.phoebe.youtiao.commmon.ModelResult;
import com.example.phoebe.youtiao.commmon.SHErrorCode;
import com.example.phoebe.youtiao.commmon.annotion.TokenCheckTrigger;
import com.example.phoebe.youtiao.commmon.enums.ExcelConfigEnum;
import com.example.phoebe.youtiao.commmon.util.BeanUtil;
import com.example.phoebe.youtiao.commmon.util.DateUtil;
import com.example.phoebe.youtiao.commmon.util.ExcelUtil;
import com.example.phoebe.youtiao.controller.arg.Expenses.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/expenses")
@Slf4j
public class ExpensesController {
    @Autowired
    ExpensesService expensesService;

    @Autowired
    AccountBookService accountBookService;

    @TokenCheckTrigger
    @RequestMapping(value = "addExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult addExpenses(@RequestHeader String token, @RequestBody AddExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.addExpenses params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        AddExpensesVo vo = BeanUtil.copy(arg, AddExpensesVo.class);
        return expensesService.addExpenses(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "updateExpenses", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ModelResult updateExpenses(@RequestHeader String token, @RequestBody UpdateExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.updateExpenses params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        UpdateExpensesVo vo = BeanUtil.copy(arg, UpdateExpensesVo.class);
        return expensesService.updateExpenses(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "deleteExpensesById", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ModelResult deleteExpensesById(@RequestHeader String token, @RequestBody DeleteExpensesById arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.deleteExpensesById params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }

        DeleteExpensesVo vo = BeanUtil.copy(arg, DeleteExpensesVo.class);
        return expensesService.deleteExpensesById(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "QueryExpensesById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult<QueryExpensesByIdResult> getExpensesById(@RequestHeader String token, @RequestBody QueryExpensesByIdArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.QueryExpensesById params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        QueryExpensesByIdVo vo = BeanUtil.copy(arg, QueryExpensesByIdVo.class);

        return expensesService.queryExpensesById(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "listExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ModelResult listExpenses(@RequestHeader String token, @RequestBody ListExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.listExpensesByAccountBookId params error arg:{}", arg);
            return new ModelResult(SHErrorCode.PARAMS_ERROR);
        }
        ListExpensesVo vo = BeanUtil.copy(arg, ListExpensesVo.class);
        return expensesService.listExpenses(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "sumInAndOutExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<SumInAndOutExpensesResult> sumInAndOutExpenses(@RequestHeader String token, @RequestBody SumInAndOutExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.sumInAndOutExpenses params is error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        SumInAndOutExpensesVo vo = BeanUtil.copy(arg, SumInAndOutExpensesVo.class);
        return expensesService.sumInAndOutExpenses(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "sumThisDayExpenses", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<SumThisDayExpensesResult> sumThisDayExpenses(@RequestHeader String token, @RequestBody SumThisDayExpensesArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.sumThisDayExpenses params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        SumThisDayExpensesVo vo = BeanUtil.copy(arg, SumThisDayExpensesVo.class);
        return expensesService.sumThisDayExpenses(vo);
    }
    @TokenCheckTrigger
    @RequestMapping(value = "showEveryDayExpensesDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<List<List<EveryDayExpensesDetailResult>>> showEveryDayExpensesDetail(@RequestHeader String token, @RequestBody EveryDayExpensesDetailArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.showEveryDayExpensesDetail params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        EveryDayExpensesDetailVo vo = BeanUtil.copy(arg, EveryDayExpensesDetailVo.class);
        return expensesService.showEveryDayExpensesDetail(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "expensesGroupClassificationByTypeStatistic", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<ExpensesGroupClassificationByTypeStatisticResult> expensesGroupClassificationByTypeStatistic(@RequestHeader String token, @RequestBody ExpensesGroupClassificationByTypeStatisticArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.expensesGroupClassificationByTypeStatistic params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        ExpensesGroupClassificationByTypeStatisticVo vo = BeanUtil.copy(arg, ExpensesGroupClassificationByTypeStatisticVo.class);
        return expensesService.expensesGroupClassificationByTypeStatistic(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "showExpensesTrendBetweenIntervalByAccountBookId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    ModelResult<ShowExpensesTreadResult> showExpensesTrendBetweenIntervalByAccountBookId(@RequestHeader String token, @RequestBody ShowExpensesTrendBetweenIntervalByAccountBookIdArg arg){
        if(arg.isWrongParams()){
            log.warn("ExpensesController.showExpensesTrendBetweenIntervalByAccountBookId params error arg:{}", arg);
            return new ModelResult<>(SHErrorCode.PARAMS_ERROR);
        }
        ShowExpensesTrendBetweenIntervalByAccountBookIdVo vo = BeanUtil.copy(arg, ShowExpensesTrendBetweenIntervalByAccountBookIdVo.class);
        return expensesService.showExpensesTrendBetweenIntervalByAccountBookId(vo);
    }

    @TokenCheckTrigger
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    void exportExcel(@RequestParam("accountBookId") String accountBookId, @RequestParam("interval") String interval, @RequestParam("date") String date, HttpServletResponse httpServletResponse) throws Exception {
        ExportExcelArg arg = new ExportExcelArg();
        arg.setAccountBookId(accountBookId);
        arg.setDate(new Date(Long.valueOf(date)));
//        arg.setDate(date);
//        arg.setInterval(Integer.valueOf(interval));
        arg.setInterval(Integer.valueOf(interval));
        if(arg.isWrongParams()){
            log.warn("ExpensesController.exportExcel params error arg:{}", arg);
            return;
        }

        ExportExcelVo vo = BeanUtil.copy(arg, ExportExcelVo.class);
        ExportExpensesInfoResult resultModelResult = expensesService.exportExpensesInfoToExcel(vo);
        Long startTime = resultModelResult.getBeginTime().getTime();
        Long endTime = resultModelResult.getEndTime().getTime();
        StringBuilder title = new StringBuilder(resultModelResult.getAccountBookName());
        String filename = doGenerateExpensesEnrollFilename(startTime, endTime, title).toString();
        Map<ExcelConfigEnum, Object> excelConfigMap = new LinkedHashMap<>();
        excelConfigMap.put(ExcelConfigEnum.FILE_NAME, filename);
        excelConfigMap.put(ExcelConfigEnum.SHEET_NAME, "enrollSheet");
        excelConfigMap.put(ExcelConfigEnum.CONTENT_TYPE, "application/octet-stream");
        XSSFWorkbook xssfWorkbook = ExcelUtil.generateXSSFExcel();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet(excelConfigMap.get(ExcelConfigEnum.SHEET_NAME).toString());
        xssfSheet.setDefaultColumnWidth(20);
        List<String> titleList = doGenerateExcelTitleList();
        List<List<Object>> expensesInfosList = doGenerateExcelExpensesInfoList(resultModelResult);
        ExcelUtil.fillContent(xssfSheet, titleList, expensesInfosList);
        filename = URLEncoder.encode(filename, "UTF-8");
        httpServletResponse.setContentType(excelConfigMap.get(ExcelConfigEnum.CONTENT_TYPE).toString());
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + filename);
        OutputStream bos = httpServletResponse.getOutputStream();
        xssfWorkbook.write(bos);
        httpServletResponse.flushBuffer();
        bos.flush();
        bos.close();

    }
    private StringBuilder doGenerateExpensesEnrollFilename(Long startTime, Long endTime, StringBuilder title) {
        String startTimeFormat = DateUtil.dateMillis2String(startTime, "yyyy年MM月dd日");
        String endTimeFormat = DateUtil.dateMillis2String(endTime, "yyyy年MM月dd日");
        return new StringBuilder(startTimeFormat).append("至").append(endTimeFormat).append(title).append("表单数据.xlsx");
    }

    /**
     * 构造excel标题信息
     */
    private List<String> doGenerateExcelTitleList() {
        List<String> titleList = new ArrayList<>();
        titleList.add("花费时间");
        titleList.add("收入");
        titleList.add("支出");
        titleList.add("结余");
        return titleList;
    }

    /**
     * 构造excel内容信息
     */
    List<List<Object>> doGenerateExcelExpensesInfoList(ExportExpensesInfoResult infoResult) throws Exception {
        List<List<Object>> expensesInfosList = Lists.newArrayList();
        List<ExportExpensesInfoResult.ExpensesInfo> expensesInfos = infoResult.getExpensesInfoList();
        for (ExportExpensesInfoResult.ExpensesInfo expensesInfo : expensesInfos) {

            List<Object> enrollInfos = Lists.newArrayList();

            enrollInfos.add(expensesInfo.getDate());
            enrollInfos.add(String.format("%.2f",expensesInfo.getInExpenses()));
            enrollInfos.add(String.format("%.2f",expensesInfo.getOutExpenses()));
            enrollInfos.add(String.format("%.2f",expensesInfo.getSurplus()));
            expensesInfosList.add(enrollInfos);
        }
        List<Object> enrollInfos = Lists.newArrayList();
        enrollInfos.add("总计");
        enrollInfos.add(String.format("%.2f",infoResult.getTotalInExpenses()));
        enrollInfos.add(String.format("%.2f",infoResult.getTotalOutExpenses()));
        enrollInfos.add(String.format("%.2f",infoResult.getTotalSurplus()));
        expensesInfosList.add(enrollInfos);
        return expensesInfosList;
    }
}
