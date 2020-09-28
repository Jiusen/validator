package com.firesoon.calibrator.check;

import com.firesoon.calibrator.pojo.Item;
import com.firesoon.calibrator.pojo.Result;

public class CheckSpecialGroup
{
	public static boolean Check(Result result)
	{
		Item item = result.getItem();
		
		//lyfs
		//AZ19
		if("AZ19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getLyfs()==5))
			{
				return false;
			}
		}
		
		//daynum
		//SV13/SV15
		if("SV13".equalsIgnoreCase(result.getBILL_DRGCODE())
				||"SV15".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getDaynum()>0 && item.getDaynum()<12))
			{
				return false;
			}
		}
		//daynum
		//SV19
		if("SV19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getDaynum()>13))
			{
				return false;
			}
		}
		
		//daynum
		//TX19/XH10/XZ10
		if("TX19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "XH10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "XZ10".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getDaynum()>60))
			{
				return false;
			}
		}
		
		//daynum
		//XH11/XH13/XH15/XZ19
		if("XH11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "XH13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "XH15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "XZ19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getDaynum()<=60 && item.getDaynum()>0))
			{
				return false;
			}
		}
		
		//xsetz
		//ES25
		if("ES25".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getXsetz()>0 && item.getXsetz()<=3000))
			{
				return false;
			}
		}
		//PS19
		if("PS19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getXsetz()>0 && item.getXsetz()<1500))
			{
				return false;
			}
		}
		//PT15
		if("PT15".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getXsetz()>=1500 && item.getXsetz()<2499))
			{
				return false;
			}
		}
		//PT21/PT25
		if("PT21".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "PT25".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getXsetz()>2400))
			{
				return false;
			}
		}
		//SR25
		if("SR25".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getXsetz()>0 && item.getXsetz()<3500))
			{
				return false;
			}
		}
		
		//age
		//BV11/BV15/BZ13/BZ15/CR19/DC10/DC19/DT20/ES06/ES15/FW19/GE10/IC30/IC35/IF10/IF15
		if("BV11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BV15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BZ13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BZ15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "CR19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "DC10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "DC19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "DT20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES06".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FW19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GE10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IC30".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IC35".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IF10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IF15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FW19".equalsIgnoreCase(result.getBILL_DRGCODE())
			//IF25/IG13/IH25/IJ15/IT21/IT25
				|| "IF25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IG13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IH25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IJ15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IT21".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IT25".equalsIgnoreCase(result.getBILL_DRGCODE())
				//LF10/MB10/SR10/SR25/ST10
				|| "LF10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "MB10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ST10".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getAge()>0 && item.getAge()<17))
			{
				return false;
			}
		}
		//BV16/BZ10/BZ11/CR10/DC17/DT10/DT15/DT29/ES10/ES11/ES13/ES25/EX10/EX15/EX19/FW10/GE13/GE15/GU10/GU15
		//IC31/IC33/IF11/IF13/IF23
		if("BV16".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BZ10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BZ11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "CR10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "DC17".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "DT10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "DT15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "DT29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EX10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EX15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EX19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FW10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GE13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GE15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GU10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GU15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IC31".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IC33".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IF11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IF13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IF23".equalsIgnoreCase(result.getBILL_DRGCODE())
				//IF26/IG10/IG15/IH23/IH26/IJ13/IJ16/IT20/JC10/JC19
				|| "IF26".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IG10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IG15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IH23".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IH26".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IJ13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IJ16".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "IT20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "JC10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "JC19".equalsIgnoreCase(result.getBILL_DRGCODE())
				//KT10/KT11/KT15/LF13/LF15/MB17
				|| "KT10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "KT11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "KT15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "LF13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "LF15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "MB17".equalsIgnoreCase(result.getBILL_DRGCODE())
				//MB19/SR13/SR15/ST11/ST13/ST15
				|| "MB19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ST11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ST13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ST15".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(item.getAge()>=17))
			{
				return false;
			}
		}
		return true;
	}
}
