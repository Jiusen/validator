package com.firesoon.calibrator.excel;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

import com.firesoon.calibrator.pojo.Diag;

public class DiagExcelParser
{
	Diag diag;
	public static Map<String, Diag> map1 = new HashMap<>();
	
	public void parse(InputStream inputStream) throws Exception
	{
		Workbook wb = WorkbookFactory.create(inputStream);
		
        Sheet sheet = wb.getSheetAt(0);// 打开

        int y = 1; // 取出指定位置的数
        
		while(true)
		{
	        
			diag = new Diag();
	        
	        Row row = sheet.getRow(y++);
	        
	        if(row == null)
	        	break;
	        
	        Cell cell = row.getCell(0);
	        if(cell == null)
	        {
	        	diag.setDiag_name("");
	        }else {
				cell.setCellType(CellType.STRING);
	        	diag.setDiag_name(cell.getStringCellValue());
	        }
	       
	        cell = row.getCell(1);
	        diag.setVersion(cell.getStringCellValue());
	        
	        cell = row.getCell(2);
	        if(cell == null)
	        {
	        	diag.setDiag_code("");
	        }else {
	        	diag.setDiag_code(cell.getStringCellValue().trim());
	        }
	        
	        cell = row.getCell(3);
	        if(cell == null)
	        {
	        	diag.setMain_diag_code("");
	        }
	        else {
	        	diag.setMain_diag_code(cell.getStringCellValue());
	        }
	        
	        cell = row.getCell(4);
	        if(cell == null)
	        {
	        	diag.setOther_diag_code("");
	        }
	        else {
				cell.setCellType(CellType.STRING);
	        	diag.setOther_diag_code(cell.getStringCellValue());
	        }
	        
	        if(diag.getVersion().equals("《国家临床2.0版本》"))
	        {
	        	map1.put(diag.getMain_diag_code(), diag);
	        }
		}
	}
}
