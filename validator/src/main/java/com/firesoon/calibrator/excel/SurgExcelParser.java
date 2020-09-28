package com.firesoon.calibrator.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.firesoon.calibrator.pojo.Item;
import com.firesoon.calibrator.pojo.Result;
import com.firesoon.calibrator.pojo.Surg;

public class SurgExcelParser
{
	Surg surg;
	public static Map<String, Surg> map1 = new HashMap<>();
	
	public void parse(InputStream inputStream) throws Exception
	{
		Workbook wb = WorkbookFactory.create(inputStream);
		
        Sheet sheet = wb.getSheetAt(0);// 打开

        int y = 1; // 取出指定位置的数
        
		while(true)
		{
	        
			surg = new Surg();
	        
	        Row row = sheet.getRow(y++);
	        
	        if(row == null)
	        	break;
	        
	        Cell cell = row.getCell(0);
	        
	        if(cell == null)
	        {
	        	surg.setSurg_code("");
	        }
	        else {
		        if(cell.getCellType() == CellType.STRING)
		        {
		        	surg.setSurg_code(cell.getStringCellValue());
		        }
		        else if(cell.getCellType() == CellType.NUMERIC)
		        {
		        	double value = cell.getNumericCellValue();
		        	if(value <= 0)
		        		surg.setSurg_code("");
		        	else
		        		surg.setSurg_code(String.format("%.0f", value));
		        }
	        }
	        
//	        surg.setSurg_code(cell.getStringCellValue());
	        
	        cell = row.getCell(1);
	        if(cell == null)
	        {
	        	surg.setSurg_name("");
	        }
	        else {
		        if(cell.getCellType() == CellType.STRING)
		        {
		        	surg.setSurg_name(cell.getStringCellValue());
		        }
		        else if(cell.getCellType() == CellType.NUMERIC)
		        {
		        	double value = cell.getNumericCellValue();
		        	if(value <= 0)
		        		surg.setSurg_name("");
		        	else
		        		surg.setSurg_name(String.format("%.0f", value));
		        }
	        }
//	        surg.setSurg_name(cell.getStringCellValue());
	        
	        cell = row.getCell(2);
	        if(cell == null)
	        {
	        	surg.setType("");
	        }else {

	        	cell.setCellType(CellType.STRING);
	        	surg.setType(cell.getStringCellValue().trim());
	        }
	        
	        cell = row.getCell(3);
	        if(cell == null)
	        {
	        	surg.setVersion("");
	        }
	        else {
				cell.setCellType(CellType.STRING);
	        	surg.setVersion(cell.getStringCellValue());
	        }
	        
	        if(surg.getVersion().equals("《国家临床2.0版本》"))
	        {

	        	map1.put(surg.getSurg_code().split("\\+")[0], surg);
	        }
		}
	}
}
