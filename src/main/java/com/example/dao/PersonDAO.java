package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Person;
import com.example.model.StockInfo;

public interface PersonDAO 
{
	Person insertUser(Person person);

	Person signInUser(Person person1);

	HashMap<String, String> getTopGainers();

	HashMap<String, String> getTopLosers();
	
	HashMap<String, String> getAverageLow();
	
	HashMap<String, String> getAverageHigh();
	
	HashMap<String, String> getAmazonPerf();
	
	HashMap<String, String> getMovingAvg();
	
	ArrayList<StockInfo> dashboardSearchStock(String symbol);
	HashMap<String, String> compareStocks2();
	HashMap<String, String> compareStocks1() ;
	HashMap<String, ArrayList<String>> displayAnalyst() ;
	
	HashMap<String, String> getTotalRecords();
	
	HashMap<String, ArrayList<String>> displayTechComps();
	
	HashMap<String, ArrayList<String>> displayEComComps();
	
	HashMap<String, ArrayList<String>> displayEnergyComps();
	
	HashMap<String, ArrayList<String>> displayUtilitiesComps();
	HashMap<String, ArrayList<String>> displayTransDetails(StockInfo st);

	void pushRowIntoTrans(StockInfo stock);
	
	Person manageAccountPwd(Person person1);

	HashMap<String, String> dcompareStocks1(String name1);

	HashMap<String, String> dcompareStocks2(String name2);

	ArrayList<String> fetchStockSymbols();

	HashMap<String, ArrayList<String>> getBestStocks();

	HashMap<String, ArrayList<String>> getTicker();

	HashMap<String, String> bollingerStocks1(String stock1);

	HashMap<String, String> bollingerStocks2(String stock2);
}
