package com.example.phoebe.youtiao.commmon.util;

import com.example.phoebe.youtiao.commmon.enums.ExcelConfigEnum;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;

/**
 * @Author Joey
 * @Time 2018/8/27.
 *
 * 导出excel工具
 */
public class ExcelUtil {
    private static DealObject dealObject;

    static {
        dealObject = configDealChain();
    }

    /**
     * 生成excel工作簿
     */
    public static XSSFWorkbook generateXSSFExcel() {
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        return xssfWorkbook;
    }

    /**
     * 填充表格信息
     *
     * @param <T> 数据内容类型
     */
    public static <T> void fillContent(XSSFSheet xssfSheet, List<String> titleList, List<List<Object>> dataList) {
        fillContent(xssfSheet, dataList, titleRow -> {
            for (int i = 0; i < titleList.size(); i++) {
                ExcelUtil.createCell(titleRow, i, titleList.get(i));
            }
        }, (datasRow, data) -> {
            for (int i = 0; i < data.size(); i++) {
                ExcelUtil.createCell(datasRow, i, data.get(i));
            }
        });
    }

    /**
     * 填充表格信息
     *
     * @param <T> 数据内容类型
     * @param excelTitlesFill 表格标题
     * @param excelDatasFill 表格数据
     */
    public static <T> void fillContent(XSSFSheet xssfSheet, List<T> datas, ExcelRowFill excelTitlesFill, ExcelInfosFill<T> excelDatasFill) {
        //填充标题
        fillTitles(xssfSheet, 0, excelTitlesFill);
        //填充数据
        fillDatas(xssfSheet, 1, datas, excelDatasFill);
    }

    /**
     * 填充表格标题
     *
     * @param xssfSheet 被操作操作的表格
     * @param rowNum 表格填入标题的行数
     */
    public static <T, E> void fillTitles(XSSFSheet xssfSheet, Integer rowNum, ExcelRowFill excelTitlesFill) {
        // 在索引0的位置创建行（最顶端的行）
        XSSFRow xssfRow = xssfSheet.createRow(rowNum);
        // 填充数据
        excelTitlesFill.fillRow(xssfRow);
    }

    /**
     * 填充数据
     *
     * @param xssfSheet 被操作表格
     * @param rowNum 表格填入数据的起始的行数
     * @param datas excel数据信息
     * @param excelDatasFill excel信息填充接口
     */
    public static <T> void fillDatas(XSSFSheet xssfSheet, Integer rowNum, List<T> datas, ExcelInfosFill excelDatasFill) {
        for (int i = rowNum; i < datas.size() + rowNum; i++) {
            // 在索引i的位置创建行，并设置当前操作行为该行
            XSSFRow xssfRow = xssfSheet.createRow(i);
            // 填充数据
            excelDatasFill.fillInfo(xssfRow, datas.get(i - rowNum));
        }
    }

    /**
     * 完善配置 未填的配置填入初始化配置
     *
     * @param excelConfigMap 自定义的配置信息
     * @return 完善后的配置信息
     */
    public static Map<ExcelConfigEnum, Object> refineExcelConfigMap(Map<ExcelConfigEnum, Object> excelConfigMap) {
        Map<ExcelConfigEnum, Object> refinedExcelConfigMap = getDefaultxcelConfigMap();
        refinedExcelConfigMap.forEach((key, value) -> {
            if (!excelConfigMap.containsKey(key)) {
                excelConfigMap.put(key, value);
            }
        });
        return excelConfigMap;
    }

    /**
     * 获取默认配置
     *
     * @return 默认初始化配置
     */
    public static Map<ExcelConfigEnum, Object> getDefaultxcelConfigMap() {
        Map<ExcelConfigEnum, Object> defaultExcelConfigMap = new HashMap<>();
        ExcelConfigEnum[] excelConfigEnums = ExcelConfigEnum.values();
        for (ExcelConfigEnum excelConfigEnum : excelConfigEnums) {
            defaultExcelConfigMap.put(excelConfigEnum, excelConfigEnum.getDefaultValue());
        }
        return defaultExcelConfigMap;
    }

    /**
     * 配置要验证的内容 验证excelConfigMap所有value值
     */
    private static void doVerifyParams(Map<ExcelConfigEnum, Object> excelConfigMap) {
        //验证文件名filename
        doVerifyFilename(excelConfigMap.get(ExcelConfigEnum.FILE_NAME));
        //验证contentType
        doVerifyContentType(excelConfigMap.get(ExcelConfigEnum.CONTENT_TYPE));
        //验证工作薄表名sheetName
        doVerifySheetName(excelConfigMap.get(ExcelConfigEnum.SHEET_NAME));
    }

