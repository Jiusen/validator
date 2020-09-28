package com.firesoon.calibrator.excel;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.firesoon.calibrator.pojo.Mark;
import com.firesoon.calibrator.pojo.Surg;

public class MarkExcelParser
{
	public void parse(InputStream inputStream) throws Exception
	{
		Workbook wb = WorkbookFactory.create(inputStream);
		
		//佛山标记表
        Sheet sheetFS = wb.getSheetAt(0);// 打开

        int y = 1; // 取出指定位置的数
        
		while(true)
		{
	        
			Mark mark1 = new Mark();
	        
	        Row row = sheetFS.getRow(y++);

	        if(row == null)
			{
				break;
			}
	        
	        Cell cell = row.getCell(1);

	        mark1.setDrg_code(cell.getStringCellValue());
	        
	        cell = row.getCell(7);
	        
	        if(cell == null)
	        {
	        	mark1.setCc("");
	        }
	        else {
	        	cell.setCellType(CellType.STRING);
	        	mark1.setCc(cell.getStringCellValue());
	        }
	        
	        //数据放入佛山map
	        ReadMarkExcel.formatMapFS.put(mark1.getDrg_code(), mark1.getCc());
	       
	    }
	}
}
