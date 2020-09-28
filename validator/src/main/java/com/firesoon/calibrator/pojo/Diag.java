package com.firesoon.calibrator.pojo;

public class Diag
{
	private String diag_name;
	private String version;
	private String diag_code;
	private String main_diag_code;
	private String other_diag_code;
	public String getDiag_name()
	{
		return diag_name;
	}
	public void setDiag_name(String diag_name)
	{
		this.diag_name = diag_name;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public String getDiag_code()
	{
		return diag_code;
	}
	public void setDiag_code(String diag_code)
	{
		this.diag_code = diag_code;
	}
	public String getMain_diag_code()
	{
		return main_diag_code;
	}
	public void setMain_diag_code(String main_diag_code)
	{
		this.main_diag_code = main_diag_code;
	}
	public String getOther_diag_code()
	{
		return other_diag_code;
	}
	public void setOther_diag_code(String other_diag_code)
	{
		this.other_diag_code = other_diag_code;
	}
	
	@Override
	public String toString()
	{
		return "Diag [diag_name=" + diag_name + ", version=" + version + ", diag_code=" + diag_code
				+ ", main_diag_code=" + main_diag_code + ", other_diag_code=" + other_diag_code + "]";
	}
	
}
