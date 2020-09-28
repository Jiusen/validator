package com.firesoon.calibrator.pojo;

public class Mark
{
	public String drg_code;
	public String cc;
	
	
	public String getDrg_code()
	{
		return drg_code;
	}


	public void setDrg_code(String drg_code)
	{
		this.drg_code = drg_code;
	}


	public String getCc()
	{
		return cc;
	}


	public void setCc(String cc)
	{
		this.cc = cc;
	}


	@Override
	public String toString()
	{
		return "Mark [drg_code=" + drg_code + ", cc=" + cc + "]";
	}
	
	
}
