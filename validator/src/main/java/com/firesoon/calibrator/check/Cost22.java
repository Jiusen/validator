package com.firesoon.calibrator.check;

import com.firesoon.calibrator.pojo.Item;
import com.firesoon.calibrator.pojo.Result;

public class Cost22
{
	public static String T22(Result result)
	{
		Item item = result.item;
		String rea = "费用项中费用序号遗漏或多余";
		
		//1、BB10\BG40\BK40\BK50\BY20\EK40\EK50\BB10\BR20\BR30\ER20\ES20\ET20\FK40\FK50
		//FR20/FR30/FS30/GB40/GB50/GR20/GS20/GV20/HT20/HV30/SC30/SK40/SR20下的ICU
		if("BB10".equalsIgnoreCase(result.getBILL_DRGCODE()) 
				|| "BG40".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BK40".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BK50".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BY20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EK40".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EK50".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BB10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BR20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BR30".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ER20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ET20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FK40".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FK50".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FR20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FR30".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FS30".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GB40".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GB50".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GR20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GS20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GV20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HT20".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HV30".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SC30".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SK40".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR20".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) > 120))
			{
				return rea;
			}
		}
		
		//BR19\BR29\BY19\ER19\ES19\ET19下的ICU
		if("BR19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BR29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BY19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ER19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ET19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) <= 120 
					&& Integer.valueOf(result.getItem().getIcu()) >= 48))
			{
				return rea;
			}
		}
		
		//FR19/FR29/FS29/GR19/GS19/GV10/HT19/HV29/SC29/SR19
		if("FR19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FR29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FS29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GR19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GS19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GV10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HT19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HV29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SC29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) <= 120 
					&& Integer.valueOf(result.getItem().getIcu()) > 48))
			{
				return rea;
			}
		}
		
		//ES19下的ICU
		if("BR19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BR29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BY19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ER19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) < 120 
					&& Integer.valueOf(result.getItem().getIcu()) > 48))
			{
				return rea;
			}
		}
		
		//2、BB10\BK29\EK19\EK29\EK39\FJ13\FJ15\FK19\FK29\FK39\GB11
		//GB15\GB21\GB25\GB35\SK19\SK29下的ICU
		if("BK19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BK29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EK19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EK29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "EK39".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FJ13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FJ15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FK19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FK29".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FK39".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GB11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GB15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GB21".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GB25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GB35".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SK19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SK29".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) <= 120 
					&& Integer.valueOf(result.getItem().getIcu()) >= 0))
			{
				return rea;
			}
		}
		
		//BR11\BR13\BR15\ER11\ER15下的ICU
		if("BR11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BR13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BR15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ER11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ER15".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) < 48 
					&& Integer.valueOf(result.getItem().getIcu()) >= 0))
			{
				return rea;
			}
		}
		
		//FR15/SC19
		if("FR15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SC19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) <= 48 
					&& Integer.valueOf(result.getItem().getIcu()) >= 0))
			{
				return rea;
			}
		}

		//BR28\BY18\ES18\ET18\FR28\FS28\GR18\GS18\GV18\HT18\HV28\SR18
		if("BR28".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BY18".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES18".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ET18".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FR28".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FS28".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GR18".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GS18".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GV18".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HT18".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HV28".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR18".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) <= 48 
					&& Integer.valueOf(result.getItem().getIcu()) >= 1))
			{
				return rea;
			}
		}
		
		//ER18
		if("ER18".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) <= 48))
			{
				return rea;
			}
		}

		//BR21\BR25\BY11\BY15\ES06\ES10\ES11\ES13\ES15\ES25\ET11\ET13\ET15
		//FR21\FS21\FS23\FS25\GR11\GR15\GS11\GS15\GV19\HT13\HT15\SR10\SR13\SR15
		//HV23\HV25下的ICU
		if("BR19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BR25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BY11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "BY15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES06".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ES25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ET11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ET13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "ET15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FR21".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FR23".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FR25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FS21".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FS23".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "FS25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GR11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GR15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GS11".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GS15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "GV19".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HT13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HT15".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HV23".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "HV25".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR10".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR13".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "SR15".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIcu()) ==0))
			{
				return rea;
			}
		}
		
		
		//------------CBPTime-------------
		//LM49
		if("LM49".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getCBPTime()) <= 48 && 
					Integer.valueOf(result.getItem().getCBPTime()) >= 1))
			{
				return rea;
			}
		}
		
		//LM59
		if("LM59".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getCBPTime()) <= 96 && 
					Integer.valueOf(result.getItem().getCBPTime()) >= 49))
			{
				return rea;
			}
		}
		
		//LM69\LM89
		if("LM69".equalsIgnoreCase(result.getBILL_DRGCODE())
				|| "LM89".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getCBPTime()) >= 97))
			{
				return rea;
			}
		}
		
		//-------ALSSTime-------//
		//HE19
		if("HE19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getALSSTime()) >= 1
					&& Integer.valueOf(result.getItem().getALSSTime()) <= 3))
			{
				return rea;
			}
		}
		
		//HE29
		if("HE29".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getALSSTime()) >= 4
					&& Integer.valueOf(result.getItem().getALSSTime()) <= 6))
			{
				return rea;
			}
		}
		
		//HE39
		if("HE29".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getALSSTime()) >= 7))
			{
				return rea;
			}
		}
		
		//!---ABY---
		//LG29
		if("LG29".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getABY()) > 0))
			{
				return rea;
			}
		}
		
		//ECMOTime
		//FB10
		if("FB10".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getECMOTime()) >= 96))
			{
				return rea;
			}
		}
		//FB19
		if("FB19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getECMOTime()) < 96))
			{
				return rea;
			}
		}
		
		//dialysis
		//LM19
		if("LM19".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getDialysis()) >= 1
					&& Integer.valueOf(result.getItem().getDialysis()) <= 5))
			{
				return rea;
			}
		}
		//LM29
		if("LM29".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getDialysis()) >= 6
					&& Integer.valueOf(result.getItem().getDialysis()) <= 10))
			{
				return rea;
			}
		}
		//LM39
		if("LM39".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getDialysis()) >= 11))
			{
				return rea;
			}
		}
		
		//!-----IABPTime
		//FB11
		if("FB11".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIABPTime()) >= 1
					&& Integer.valueOf(result.getItem().getIABPTime()) <= 48))
			{
				return rea;
			}
		}
		//FB12
		if("FB12".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIABPTime()) >= 49
					&& Integer.valueOf(result.getItem().getIABPTime()) <= 96))
			{
				return rea;
			}
		}
		//FB13
		if("FB13".equalsIgnoreCase(result.getBILL_DRGCODE()))
		{
			if(!(Integer.valueOf(result.getItem().getIABPTime()) >= 97))
			{
				return rea;
			}
		}
		return "";
	}
}
