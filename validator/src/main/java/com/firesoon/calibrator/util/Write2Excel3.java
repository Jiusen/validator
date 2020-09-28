package com.firesoon.calibrator.util;

import com.firesoon.calibrator.excel.CSVFenZuRead;
import com.firesoon.calibrator.excel.ReadExcel;
import com.firesoon.calibrator.pojo.Rate;
import com.firesoon.calibrator.pojo.Result;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Author: Dreamer-1
 * Date: 2019-03-01
 * Time: 11:09
 * Description: 生成Excel并写入数据
 */
public class Write2Excel3 {

    private static List<String> CELL_HEADS; //列头
    public static int rowNum = 1;
    public static Rate rate;

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
    
	public static List<String> CELL_HEADS2; //列头
    static{
        // 类装载时就载入指定好的列头信息，如有需要，可以考虑做成动态生成的列头
        CELL_HEADS2 = new ArrayList<String>();
        CELL_HEADS2.add("医院");
        CELL_HEADS2.add("错误类型");
        CELL_HEADS2.add("该类型病例数");
        CELL_HEADS2.add("总病例数");
        CELL_HEADS2.add("占比");
    }

    /**
     * 生成Excel并写入数据信息
     * @param dataList 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static Workbook exportData(Map<String, List<Result>> placeMap1){
        // 生成xlsx的Excel
        Workbook workbook = new SXSSFWorkbook();

        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
        //Workbook workbook = new HSSFWorkbook();

        // 生成Sheet表，写入第一行的列头
        Sheet sheet = buildDataSheet(workbook);
        Sheet sheet2 = buildDataSheet2(workbook);
        //构建每行的数据内容
        
        for(String s:placeMap1.keySet())
        {
            List dataList = placeMap1.get(s);
            
            for (Iterator<Result> it = dataList.iterator(); it.hasNext(); ) {
            	Result data = it.next();
                if (data == null) {
                    continue;
                }
                //输出行数据
                Row row = sheet.createRow(rowNum++);
                convertDataToRow(data, row);
            }
        }
        
        int rown = 0;
        for(String s:ReadExcel.rateMap.keySet())
        {
            //主诊断缺失
        	rate = ReadExcel.rateMap.get(s);
        	double sum = CSVFenZuRead.placeNumMap.get(s);
            int n = Write2ExcelRate.writeRate(workbook, sheet2, rown, rate, sum, s);  //修改by/js
            rown = n;
        }

        
        return workbook;
    }

    public static void convertSummaryDataToRow(Row row, String value) {
        int cellNum = 0;
        Cell cell;
        // pid
        cell = row.createCell(cellNum++);

        //插值
        cell.setCellValue(value);
    }

    /**
     * 生成sheet表，并写入第一行数据（列头）
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    public static Sheet buildDataSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("错误信息");
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
     * 生成sheet2表，并写入第一行数据（列头）
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    public static Sheet buildDataSheet2(Workbook workbook) {
        Sheet sheet = workbook.createSheet("错误信息汇总");
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
        return sheet;
    }
    

    /**
     * 设置第一行列头的样式
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    public static CellStyle buildHeadCellStyle(Workbook workbook) {
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
    private static void convertDataToRow(Result data, Row row){
        int cellNum = 0;
        Cell cell;
        // pid
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getPID()? "" : data.getPID());
        // billdrgcode
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getBILL_DRGCODE()? "" : data.getBILL_DRGCODE());
        // sysdrgcode
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getSYS_DRGCODE()? "" : data.getSYS_DRGCODE());
        // requestParam
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getREQUEST_JSON());
        // source
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getSource());
        // errorReason
        cell = row.createCell(cellNum++);
//        cell.setCellValue(data.getReason().toString());
        if(data.getReason() == null){
            cell.setCellValue("");
        }else {
            cell.setCellValue(data.getReason().toString());
        }
    }
    

}