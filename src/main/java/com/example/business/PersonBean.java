package com.example.business;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.PersonDAO;
import com.example.model.Person;
import com.example.model.StockInfo;

public class PersonBean 
{
	@Autowired
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) 
	{
		this.personDAO = personDAO;
	}

	public Person insertUser(Person person) 
	{
		return personDAO.insertUser(person);
	}

	public Person signInUser(Person person) 
	{
		return personDAO.signInUser(person);
	}

	public HashMap<String, String> getTopGainers() 
	{
		// TODO Auto-generated method stub
		return personDAO.getTopGainers();
	}

	public HashMap<String, String> getTopLosers() 
	{
		// TODO Auto-generated method stub
		return personDAO.getTopLosers();
	}
	
	public HashMap<String, String> getAverageHigh() 
	{
		// TODO Auto-generated method stub
		return personDAO.getAverageHigh();
	}

	public HashMap<String, String> getAverageLow() 
	{
		// TODO Auto-generated method stub
		return personDAO.getAverageLow();
	}
	
	public HashMap<String, String> getAmazonPerf() 
	{
		// TODO Auto-generated method stub
		return personDAO.getAmazonPerf();
	}
	
	public HashMap<String, String> getMovingAvg() 
	{
		// TODO Auto-generated method stub
		return personDAO.getMovingAvg();
	}

	public ArrayList<StockInfo> dashboardSearchStock(String symbol) 
	{
		// TODO Auto-generated method stub
		return personDAO.dashboardSearchStock(symbol);
	}
	
	public HashMap<String, String> compareStocks2()
	{
		// TODO Auto-generated method stub
		return personDAO.compareStocks2();
	}
	public HashMap<String, String> compareStocks1()
	{
		// TODO Auto-generated method stub
		return personDAO.compareStocks1();
	}
	public HashMap<String, ArrayList<String>> displayAnalyst()
	{
		// TODO Auto-generated method stub
		return personDAO.displayAnalyst();
	}

	
	public HashMap<String, String> getTotalRecords()
	{
		// TODO Auto-generated method stub
		return personDAO.getTotalRecords();
	}
	
	public HashMap<String, ArrayList<String>> displayTechComps()
	{
		// TODO Auto-generated method stub
		return personDAO.displayTechComps();
	}
	
	public HashMap<String, ArrayList<String>> displayEComComps()
	{
		// TODO Auto-generated method stub
		return personDAO.displayEComComps();
	}
	
	public HashMap<String, ArrayList<String>> displayEnergyComps()
	{
		// TODO Auto-generated method stub
		return personDAO.displayEnergyComps();
	}
	
	public HashMap<String, ArrayList<String>> displayUtilitiesComps()
	{
		// TODO Auto-generated method stub
		return personDAO.displayUtilitiesComps();
	}
	
	
	public HashMap<String, ArrayList<String>> displayTransDetails(StockInfo st)
	{
		// TODO Auto-generated method stub
		return personDAO.displayTransDetails(st);
	}
	
	
	public void pushRowIntoTrans(StockInfo st)
	{
		// TODO Auto-generated method stub
		 personDAO.pushRowIntoTrans(st);
	}
	
	public Person manageAccountPwd(Person person) 
	{
		return personDAO.manageAccountPwd(person);
	}

	public HashMap<String, String> dcompareStocks1(String name1) {
		// TODO Auto-generated method stub
		return personDAO.dcompareStocks1(name1);
	}

	public HashMap<String, String> dcompareStocks2(String name2) {
		// TODO Auto-generated method stub
		return personDAO.dcompareStocks2(name2);
	}
	
	public ArrayList<String> fetchStockSymbols()
	{
		// TODO Auto-generated method stub
				return personDAO.fetchStockSymbols();	
	}

	public HashMap<String, ArrayList<String>> getTicker() {
		// TODO Auto-generated method stub
		return personDAO.getTicker();
	}

	public HashMap<String, ArrayList<String>> getBestStocks() {
		// TODO Auto-generated method stub
		return personDAO.getBestStocks();
	}

	public HashMap<String, String> bollingerStocks1(String stock1) {
		// TODO Auto-generated method stub
		return personDAO.bollingerStocks1(stock1);
	}

	public HashMap<String, String> bollingerStocks2(String stock2) {
		// TODO Auto-generated method stub
		return personDAO.bollingerStocks2(stock2);
	}
}

