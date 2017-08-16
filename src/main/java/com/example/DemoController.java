package com.example;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.PersonBean;
import com.example.model.Person;
import com.example.model.StockInfo;

@RestController

class DemoController2{
	
	@RequestMapping(value = "/registerUser",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public Person secondPage(@RequestBody Person person)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		System.out.println(person.getSsn());
		
		Person cityOut =  p.insertUser(person);
	//	System.out.println(cityOut.getName() + " " + cityOut.getCountry());
		return person;
	}
	
	
	
	@RequestMapping(value = "/pushRowIntoTrans",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public void pushRowIntoTrans(@RequestBody StockInfo person)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		//System.out.println(person.getSsn());
		
		 p.pushRowIntoTrans(person);
	//	System.out.println(cityOut.getName() + " " + cityOut.getCountry());
		
	}
	
	@RequestMapping(value = "/signInUser",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public Person signIn(@RequestBody Person person)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		//System.out.println(person.getSsn());
		
		Person cityOut =  p.signInUser(person);
	    System.out.println(cityOut.getFName() + " " + cityOut.getLName());
		return cityOut;
	}
	
	@RequestMapping(value = "/getTopGainers",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getTopGainers()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getTopGainers();
	}
	
	@RequestMapping(value = "/getTopLosers",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getTopLosers()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getTopLosers();
	}
	
	@RequestMapping(value = "/getAverageLow",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getAverageLow()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getAverageLow();
	}
	
	@RequestMapping(value = "/getAverageHigh",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getAverageHigh()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getAverageHigh();
	}
	
	@RequestMapping(value = "/getAmazonPerf",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getAmazonPerf()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getAmazonPerf();
	}
	
	
	@RequestMapping(value = "/getMovingAvg",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getMovingAvg()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getMovingAvg();
	}
	
	@RequestMapping(value = "/dashboardSearchStock",method=RequestMethod.POST, produces="application/json")
	public ArrayList<StockInfo> dashboardSearchStock(String symbol)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.dashboardSearchStock(symbol);
	}
	@RequestMapping(value = "/displayAnalyst",method=RequestMethod.POST, produces="application/json")
	public HashMap<String, ArrayList<String>> displayAnalyst(Person person)
	{

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.displayAnalyst();
	}
	
	@RequestMapping(value = "/compareStocks1",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> compareStocks1()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.compareStocks1();
	}
	
	@RequestMapping(value = "/compareStocks2",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> compareStocks2()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.compareStocks2();
	}
	
	@RequestMapping(value = "/getTotalRecords",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getTotalRecords()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getTotalRecords();
	}
	
	
	@RequestMapping(value = "/displayTechComps",method=RequestMethod.POST, produces="application/json")
	public HashMap<String, ArrayList<String>> displayTechComps()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.displayTechComps();
	}
	
	@RequestMapping(value = "/displayEnergyComps",method=RequestMethod.POST, produces="application/json")
	public HashMap<String, ArrayList<String>> displayEnergyComps()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.displayEnergyComps();
	}
	
	@RequestMapping(value = "/displayEComComps",method=RequestMethod.POST, produces="application/json")
	public HashMap<String, ArrayList<String>> displayEComComps()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.displayEComComps();
	}
	
	@RequestMapping(value = "/displayUtilitiesComps",method=RequestMethod.POST, produces="application/json")
	public HashMap<String, ArrayList<String>> displayUtilitiesComps()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.displayUtilitiesComps();
	}
	
	
	@RequestMapping(value = "/displayTransDetails",method=RequestMethod.POST, produces="application/json")
	public HashMap<String, ArrayList<String>> displayTransDetails(@RequestBody StockInfo st)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.displayTransDetails(st);
	}
	
	@RequestMapping(value = "/manageAccountPwd",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public Person manageAccountPwd(@RequestBody Person person)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		//System.out.println(person.getSsn());
		
		Person cityOut =  p.manageAccountPwd(person);
		return cityOut;
	}
	
	@RequestMapping(value = "/dcompareStocks1",method=RequestMethod.GET, produces="application/json")
    public HashMap<String,String> dcompareStocks1(@RequestParam("name1") String name1)
    {
        System.out.println(name1);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PersonBean p = (PersonBean) applicationContext.getBean("db");
        return p.dcompareStocks1(name1);
    }
    
    @RequestMapping(value = "/dcompareStocks2",method=RequestMethod.GET, produces="application/json")
    public HashMap<String,String> dcompareStocks2(@RequestParam("name2") String name2)
    {
        System.out.println(name2);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PersonBean p = (PersonBean) applicationContext.getBean("db");
        return p.dcompareStocks2(name2);
    }
  
    
    @RequestMapping(value = "/fetchStockSymbols",method=RequestMethod.POST, produces="application/json")
    public ArrayList<String> fetchStockSymbols()
    {
       // System.out.println(name2);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PersonBean p = (PersonBean) applicationContext.getBean("db");
        return p.fetchStockSymbols();
    }
    
    @RequestMapping(value = "/getTicker",method=RequestMethod.POST, produces="application/json")
    public HashMap<String, ArrayList<String>> getTicker()
    {
       // System.out.println(name2);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PersonBean p = (PersonBean) applicationContext.getBean("db");
        return p.getTicker();
    }

    @RequestMapping(value = "/getBestStocks",method=RequestMethod.POST, produces="application/json")
    public HashMap<String, ArrayList<String>> getBestStocks()
    {
       // System.out.println(name2);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PersonBean p = (PersonBean) applicationContext.getBean("db");
        return p.getBestStocks();
    }
    
    
    @RequestMapping(value = "/bollingerStocks1",method=RequestMethod.GET, produces="application/json")
    public HashMap<String, String> bollingerStocks1(String stock1)
    {
       // System.out.println(name2);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PersonBean p = (PersonBean) applicationContext.getBean("db");
        return p.bollingerStocks1(stock1);
    }
	
    @RequestMapping(value = "/bollingerStocks2",method=RequestMethod.GET, produces="application/json")
    public HashMap<String, String> bollingerStocks2(String stock2)
    {
       // System.out.println(name2);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PersonBean p = (PersonBean) applicationContext.getBean("db");
        return p.bollingerStocks2(stock2);
    }
}
