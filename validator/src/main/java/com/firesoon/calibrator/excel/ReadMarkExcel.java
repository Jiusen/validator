package com.firesoon.calibrator.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReadMarkExcel
{
	//存储佛山标记表drg_code、cc
	public static Map<String, String> formatMapFS = new HashMap<>();
	
	//存储台州标记表drg_code、cc
	public static Map<String, String> formatMapTZ = new HashMap<>();
	
	private File file;
	
	public ReadMarkExcel(String readPath) throws Exception
	{
		file = new File(readPath);
		MarkExcelParser excel = new MarkExcelParser();
		
		// 从 Excel表要中提取数据
		InputStream in = new FileInputStream(file);
		try {
			excel.parse(in);
		}finally {
			in.close();
		}
	}
	
}
