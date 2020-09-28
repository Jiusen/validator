package com.firesoon.calibrator.util;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.firesoon.calibrator.pojo.Rate;

public class Write2ExcelRate
{
	private static List<String> CELL_HEADS; //列头
	
	public static int writeRate(Workbook workbook, Sheet sheet, int rowNum, Rate rate, double sum, String hospital)
	{
		int rm = rowNum;
		double SysNum = sum;
		
		
		int cellNum = 0;
		Cell cell;
		
		double zzdsum = rate.zzdsum / SysNum;//主诊断缺失
		double zsssum = rate.zsssum / SysNum;//1.2主手术缺失
		double czdsum = rate.czdsum / SysNum;//1.3次诊断缺失
		double fyxqssum = rate.fyxqssum / SysNum;//2.1费用项参数个数不一致
		double sexsum = rate.sexsum / SysNum;//1.1性别
		double agesum = rate.agesum / SysNum;//1.2年龄
		double weightSum = rate.weightSum / SysNum; //3.新生儿体重
		double zzdqcsum = rate.zzdqcsum / SysNum;//1.1主诊断取错
		double zssqcsum = rate.zssqcsum / SysNum;//1.2主手术取错
		double fyxqc = rate.fyxqc / SysNum; //2费用项取错
		double ynzxwh = rate.ynzxwh / SysNum;//2院内自行维护编码
		double ADRG1 = rate.ADRG1 / SysNum;//1.1应该是外科操作结果入内科
		double ADRG2 = rate.ADRG2 / SysNum;//1.2应该是内科结果入外科操作
		double ADRG3 = rate.ADRG3 / SysNum;//1.3都是内科，不同MDC
		double ADRG4 = rate.ADRG4 / SysNum;//1.4都是外科操作，不同MDC
		double hbzqc1 = rate.hbzfc1 / SysNum;//合并症分错
		double hbzqc2 = rate.hbzfc2 / SysNum;//合并症分错
		double hbzqc3 = rate.hbzfc3 / SysNum;//合并症分错
		double hbzqc4 = rate.hbzfc4 / SysNum;//合并症分错
		double hbzqc5 = rate.hbzfc5 / SysNum;//合并症分错
		double hbzqc6 = rate.hbzfc6 / SysNum;//合并症分错
		double hbzqc7 = rate.hbzfc7 / SysNum;//合并症分错
		double special = rate.special / SysNum;//2.病组特殊条件没满足
        
		//总结分析

        
        CellStyle cellStyle = Write2Excel3.buildHeadCellStyle(workbook);

//		String value100 = "医院: " + hospital;
//        Row row100 = sheet.createRow(rm++);
//        cellNum = 0;
//        cell = row100.createCell(cellNum++);
//        cell.setCellValue(value100);
//        cell.setCellStyle(cellStyle);
        
        // 写入第一行各列的数据
        Row head = sheet.createRow(rm++);
        for (int i = 0; i < Write2Excel3.CELL_HEADS2.size(); i++) {
            cell = head.createCell(i);
            cell.setCellValue(Write2Excel3.CELL_HEADS2.get(i));
            cell.setCellStyle(cellStyle);
        }
		//主诊断缺失率
		String valueG111 = "【一、缺失；1.病案缺失；1.1主诊断缺失】";
        String valueG112 = rate.zzdsum + "";
        String valueG113 = SysNum + "";
        String valueG114 = convert(zzdsum) + "";
        String hos = hospital;
        
		Row rowG1 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG1.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG1.createCell(cellNum++);
        cell.setCellValue(valueG111);
        cell = rowG1.createCell(cellNum++);
        cell.setCellValue(valueG112);
        cell = rowG1.createCell(cellNum++);
        cell.setCellValue(valueG113);
        cell = rowG1.createCell(cellNum++);
        cell.setCellValue(valueG114);

        //1.2主手术缺失
		String valueG211 = "【一、缺失；1.病案缺失；1.2主手术缺失】";
        String valueG212 = rate.zsssum + "";
        String valueG213 = SysNum + "";
        String valueG214 = convert(zsssum) + "";
        
		Row rowG2 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG2.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG2.createCell(cellNum++);
        cell.setCellValue(valueG211);
        cell = rowG2.createCell(cellNum++);
        cell.setCellValue(valueG212);
        cell = rowG2.createCell(cellNum++);
        cell.setCellValue(valueG213);
        cell = rowG2.createCell(cellNum++);
        cell.setCellValue(valueG214);
        
        //1.3次诊断缺失
		String valueG411 = "【一、缺失；1.病案缺失；1.3次诊断缺失】";
        String valueG412 = rate.czdsum + "";
        String valueG413 = SysNum + "";
        String valueG414 = convert(czdsum) + "";
        
		Row rowG4 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG4.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG4.createCell(cellNum++);
        cell.setCellValue(valueG411);
        cell = rowG4.createCell(cellNum++);
        cell.setCellValue(valueG412);
        cell = rowG4.createCell(cellNum++);
        cell.setCellValue(valueG413);
        cell = rowG4.createCell(cellNum++);
        cell.setCellValue(valueG414);
        
        //2.1费用项缺失
		String valueG511 = "【一、缺失；1.病案缺失；2.1费用项参数个数不一致】";
        String valueG512 = rate.fyxqssum + "";
        String valueG513 = SysNum + "";
        String valueG514 = convert(fyxqssum) + "";
        
		Row rowG5 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG5.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG5.createCell(cellNum++);
        cell.setCellValue(valueG511);
        cell = rowG5.createCell(cellNum++);
        cell.setCellValue(valueG512);
        cell = rowG5.createCell(cellNum++);
        cell.setCellValue(valueG513);
        cell = rowG5.createCell(cellNum++);
        cell.setCellValue(valueG514);
        
		String valueG5111 = "--dialysis缺失数量";
        String valueG5112 = rate.cost[0] + "";
        String valueG5113 = SysNum + "";
        String valueG5114 = convert(rate.cost[0] / SysNum) + "";
        
		Row rowG51 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG51.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG51.createCell(cellNum++);
        cell.setCellValue(valueG5111);
        cell = rowG51.createCell(cellNum++);
        cell.setCellValue(valueG5112);
        cell = rowG51.createCell(cellNum++);
        cell.setCellValue(valueG5113);
        cell = rowG51.createCell(cellNum++);
        cell.setCellValue(valueG5114);
        
		String valueG5211 = "--ABY缺失数量";
        String valueG5212 = rate.cost[1] + "";
        String valueG5213 = SysNum + "";
        String valueG5214 = convert(rate.cost[1] / SysNum) + "";
        
		Row rowG52 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG52.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG52.createCell(cellNum++);
        cell.setCellValue(valueG5211);
        cell = rowG52.createCell(cellNum++);
        cell.setCellValue(valueG5212);
        cell = rowG52.createCell(cellNum++);
        cell.setCellValue(valueG5213);
        cell = rowG52.createCell(cellNum++);
        cell.setCellValue(valueG5214);
        
        //3
		String valueG5311 = "--ALSSTime缺失数量";
        String valueG5312 = rate.cost[2] + "";
        String valueG5313 = SysNum + "";
        String valueG5314 = convert(rate.cost[2] / SysNum) + "";
        
		Row rowG53 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG53.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG53.createCell(cellNum++);
        cell.setCellValue(valueG5311);
        cell = rowG53.createCell(cellNum++);
        cell.setCellValue(valueG5312);
        cell = rowG53.createCell(cellNum++);
        cell.setCellValue(valueG5313);
        cell = rowG53.createCell(cellNum++);
        cell.setCellValue(valueG5314);
        
        //4  
		String valueG5411 = "--CBPTime缺失数量";
        String valueG5412 = rate.cost[3] + "";
        String valueG5413 = SysNum + "";
        String valueG5414 = convert(rate.cost[3] / SysNum) + "";
        
		Row rowG54 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG54.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG54.createCell(cellNum++);
        cell.setCellValue(valueG5411);
        cell = rowG54.createCell(cellNum++);
        cell.setCellValue(valueG5412);
        cell = rowG54.createCell(cellNum++);
        cell.setCellValue(valueG5413);
        cell = rowG54.createCell(cellNum++);
        cell.setCellValue(valueG5414);
        
        //5
		String valueG5511 = "--ECMOTime缺失数量";
        String valueG5512 = rate.cost[4] + "";
        String valueG5513 = SysNum + "";
        String valueG5514 = convert(rate.cost[4] / SysNum) + "";
        
		Row rowG55 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG55.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG55.createCell(cellNum++);
        cell.setCellValue(valueG5511);
        cell = rowG55.createCell(cellNum++);
        cell.setCellValue(valueG5512);
        cell = rowG55.createCell(cellNum++);
        cell.setCellValue(valueG5513);
        cell = rowG55.createCell(cellNum++);
        cell.setCellValue(valueG5514);
        
        //6
		String valueG5611 = "--IABPTime缺失数量";
        String valueG5612 = rate.cost[5] + "";
        String valueG5613 = SysNum + "";
        String valueG5614 = convert(rate.cost[5] / SysNum) + "";
        
		Row rowG56 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG56.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG56.createCell(cellNum++);
        cell.setCellValue(valueG5611);
        cell = rowG56.createCell(cellNum++);
        cell.setCellValue(valueG5612);
        cell = rowG56.createCell(cellNum++);
        cell.setCellValue(valueG5613);
        cell = rowG56.createCell(cellNum++);
        cell.setCellValue(valueG5614);
     
        //7
		String valueG5711 = "--icu缺失数量";
        String valueG5712 = rate.cost[6] + "";
        String valueG5713 = SysNum + "";
        String valueG5714 = convert(rate.cost[6] / SysNum) + "";
        
		Row rowG57 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG57.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG57.createCell(cellNum++);
        cell.setCellValue(valueG5711);
        cell = rowG57.createCell(cellNum++);
        cell.setCellValue(valueG5712);
        cell = rowG57.createCell(cellNum++);
        cell.setCellValue(valueG5713);
        cell = rowG57.createCell(cellNum++);
        cell.setCellValue(valueG5714);
        
//		String value58 = "---vetime缺失数量: " + PlaceCost.costs[7] + ", vetime缺失率:" + convert(PlaceCost.costs[7] / SysNum);
//        Row row58 = sheet.createRow(rm++);
//        Write2Excel3.convertSummaryDataToRow(row58, value58);
        
        //8
		String valueG5811 = "--vetime缺失数量";
        String valueG5812 = rate.cost[7] + "";
        String valueG5813 = SysNum + "";
        String valueG5814 = convert(rate.cost[7] / SysNum) + "";
        
		Row rowG58 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG58.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG58.createCell(cellNum++);
        cell.setCellValue(valueG5811);
        cell = rowG58.createCell(cellNum++);
        cell.setCellValue(valueG5812);
        cell = rowG58.createCell(cellNum++);
        cell.setCellValue(valueG5813);
        cell = rowG58.createCell(cellNum++);
        cell.setCellValue(valueG5814);

        //9
		String valueG5911 = "--lteplase drug缺失数量";
        String valueG5912 = rate.cost[8] + "";
        String valueG5913 = SysNum + "";
        String valueG5914 = convert(rate.cost[8] / SysNum) + "";
        
		Row rowG59 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG59.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG59.createCell(cellNum++);
        cell.setCellValue(valueG5911);
        cell = rowG59.createCell(cellNum++);
        cell.setCellValue(valueG5912);
        cell = rowG59.createCell(cellNum++);
        cell.setCellValue(valueG5913);
        cell = rowG59.createCell(cellNum++);
        cell.setCellValue(valueG5914);
        
        //1\3.1性别缺失      
		String valueG611 = "【一、缺失；3.基本信息缺失；1.1性别】";
        String valueG612 = rate.sexsum + "";
        String valueG613 = SysNum + "";
        String valueG614 = convert(sexsum) + "";
        
		Row rowG6 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG6.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG6.createCell(cellNum++);
        cell.setCellValue(valueG611);
        cell = rowG6.createCell(cellNum++);
        cell.setCellValue(valueG612);
        cell = rowG6.createCell(cellNum++);
        cell.setCellValue(valueG613);
        cell = rowG6.createCell(cellNum++);
        cell.setCellValue(valueG614);
        //1\3.2年龄缺失
		String valueG711 = "【一、缺失；3.基本信息缺失；1.2年龄】";
        String valueG712 = rate.agesum + "";
        String valueG713 = SysNum + "";
        String valueG714 = convert(agesum) + "";
        
		Row rowG7 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG7.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG7.createCell(cellNum++);
        cell.setCellValue(valueG711);
        cell = rowG7.createCell(cellNum++);
        cell.setCellValue(valueG712);
        cell = rowG7.createCell(cellNum++);
        cell.setCellValue(valueG713);
        cell = rowG7.createCell(cellNum++);
        cell.setCellValue(valueG714);
        //1\3.3新生儿体重缺失
		String valueG811 = "【一、缺失；3.基本信息缺失；1.3新生儿体重】";
        String valueG812 = rate.weightSum + "";
        String valueG813 = SysNum + "";
        String valueG814 = convert(weightSum) + "";
        
		Row rowG8 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG8.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG8.createCell(cellNum++);
        cell.setCellValue(valueG811);
        cell = rowG8.createCell(cellNum++);
        cell.setCellValue(valueG812);
        cell = rowG8.createCell(cellNum++);
        cell.setCellValue(valueG813);
        cell = rowG8.createCell(cellNum++);
        cell.setCellValue(valueG814);
        
        //2.1主诊断缺失
//        String value9 = "【二、取错；1.病案取错；1.1主诊断取错】【 该类型病例数】: " + rate.zzdqcsum + "【总病例数】: " + SysNum + "【占比】:" + convert(zzdqcsum);
//        Row row9 = sheet.createRow(rm++);
//        Write2Excel3.convertSummaryDataToRow(row9, value9);
        
		String valueG911 = "【二、取错；1.病案取错；1.1主诊断取错】";
        String valueG912 = rate.zzdqcsum + "";
        String valueG913 = SysNum + "";
        String valueG914 = convert(zzdqcsum) + "";
        
		Row rowG9 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG9.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG9.createCell(cellNum++);
        cell.setCellValue(valueG911);
        cell = rowG9.createCell(cellNum++);
        cell.setCellValue(valueG912);
        cell = rowG9.createCell(cellNum++);
        cell.setCellValue(valueG913);
        cell = rowG9.createCell(cellNum++);
        cell.setCellValue(valueG914);
        
        //2.2主手术取错
//        String value10 = "【二、取错；1.病案取错；1.2主手术取错】【 该类型病例数】: " + rate.zssqcsum + "【总病例数】: " + SysNum + "【占比】:" + convert(zssqcsum);
//        Row row10 = sheet.createRow(rm++);
//        Write2Excel3.convertSummaryDataToRow(row10, value10);
		String valueG1011 = "【二、取错；1.病案取错；1.2主手术取错】";
        String valueG1012 = rate.zssqcsum + "";
        String valueG1013 = SysNum + "";
        String valueG1014 = convert(zssqcsum) + "";
        
		Row rowG10 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG10.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG10.createCell(cellNum++);
        cell.setCellValue(valueG1011);
        cell = rowG10.createCell(cellNum++);
        cell.setCellValue(valueG1012);
        cell = rowG10.createCell(cellNum++);
        cell.setCellValue(valueG1013);
        cell = rowG10.createCell(cellNum++);
        cell.setCellValue(valueG1014);
        //2\2费用项取错
//        String value11 = "【二、取错；2.费用项取错；2.1费用项中费用序号遗漏或多余】【 该类型病例数】: " + rate.fyxqc + "【总病例数】: " + SysNum + "【占比】:" + convert(fyxqc);
//        Row row11 = sheet.createRow(rm++);
//        Write2Excel3.convertSummaryDataToRow(row11, value11);
		String valueG1111 = "【二、取错；2.费用项取错；2.1费用项中费用序号遗漏或多余】";
        String valueG1112 = rate.fyxqc + "";
        String valueG1113 = SysNum + "";
        String valueG1114 = convert(fyxqc) + "";
        
		Row rowG11 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG11.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG11.createCell(cellNum++);
        cell.setCellValue(valueG1111);
        cell = rowG11.createCell(cellNum++);
        cell.setCellValue(valueG1112);
        cell = rowG11.createCell(cellNum++);
        cell.setCellValue(valueG1113);
        cell = rowG11.createCell(cellNum++);
        cell.setCellValue(valueG1114);
        //3.1医院自行维护的编码
//        String value12 = "【三、编码；2.院内自行维护的编码；2.1医院自行维护的编码】【 该类型病例数】: " + rate.ynzxwh + "【总病例数】: " + SysNum + "【占比】:" + convert(ynzxwh);
//        Row row12 = sheet.createRow(rm++);
//        Write2Excel3.convertSummaryDataToRow(row12, value12);
        String valueG1211 = "【三、编码；2.院内自行维护的编码；2.1医院自行维护的编码】";
        String valueG1212 = rate.ynzxwh + "";
        String valueG1213 = SysNum + "";
        String valueG1214 = convert(ynzxwh) + "";
        
		Row rowG12 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG12.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG12.createCell(cellNum++);
        cell.setCellValue(valueG1211);
        cell = rowG12.createCell(cellNum++);
        cell.setCellValue(valueG1212);
        cell = rowG12.createCell(cellNum++);
        cell.setCellValue(valueG1213);
        cell = rowG12.createCell(cellNum++);
        cell.setCellValue(valueG1214);
        
        //4.1应该是外科操作结果入内科
//        String value13 = "【五、分组错误；1.ADRG分错；1.1应该是外科操作结果入内科】【 该类型病例数】: " + rate.ADRG1 + "【总病例数】: " + SysNum + "【占比】:" + convert(ADRG1);
//		Row row13 = sheet.createRow(rm++);
//        Write2Excel3.convertSummaryDataToRow(row13, value13);
        
        String valueG1311 = "【五、分组错误；1.ADRG分错；1.1应该是外科操作结果入内科】";
        String valueG1312 = rate.ADRG1 + "";
        String valueG1313 = SysNum + "";
        String valueG1314 = convert(ADRG1) + "";
        
		Row rowG13 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG13.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG13.createCell(cellNum++);
        cell.setCellValue(valueG1311);
        cell = rowG13.createCell(cellNum++);
        cell.setCellValue(valueG1312);
        cell = rowG13.createCell(cellNum++);
        cell.setCellValue(valueG1313);
        cell = rowG13.createCell(cellNum++);
        cell.setCellValue(valueG1314);
        //3.1应该是内科结果入外科操作
//        String value14 = "【五、分组错误；1.ADRG分错；1.2应该是内科结果入外科操作】: 【 该类型病例数】: " + rate.ADRG2 + "【总病例数】: " + SysNum + "【占比】:" + convert(ADRG2);
//		Row row14 = sheet.createRow(rm++);
//        Write2Excel3.convertSummaryDataToRow(row14, value14);
        
        String valueG1411 = "【五、分组错误；1.ADRG分错；1.2应该是内科结果入外科操作】";
        String valueG1412 = rate.ADRG2 + "";
        String valueG1413 = SysNum + "";
        String valueG1414 = convert(ADRG2) + "";
        
		Row rowG14 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG14.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG14.createCell(cellNum++);
        cell.setCellValue(valueG1411);
        cell = rowG14.createCell(cellNum++);
        cell.setCellValue(valueG1412);
        cell = rowG14.createCell(cellNum++);
        cell.setCellValue(valueG1413);
        cell = rowG14.createCell(cellNum++);
        cell.setCellValue(valueG1414);
        //3.1都是内科，不同MDC
        String valueG1511 = "【五、分组错误；1.ADRG分错；1.3都是内科，不同MDC】";
        String valueG1512 = rate.ADRG3 + "";
        String valueG1513 = SysNum + "";
        String valueG1514 = convert(ADRG3) + "";
        
		Row rowG15 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG15.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG15.createCell(cellNum++);
        cell.setCellValue(valueG1511);
        cell = rowG15.createCell(cellNum++);
        cell.setCellValue(valueG1512);
        cell = rowG15.createCell(cellNum++);
        cell.setCellValue(valueG1513);
        cell = rowG15.createCell(cellNum++);
        cell.setCellValue(valueG1514);
        //3.1都是外科操作，不同MDC
        
        String valueG1611 = "【五、分组错误；1.ADRG分错；1.4都是外科操作，不同MDC】";
        String valueG1612 = rate.ADRG4 + "";
        String valueG1613 = SysNum + "";
        String valueG1614 = convert(ADRG4) + "";
        
		Row rowG16 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG16.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG16.createCell(cellNum++);
        cell.setCellValue(valueG1611);
        cell = rowG16.createCell(cellNum++);
        cell.setCellValue(valueG1612);
        cell = rowG16.createCell(cellNum++);
        cell.setCellValue(valueG1613);
        cell = rowG16.createCell(cellNum++);
        cell.setCellValue(valueG1614);
        
        //4.1.1合并症分错       
        String valueG1711 = "【五、分组错误；2.合并症分错；2.1应该是重要结果分到一般】";
        String valueG1712 = rate.hbzfc1 + "";
        String valueG1713 = SysNum + "";
        String valueG1714 = convert(hbzqc1) + "";
        
		Row rowG171 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG171.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG171.createCell(cellNum++);
        cell.setCellValue(valueG1711);
        cell = rowG171.createCell(cellNum++);
        cell.setCellValue(valueG1712);
        cell = rowG171.createCell(cellNum++);
        cell.setCellValue(valueG1713);
        cell = rowG171.createCell(cellNum++);
        cell.setCellValue(valueG1714);
        
        //4.1.3合并症分错       
        String valueG1721 = "【五、分组错误；2.合并症分错；2.2应该是重要结果分到不伴】";
        String valueG1722 = rate.hbzfc2 + "";
        String valueG1723 = SysNum + "";
        String valueG1724 = convert(hbzqc2) + "";
        
		Row rowG27 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG27.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG27.createCell(cellNum++);
        cell.setCellValue(valueG1721);
        cell = rowG27.createCell(cellNum++);
        cell.setCellValue(valueG1722);
        cell = rowG27.createCell(cellNum++);
        cell.setCellValue(valueG1723);
        cell = rowG27.createCell(cellNum++);
        cell.setCellValue(valueG1724);
        
        //4.1合并症分错       
        String valueG1731 = "【五、分组错误；2.合并症分错；2.3应该是一般结果是重要】";
        String valueG1732 = rate.hbzfc3 + "";
        String valueG1733 = SysNum + "";
        String valueG1734 = convert(hbzqc3) + "";
        
		Row rowG37 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG37.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG37.createCell(cellNum++);
        cell.setCellValue(valueG1731);
        cell = rowG37.createCell(cellNum++);
        cell.setCellValue(valueG1732);
        cell = rowG37.createCell(cellNum++);
        cell.setCellValue(valueG1733);
        cell = rowG37.createCell(cellNum++);
        cell.setCellValue(valueG1734);
        
        //4.1合并症分错       
        String valueG1741 = "【五、分组错误；2.合并症分错；2.4应该是不伴结果是重要】";
        String valueG1742 = rate.hbzfc4 + "";
        String valueG1743 = SysNum + "";
        String valueG1744 = convert(hbzqc4) + "";
        
		Row rowG47 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG47.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG47.createCell(cellNum++);
        cell.setCellValue(valueG1741);
        cell = rowG47.createCell(cellNum++);
        cell.setCellValue(valueG1742);
        cell = rowG47.createCell(cellNum++);
        cell.setCellValue(valueG1743);
        cell = rowG47.createCell(cellNum++);
        cell.setCellValue(valueG1744);
        
        //4.1合并症分错       
        String valueG1751 = "【五、分组错误；2.合并症分错；2.5应该是一般结果是不伴】";
        String valueG1752 = rate.hbzfc5 + "";
        String valueG1753 = SysNum + "";
        String valueG1754 = convert(hbzqc5) + "";
        
		Row rowG457 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG457.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG457.createCell(cellNum++);
        cell.setCellValue(valueG1751);
        cell = rowG457.createCell(cellNum++);
        cell.setCellValue(valueG1752);
        cell = rowG457.createCell(cellNum++);
        cell.setCellValue(valueG1753);
        cell = rowG457.createCell(cellNum++);
        cell.setCellValue(valueG1754);
        
        //4.1合并症分错       
        String valueG1761 = "【五、分组错误；2.合并症分错；2.6应该是不伴结果是一般】";
        String valueG1762 = rate.hbzfc6 + "";
        String valueG1763 = SysNum + "";
        String valueG1764 = convert(hbzqc6) + "";
        
		Row rowG67 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG67.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG67.createCell(cellNum++);
        cell.setCellValue(valueG1761);
        cell = rowG67.createCell(cellNum++);
        cell.setCellValue(valueG1762);
        cell = rowG67.createCell(cellNum++);
        cell.setCellValue(valueG1763);
        cell = rowG67.createCell(cellNum++);
        cell.setCellValue(valueG1764);
        
        //4.1合并症分错       
        String valueG1771 = "【五、分组错误；2.合并症分错；2.7其他】";
        String valueG1772 = rate.hbzfc7 + "";
        String valueG1773 = SysNum + "";
        String valueG1774 = convert(hbzqc7) + "";
        
		Row rowG77 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG77.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG77.createCell(cellNum++);
        cell.setCellValue(valueG1771);
        cell = rowG77.createCell(cellNum++);
        cell.setCellValue(valueG1772);
        cell = rowG77.createCell(cellNum++);
        cell.setCellValue(valueG1773);
        cell = rowG77.createCell(cellNum++);
        cell.setCellValue(valueG1774);
        
        //5.2病组特殊条件没满足    
        String valueG1811 = "【六、未分组；2.病组特殊条件没满足】";
        String valueG1812 = rate.special + "";
        String valueG1813 = SysNum + "";
        String valueG1814 = convert(special) + "";
        
		Row rowG18 = sheet.createRow(rm++);
        cellNum = 0;
        cell = rowG18.createCell(cellNum++);
        //插值
            cell.setCellValue(hos);
            cell = rowG18.createCell(cellNum++);
        cell.setCellValue(valueG1811);
        cell = rowG18.createCell(cellNum++);
        cell.setCellValue(valueG1812);
        cell = rowG18.createCell(cellNum++);
        cell.setCellValue(valueG1813);
        cell = rowG18.createCell(cellNum++);
        cell.setCellValue(valueG1814);

            String value101 = "";
            Row row101 = sheet.createRow(rm++);
            Write2Excel3.convertSummaryDataToRow(row101, value101);
        
        return rm;//添加的行
        
	}
	
    //四舍五入
    public static   double   convert(double   value){  
        long   l1   =   Math.round(value*1000000);   //四舍五入  
        double   ret   =   l1/1000000.0;               //注意：使用   100.0   而不是   100  
        return   ret;  
    } 
}
