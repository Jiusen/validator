package com.firesoon.calibrator.excel;


import java.io.File;

import com.firesoon.calibrator.check.Check;

public class ReadExcel2
{
	private File file;
	
	public ReadExcel2(String readPath) throws Exception
	{
		 //csv文件的读取
		CSVRead.readCsvFile(readPath);
	}
}