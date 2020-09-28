package com.firesoon.writer;

import com.alibaba.fastjson.JSON;
import com.firesoon.pojo.GroupingParam;
import com.firesoon.reader.GroupParamExcelReader;
import com.firesoon.util.MapSortUtil;
import com.firesoon.validate.TValidate;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import sun.dc.pr.PRError;

import java.util.*;

/**
 * Author: Dreamer-1
 * Date: 2019-03-01
 * Time: 11:09
 * Description: 生成Excel并写入数据
 */
public class ExcelWriter {
    // 错误信息开始行
    private static int rowNum = 1;
    //  错误信息汇总开始行
    private static int errTotalRowNum = 0;
    private static List<String> CELL_HEADS; //列头
    private static Map<String,String> errString = new HashMap<>();
    static {
//        errString.put("noneZd","111+【一、缺失；1.病案缺失；1.1主诊断缺失】");
//        errString.put("noneSs","112+【一、缺失；1.病案缺失；1.2主手术缺失】");
//        errString.put("noneCzd","113+【一、缺失；1.病案缺失；1.3次诊断缺失】");
//        errString.put("noneSex","【一、缺失；3.基本信息缺失；1.1性别】");
//        errString.put("noneAge","【一、缺失；3.基本信息缺失；1.2年龄】");
//        errString.put("noneXzets","【一、缺失；3.基本信息缺失；1.3新生儿体重】");
//        errString.put("errZd","【二、取错；1.病案取错；1.1主诊断取错】");
//        errString.put("errSs","【二、取错；1.病案取错；1.2主手术取错】");
//        errString.put("owenCode","【三、编码；2.院内自行维护的编码；2.1医院自行维护的编码】");
//        errString.put("errWtoN","【五、分组错误；1.ADRG分错；1.1应该是外科操作结果入内科】");
//        errString.put("errNtoW","【五、分组错误；1.ADRG分错；1.2应该是内科操作结果入外科】");
//        errString.put("errNandunMdc","【五、分组错误；1.ADRG分错；1.3都是内科，不同MDC】");
//        errString.put("errWandunMdc","【五、分组错误；1.ADRG分错；1.4都是外科操作，不同MDC】");
//        errString.put("errHbz","【五、分组错误；2.合并症分错；1.1合并症分错】");
//        errString.put("unSpecial","【六、未分组；2.病组特殊条件没满足；1.1病组特殊条件没满足】");
        errString.put("noneZd","111+【一、缺失；1.病案缺失；1.1主诊断缺失】");
        errString.put("noneSs","112+【一、缺失；1.病案缺失；1.2主手术缺失】");
        errString.put("noneCzd","113+【一、缺失；1.病案缺失；1.3次诊断缺失】");
        errString.put("noneSex","131+【一、缺失；3.基本信息缺失；1.1性别】");
        errString.put("noneAge","132+【一、缺失；3.基本信息缺失；1.2年龄】");
        errString.put("noneXzets","133+【一、缺失；3.基本信息缺失；1.3新生儿体重】");
        errString.put("errZd","211+【二、取错；1.病案取错；1.1主诊断取错】");
        errString.put("errSs","212+【二、取错；1.病案取错；1.2主手术取错】");
        errString.put("owenCode","321+【三、编码；2.院内自行维护的编码；2.1医院自行维护的编码】");
        errString.put("errWtoN","511+【五、分组错误；1.ADRG分错；1.1应该是外科操作结果入内科】");
        errString.put("errNtoW","512+【五、分组错误；1.ADRG分错；1.2应该是内科操作结果入外科】");
        errString.put("errNandunMdc","513+【五、分组错误；1.ADRG分错；1.3都是内科，不同MDC】");
        errString.put("errWandunMdc","514+【五、分组错误；1.ADRG分错；1.4都是外科操作，不同MDC】");
        //errString.put("errHbz","521+【五、分组错误；2.合并症分错；1.1合并症分错】");
        errString.put("unSpecial","621+【六、未分组；2.病组特殊条件没满足；1.1病组特殊条件没满足】");
        errString.put("err1","521+【五、分组错误；2.合并症分错；2.1应该是重要结果分到一般】");
        errString.put("err2","522+【五、分组错误；2.合并症分错；2.2应该是重要结果分到不伴】");
        errString.put("err3","523+【五、分组错误；2.合并症分错；2.3应该是一般结果是重要】");
        errString.put("err4","524+【五、分组错误；2.合并症分错；2.4应该是不伴结果是重要】");
        errString.put("err5","525+【五、分组错误；2.合并症分错；2.5应该是一般结果是不伴】");
        errString.put("err6","526+【五、分组错误；2.合并症分错；2.6应该是不伴结果是一般】");
        errString.put("err7","527+【五、分组错误；2.合并症分错；2.7其他】");
    }
    static{
        // 类装载时就载入指定好的列头信息，如有需要，可以考虑做成动态生成的列头
        CELL_HEADS = new ArrayList<String>();
        CELL_HEADS.add("PID");
        CELL_HEADS.add("BILL_DRGCODE");
        CELL_HEADS.add("SYS_DRGCODE");
        CELL_HEADS.add("REQUEST_JSON");
        CELL_HEADS.add("source");
        CELL_HEADS.add("ERROR_REASON");
    }

