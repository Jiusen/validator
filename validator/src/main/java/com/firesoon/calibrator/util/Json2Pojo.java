package com.firesoon.calibrator.util;

import com.alibaba.fastjson.JSON;

public class Json2Pojo
{
	
	// String -> Pojo
	public static Object json2pojo(String jsonStr, Class cls)
	{
		Object obj = JSON.parseObject(jsonStr, cls);
		
		return obj;
	}
}
