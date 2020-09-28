package com.firesoon.calibrator.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class DiagReadExcel
{
	private File file;
	
	public DiagReadExcel(String readPath) throws Exception
	{
		file = new File(readPath);
		DiagExcelParser excel = new DiagExcelParser();
		
		// 从 Excel表要中提取数据
		InputStream in = new FileInputStream(file);
		try {
			excel.parse(in);
		}finally {
			in.close();
		}
	}
}
