package com.firesoon.calibrator.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SurgReadExcel
{
	private File file;
	
	public SurgReadExcel(String readPath) throws Exception
	{
		file = new File(readPath);
		SurgExcelParser excel = new SurgExcelParser();
		// 从 Excel表要中提取数据
		InputStream in = new FileInputStream(file);
		try {
			excel.parse(in);
		}finally {
			in.close();
		}
		
		
//		//一系列的检查操作
//		System.out.println("+++++surg： " + SurgExcelParser.map1.get("00.0100"));
//		System.out.println("+++++surg： " + SurgExcelParser.map1.get("00.1300"));
//		System.out.println("+++++surg： " + SurgExcelParser.map1.get("86.7400x035"));
		
//		System.out.println("exit");
	}
}
