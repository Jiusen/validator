package com.firesoon.calibrator.place;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于存储费用项参数
 * @author Jiusen.Guo
 *
 */
public class PlaceCost
{
	public static Map<String, String> cost = new HashMap<>();  //map形式
	public static String[] costStrs = new String[9];	//字符串数组形式[存在效率问题]
	public static double[] costs = new double[9];	//字符串数组形式[存在效率问题]
	
	public static void plaCost()
	{
		cost.put("dialysis", "dialysis");
		cost.put("ABY", "ABY");
		cost.put("ALSSTime", "ALSSTime");
		cost.put("CBPTime", "CBPTime");
		cost.put("ECMOTime", "ECMOTime");
		cost.put("IABPTime", "IABPTime");
		cost.put("icu", "icu");
		cost.put("vetime", "vetime");
		cost.put("Alteplase", "Alteplase");
		
		costStrs[0] = "dialysis";
		costStrs[1] = "ABY";
		costStrs[2] = "ALSSTime";
		costStrs[3] = "CBPTime";
		costStrs[4] = "ECMOTime";
		costStrs[5] = "IABPTime";
		costStrs[6] = "icu";
		costStrs[7] = "vetime";
		costStrs[8] = "Alteplase";
	}
}
