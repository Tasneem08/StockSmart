package com.example.model;

public class StockInfo 
{
	String symbol;
	String soldDate;
	String boughtDate;
	String volume;
	String email;
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getSoldDate()
	{
		return soldDate;
	}
	
	public void setSoldDate(String soldDate)
	{
		this.soldDate = soldDate;
	}
	
	public String getBoughtDate()
	{
		return boughtDate;
	}
	
	public void setBoughtDate(String boughtDate)
	{
		this.boughtDate = boughtDate;
	}
	
	
	public String getVolume()
	{
		return volume;
	}
	

	public String setVolume(String volume)
	{
		return this.volume=volume;
	}
	
	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	
	public String getSymbol()
	{
		return this.symbol;
	}
	
}