    /**
     * 验证工作薄名sheerName
     */
    private static void doVerifySheetName(Object sheetName) {
        doVerifyObjInstanceofString(sheetName, "内容类型sheetName不为String类型");
    }

    /**
     * 验证内容类型contentType
     *
     * @param contentType 内容类型对象
     */
    private static void doVerifyContentType(Object contentType) {
        doVerifyObjInstanceofString(contentType, "内容类型contentType不为String类型");
    }

    /**
     * 验证文件名
     *
     * @param filename 文件名类型
     */
    private static void doVerifyFilename(Object filename) {
        doVerifyObjInstanceofString(filename, "文件名filename不为String类型");
    }

    /**
     * 不带错误信息的验证对象是否为String类型
     *
     * @param obj 验证对象
     */
    private static void doVerifyObjInstanceofString(Object obj) {
        doVerifyObjInstanceofString(obj, null);
    }

    /**
     * 验证对象类型是否为String
     *
     * @param obj 验证对象
     * @param message 抛出异常信息
     */
    private static void doVerifyObjInstanceofString(Object obj, String message) {
        Optional optional = Optional.of(obj);
        if (!(optional.get() instanceof String)) {
            if (message == null) {
                throw new IllegalArgumentException("[Error] " + obj + "不为String 类型");
            } else {
                throw new IllegalArgumentException("[Error] " + message);
            }
        }
    }

    /**
     * 批量设置单元格格式
     */
    public static void setCellsStyle(XSSFRow xssfRow, CellStyle cellStyle, int[] cellIndexs) {
        for (int index : cellIndexs) {
            if (xssfRow.getCell(index) == null) {
                xssfRow.createCell(index);
            }
            xssfRow.getCell(index).setCellStyle(cellStyle);
        }
    }

    /**
     * 配置数据处理对象
     */
    public static DealObject configDealChain() {
        DealObject integerDeal = new IntegerDeal();
        DealObject doubleDeal = new DoubleDeal();
        DealObject booleanDeal = new BooleanDeal();
        DealObject stringDeal = new StringDeal();
        DealObject dateDeal = new DateDeal();
        DealObject long2DateDeal = new Long2DateDeal();
        DealObject selectManyDeal = new SelectManyDeal();

        integerDeal.setDealObject(doubleDeal);
        doubleDeal.setDealObject(booleanDeal);
        booleanDeal.setDealObject(stringDeal);
        stringDeal.setDealObject(dateDeal);
        dateDeal.setDealObject(long2DateDeal);
        long2DateDeal.setDealObject(selectManyDeal);

        DealObject defaultDeal = new DefaultDeal();
        selectManyDeal.setDealObject(defaultDeal);

        return integerDeal;
    }

    /**
     * 职责链 创建cell
     */
    public static void createCell(XSSFRow xssfRow, int index, Object obj) {
        dealObject.deal(xssfRow, index, obj);
    }

    public static Object fromLong2Date(Long obj) {
        if (obj != null) {
            return new Date(obj);
        }
        return null;
    }

    /**
     * @Author Joey
     * @Time 2018/8/27.
     *
     * 接口描述：填充数据接口
     */
    @FunctionalInterface
    public interface ExcelInfosFill<T> {
        /**
         * 用于填充数据方法 excelDatasFill example：
         *
         * value->{ //在索引的位置创建单元格, 在单元格中输入内容 row.createCell(i).setCellValue(t.get_()); // 定义单元格为字符串类型 row.getCell(i).setCellType(HSSFCell.CELL_TYPE_STRING); }
         *
         * @param xssfRow 填充内容的行
         * @param t 用于填充内容的对象
         */
        void fillInfo(XSSFRow xssfRow, T t);
    }

    /**
     * @Author Joey
     * @Time 2018/8/27.
     *
     * 接口描述：填充行内容接口
     */
    @FunctionalInterface
    public interface ExcelRowFill {
        /**
         * 用于填充数据方法 excelDatasFill example：
         *
         * value->{ //在索引的位置创建单元格, 在单元格中输入内容 row.createCell(i).setCellValue("content"); // 定义单元格为字符串类型 row.getCell(i).setCellType(HSSFCell.CELL_TYPE_STRING); }
         *
         * @param xssfRow 填充内容的行
         */
        void fillRow(XSSFRow xssfRow);
    }