    /**
     * 生成Excel并写入数据信息
     * @param dataList 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static Workbook exportData(List<GroupingParam> dataList){
        // 生成xlsx的Excel
        Workbook workbook = new SXSSFWorkbook();

        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
        //Workbook workbook = new HSSFWorkbook();

        // 生成Sheet表，写入第一行的列头
        Sheet sheet1 = buildDataSheet(workbook,"错误信息");
        //
        Sheet sheet2 = workbook.createSheet("错误信息汇总");
        //构建每行的数据内容

        for (Iterator<GroupingParam> it = dataList.iterator(); it.hasNext(); ) {
            GroupingParam data = it.next();
            if (data == null) {
                continue;
            }
            //输出行数据
            Row row = sheet1.createRow(rowNum++);
            convertDataToRow(data, row);
        }
//        // 类型错误表头
//        Row row = sheet.createRow(rowNum++);
//        convertTypeSumHeadDataToRow(row);
//
        // 类型错误数据填充
        for (String sourceKey : TValidate.getSourceTotal().keySet()) {

            convertDataToRowBySource(workbook,sheet2, sourceKey, TValidate.getSourceMisTypeMap().get(sourceKey),TValidate.getSourceMisFeeMap().get(sourceKey),TValidate.getSourceErrFeeMap().get(sourceKey));

        }
        return workbook;
    }




    /**
     *
     * @param sheet sheet
     * @param source   医院
     * @param misType  错误类型
     * @param misFeeItem 缺失参数
     * @param errFeeItem    错误参数
     * @return
     */
    public static int convertDataToRowBySource(Workbook workbook,Sheet sheet,String source,Map<String,Integer> misType,Map<String,Integer> misFeeItem,Map<String,Integer> errFeeItem){
        Row row = sheet.createRow(rowNum++);
        //convertSourceHeadDataToRow(row,source,workbook);
        // 类型错误表头
        row = sheet.createRow(errTotalRowNum++);
        convertTypeSumHeadDataToRow(row,workbook);
        errString = MapSortUtil.sortByValueAsc(errString);
        //System.out.println("misType:"+misType);
        //row = sheet.createRow(errTotalRowNum++);

        // 类型错误数据填充
        for (String key : errString.keySet()) {
            row = sheet.createRow(errTotalRowNum++);
            convertDataSumToRow(source,key,misType,row);
        }
        // 缺失费用项表头
        row = sheet.createRow(errTotalRowNum++);
        convertMisFeeItemSumHeadDataToRow(row,workbook,source);
        // 缺失费用项数据填充
        for (String key : misFeeItem.keySet()) {
            row = sheet.createRow(errTotalRowNum++);
            convertDataSumToRow(source,key,misFeeItem,row);
        }
        // 费用项错误表头
        row = sheet.createRow(errTotalRowNum++);
        convertErrFeeItemSumHeadDataToRow(row,workbook,source);
        // 错误费用项数据填充
        for (String key : errFeeItem.keySet()) {
            row = sheet.createRow(errTotalRowNum++);
            convertDataSumToRow(source,key,errFeeItem,row);
        }
        return errTotalRowNum;
    }