    @Setter
    static abstract class DealObject {
        DealObject dealObject;

        abstract void deal(XSSFRow xssfRow, int index, Object obj);
    }

    static class IntegerDeal extends DealObject {
        @Override
        public void deal(XSSFRow xssfRow, int index, Object obj) {
            if (obj != null) {
                if (obj.getClass().equals(Integer.class)) {
                    xssfRow.createCell(index).setCellValue((Integer) obj);
                } else {
                    dealObject.deal(xssfRow, index, obj);
                }
            }
            //obj == null
            else {
                xssfRow.createCell(index);
            }
        }
    }

    static class DoubleDeal extends DealObject {
        @Override
        public void deal(XSSFRow xssfRow, int index, Object obj) {
            if (obj != null) {
                if (obj.getClass().equals(Double.class)) {
                    xssfRow.createCell(index).setCellValue((Double) obj);
                } else {
                    dealObject.deal(xssfRow, index, obj);
                }
            }
            //obj == null
            else {
                xssfRow.createCell(index);
            }
        }
    }

    static class BooleanDeal extends DealObject {
        @Override
        public void deal(XSSFRow xssfRow, int index, Object obj) {
            if (obj != null) {
                if (obj.getClass().equals(Boolean.class)) {
                    xssfRow.createCell(index).setCellValue((Boolean) obj);
                } else {
                    dealObject.deal(xssfRow, index, obj);
                }
            }
            //obj == null
            else {
                xssfRow.createCell(index);
            }
        }
    }

    static class StringDeal extends DealObject {
        @Override
        public void deal(XSSFRow xssfRow, int index, Object obj) {
            if (obj != null) {
                if (obj.getClass().equals(String.class)) {
                    xssfRow.createCell(index).setCellValue((String) obj);
                } else {
                    dealObject.deal(xssfRow, index, obj);
                }
            }
            //obj == null
            else {
                xssfRow.createCell(index);
            }
        }
    }

    static class DateDeal extends DealObject {
        @Override
        public void deal(XSSFRow xssfRow, int index, Object obj) {
            if (obj != null) {
                if (obj.getClass().equals(Date.class)) {
                    XSSFWorkbook workbook = xssfRow.getSheet().getWorkbook();
                    CreationHelper createHelper = workbook.getCreationHelper();
                    //时间单元格样式
                    XSSFCellStyle dateCellStyle = workbook.createCellStyle();
                    //时间样式
                    dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));
                    dateCellStyle.setAlignment(HorizontalAlignment.LEFT);
                    xssfRow.createCell(index).setCellValue((Date) obj);
                    xssfRow.getCell(index).setCellStyle(dateCellStyle);
                } else {
                    dealObject.deal(xssfRow, index, obj);
                }
            }
            //obj == null
            else {
                xssfRow.createCell(index);
            }
        }
    }

    static class SelectManyDeal extends DealObject {
        @Override
        void deal(XSSFRow xssfRow, int index, Object obj) {
            if (obj != null) {
                if (obj.getClass().equals(ArrayList.class)) {
                    StringBuilder strb = new StringBuilder();
                    ((ArrayList<String>) obj).forEach(value -> {
                        strb.append(value + " , ");
                    });
                    xssfRow.createCell(index).setCellValue(strb.toString());
                } else {
                    dealObject.deal(xssfRow, index, obj);
                }
            }
            //obj == null
            else {
                xssfRow.createCell(index);
            }
        }
    }

    static class Long2DateDeal extends DealObject {
        @Override
        public void deal(XSSFRow xssfRow, int index, Object obj) {
            if (obj != null) {
                if (obj.getClass().equals(Long.class)) {
                    XSSFWorkbook workbook = xssfRow.getSheet().getWorkbook();
                    CreationHelper createHelper = workbook.getCreationHelper();
                    //时间单元格样式
                    XSSFCellStyle dateCellStyle = workbook.createCellStyle();
                    //时间样式
                    dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));
                    dateCellStyle.setAlignment(HorizontalAlignment.LEFT);
                    xssfRow.createCell(index).setCellValue(new Date((Long) obj));
                    xssfRow.getCell(index).setCellStyle(dateCellStyle);
                } else {
                    dealObject.deal(xssfRow, index, obj);
                }
            }
            //obj == null
            else {
                xssfRow.createCell(index);
            }
        }
    }

    static class DefaultDeal extends DealObject {
        @Override
        void deal(XSSFRow xssfRow, int index, Object obj) {
            xssfRow.createCell(index).setCellValue("数据导出有误");
        }
    }
}