    /**
     *
     * @param source 地区
     * @param key   参数
     * @param map   存储 参数，出现次数
     * @param row   行
     */
    private static void convertDataSumToRow(String source,String key, Map<String,Integer> map, Row row) {
        int cellNum = 0;
        Cell cell;
        // 设置医院
        cell = row.createCell(cellNum++);
        cell.setCellValue(source);
        // 错误类型
        cell = row.createCell(cellNum++);
        cell.setCellValue(errString.get(key) == null? "---"+key:errString.get(key).split("\\+")[1]);
        // 总数
        cell = row.createCell(cellNum++);
        cell.setCellValue(map.get(key));
        // 该类型总病例数
        cell = row.createCell(cellNum++);
        cell.setCellValue(TValidate.getSourceTotal().get(source));
        // 概率
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)map.get(key)/(double)TValidate.getSourceTotal().get(source));
        // requestParam
        cell = row.createCell(cellNum++);
        // source
        cell = row.createCell(cellNum++);
        // errorReason
        cell = row.createCell(cellNum++);
    }

    private static void convertTypeSumHeadDataToRow(Row row,Workbook workbook) {
        int cellNum = 0;
        Cell cell;
        cell = row.createCell(cellNum++);
        cell.setCellValue("医院");
        cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("错误类型");
        cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("该类型病例数");
        cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("总病例数");
        cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("占比");
        cell.setCellStyle(buildHeadCellStyle(workbook));
        //
        //cell = row.createCell(cellNum++);
        //
        //cell = row.createCell(cellNum++);
        //

    }

    private static void convertSourceHeadDataToRow(Row row,String source,Workbook workbook) {
        int cellNum = 0;
        Cell cell;
        // 设置医院
        cell = row.createCell(cellNum++);
        cell.setCellStyle(buildHeadCellStyle(workbook));
        cell.setCellValue("医院:"+source);
        cell = row.createCell(cellNum++);

        cell = row.createCell(cellNum++);

        cell = row.createCell(cellNum++);
        //
        cell = row.createCell(cellNum++);
        //
        cell = row.createCell(cellNum++);
        //

    }


    private static void convertMisFeeItemSumHeadDataToRow(Row row,Workbook workbook,String source) {
        int cellNum = 0;
        Cell cell;
        // 医院
        cell = row.createCell(cellNum++);
        cell.setCellValue(source);
        // 错误原因
        cell = row.createCell(cellNum++);
        cell.setCellValue("【一、缺失；2.费用项缺失；2.1费用项参数个数不一致】");
        //cell.setCellStyle(buildHeadCellStyle(workbook));
        // billdrgcode
        cell = row.createCell(cellNum++);
        cell.setCellValue("该类型病例数");
        //cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("总病例数");
        //cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("占比");
        //cell.setCellStyle(buildHeadCellStyle(workbook));
        //cell = row.createCell(cellNum++);

        //cell = row.createCell(cellNum++);

    }

    private static void convertErrFeeItemSumHeadDataToRow(Row row,Workbook workbook,String source) {
        int cellNum = 0;
        Cell cell;
        // 医院
        cell = row.createCell(cellNum++);
        cell.setCellValue(source);
        // pid
        cell = row.createCell(cellNum++);
        cell.setCellValue("【二、取错；2.费用项取错；2.1 费用项遗漏或多余】");
        // cell.setCellStyle(buildHeadCellStyle(workbook));
        // billdrgcode
        cell = row.createCell(cellNum++);
        cell.setCellValue("该类型病例数");
        // cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("总病例数");
        //cell.setCellStyle(buildHeadCellStyle(workbook));
        cell = row.createCell(cellNum++);
        cell.setCellValue("占比");
        //cell.setCellStyle(buildHeadCellStyle(workbook));
        // requestParam
        //cell = row.createCell(cellNum++);
        // source
        //cell = row.createCell(cellNum++);

    }
    /**
     * 生成sheet表，并写入第一行数据（列头）
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    private static Sheet buildDataSheet(Workbook workbook,String name) {
        Sheet sheet = workbook.createSheet(name);
        // 设置列头宽度
        for (int i=0; i<CELL_HEADS.size(); i++) {
            sheet.setColumnWidth(i, 4000);
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 400);
        // 构建头单元格样式
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        // 写入第一行各列的数据
        Row head = sheet.createRow(0);
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(CELL_HEADS.get(i));
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }

    /**
     * 设置第一行列头的样式
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 将数据转换成行
     * @param data 源数据
     * @param row 行对象
     * @return
     */
    private static void convertDataToRow(GroupingParam data, Row row){
        int cellNum = 0;
        Cell cell;
        // pid
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getPid()? "" : data.getPid());
        // billdrgcode
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getBillDra()? "" : data.getBillDra());
        // sysdrgcode
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getSysDrg()? "" : data.getSysDrg());
        // requestParam
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getItemStr());
        // source
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getSource());
        // errorReason
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getErrorReason().toString());
    }
}