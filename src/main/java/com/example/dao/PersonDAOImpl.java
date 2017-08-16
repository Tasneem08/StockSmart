package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.example.model.Person;
import com.example.model.StockInfo;

public class PersonDAOImpl implements PersonDAO 
{
	private JdbcTemplate jdbcTemplate;
	 
    public JdbcTemplate getJdbcTemplate() 
    {
        return jdbcTemplate;
    }
 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public Person insertUser(Person person1) 
	{
		final Person person = new Person();
        String quer = "Insert into Person values('"+person1.getFName()+"','"+person1.getLName()+"','"+person1.getDob()+"','01-JAN-03 05.10.11.317000000','"+person1.getPassword()+"','"+person1.getLogin()+"','"+person1.getState()+"','"+person1.getCity()+"','"+person1.getStreet()+"',"+person1.getZip()+",'"+person1.getSsn()+"')";
        jdbcTemplate.execute(quer);
        return person1; 
	}
	
	
	@Override
	public void pushRowIntoTrans(StockInfo stock) 
	{
		String sold = stock.getSoldDate();
		String bought = stock.getBoughtDate();
		if(sold != null)
		{
			sold = "'"+sold+"'";
		}
		if(bought != null)
		{
			bought = "'"+bought+"'";
		}
		
        String quer = "INSERT INTO TRANSACTIONDETAILS(NO_OF_SHARES, SOLD_ON_DATE, BOUGHT_ON_DATE, SYMBOL, EMAIL, UPDATE_TM) VALUES ("+stock.getVolume()+","+sold+","+bought+",'"+stock.getSymbol()+"','"+stock.getEmail()+"', CURRENT_TIMESTAMP)";
        
        jdbcTemplate.execute(quer);
	}
	
	@Override
	public Person signInUser(Person person1) 
	{
		final Person person = new Person();
        String quer = "Select * from Person where email = '"+person1.getLogin()+"' and pwd = '" + person1.getPassword() + "'";
        jdbcTemplate.execute(quer);
        return (Person) jdbcTemplate.query(quer, new ResultSetExtractor<Person>() {
        
        	public Person extractData(ResultSet rs) throws SQLException, DataAccessException {
     
        	if(!rs.next())
        	{
        		person.setSsn("-1");
        	}
            if (rs.next()) 
            {
            	person.setFName(rs.getString(1));
            	person.setLName(rs.getString(2));
            	person.setSsn(rs.getString(11));
            }
            
            return person;
       
	}});
   }
	
	@Override
	public HashMap<String, String> getTopGainers() 
	{
		String quer = "SELECT * FROM (SELECT ST.SYMBOL, (ST.OPEN - ST.CLOSE) AS GAIN FROM STOCKTRENDS ST WHERE ST.OPEN - ST.CLOSE > 0  AND ST.TIMESTAMP = '01-MAR-17' ORDER BY GAIN DESC) WHERE ROWNUM <=10";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> getTopLosers() 
	{
		String quer = "SELECT * FROM (SELECT ST.SYMBOL, (ST.OPEN - ST.CLOSE) AS LOSS FROM STOCKTRENDS ST WHERE ST.TIMESTAMP = '01-MAR-17' ORDER BY LOSS) WHERE ROWNUM <=10";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> compareStocks1() 
	{
		String quer = "SELECT TO_CHAR(TO_DATE(MON, 'MM'), 'Month'), AVG(HIGH) FROM MONTHLYSTATS WHERE YR = 2016 GROUP BY SYMBOL,MON HAVING SYMBOL = 'HD' ORDER BY MON";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> compareStocks2() 
	{
		String quer = "SELECT TO_CHAR(TO_DATE(MON, 'MM'), 'Month'), AVG(HIGH) FROM MONTHLYSTATS WHERE YR = 2016 GROUP BY SYMBOL,MON HAVING SYMBOL = 'COST' ORDER BY MON";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
	

	@Override
	public HashMap<String, ArrayList<String>> displayAnalyst() 
	{
		String quer = "select personphone.ssn, fname,lname,email,personphone.PHONENO from person,analyst,personphone where analyst.ssn = personphone.ssn and person.ssn = analyst.ssn";
		
		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
		
		return (HashMap<String,ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
		{
	        
        	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		
        		while (rs.next())
        		{
        			ArrayList<String> al = new ArrayList<String>();
        		
        			al.add(rs.getString("Fname"));
        			al.add(rs.getString("LName"));
        			al.add(rs.getString("Email"));
        			al.add(rs.getString("PhoneNo"));
        			hm.put(rs.getString("SSN"),al);	    
        		    
        		}
        		
             return hm;
        	}
        
	});
	}
@Override
	public HashMap<String, String> getAverageLow() 
	{
		String quer = "SELECT * FROM (SELECT SYMBOL, AVG(LOW) AS AVG_LOW FROM STOCKTRENDS GROUP BY SYMBOL ORDER BY AVG_LOW DESC) WHERE ROWNUM <=10";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> getAverageHigh() 
	{
		String quer = "SELECT * FROM (SELECT SYMBOL, AVG(HIGH) AS AVG_HIGH FROM STOCKTRENDS GROUP BY SYMBOL ORDER BY AVG_HIGH DESC) WHERE ROWNUM <=10";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> getAmazonPerf() 
	{
		String quer = "SELECT TO_CHAR(TO_DATE(MON, 'MM'), 'Month'), AVG(HIGH) FROM MONTHLYSTATS WHERE YR = 2016 GROUP BY SYMBOL,MON HAVING SYMBOL = 'AMZN' ORDER BY MON";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
        		
        		System.out.println(hm.keySet());
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> getMovingAvg() 
	{
		String quer = "SELECT * FROM (SELECT SYMBOL, AVG(CLOSE) AS AVG_CLOSING FROM STOCKTRENDS GROUP BY SYMBOL ORDER BY AVG_CLOSING) WHERE ROWNUM <=45";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
        		
        		System.out.println(hm.keySet());
             return hm;
        	}
	});
	}
	
	@Override
	public ArrayList<StockInfo> dashboardSearchStock(String symbol) 
	{
		String quer = "SELECT * FROM (SELECT SYMBOL, AVG(CLOSE) AS AVG_CLOSING FROM STOCKTRENDS GROUP BY SYMBOL  HAVING SYMBOL ='"+symbol+"' ORDER BY AVG_CLOSING) WHERE ROWNUM <=45";
		ArrayList<StockInfo> hm = new ArrayList<StockInfo>();
		StockInfo st = new StockInfo();
		
		return (ArrayList<StockInfo>) jdbcTemplate.query(quer, new ResultSetExtractor<ArrayList<StockInfo>>() 
		{
	        
        	public ArrayList<StockInfo> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			st.setSymbol(rs.getNString("SYMBOL"));
        			hm.add(st);
        		}
        		System.out.println(st.getSymbol());
             return hm;
        	}
	});
	}
	
	//getTotalRecords();
	
	@Override
	public HashMap<String, String> getTotalRecords() 
	{
		String quer1 = "select sum(cnt) from (select count(*) as cnt from person union select count(*) as cnt from analyst union select count(*) as cnt from article union select count(*) as cnt from company union";
		String quer2 = " select count(*) as cnt from stockinfo union select count(*) as cnt from managesanalyst union select count(*) as cnt from managesuser union select count(*) as cnt from personphone";
		String quer3 = " union select count(*) as cnt from registereduser union select count(*) as cnt from stocktrends union select count(*) as cnt from transactiondetails)";
		String quer = quer1+quer2+quer3;
		HashMap<String, String> hm = new HashMap<String,String>();
		
		jdbcTemplate.execute(quer);
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put("Total Records ", rs.getString(1));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, ArrayList<String>> displayTechComps() 
	{
		String quer = "select * from company where sector in ('Technology', 'IT services','Travel Technology','Enterprise software','Computer software')";
		
		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
		
		return (HashMap<String,ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
		{
	        
        	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		
        		while (rs.next())
        		{
        			ArrayList<String> al = new ArrayList<String>();
        			al.add(rs.getString(2));
        			al.add(rs.getString(3));
        			al.add(rs.getString(4));
        			al.add(rs.getString(6));
        			hm.put(rs.getString(2),al);	    
        		 
        		}
        		System.out.println(hm);
             return hm;
        	}
        
	});
	}
	
	@Override
	public HashMap<String, ArrayList<String>> displayEComComps() 
	{
		String quer = "select * from company where sector in ('E-commerce', 'Telecommunications','Consumer electronics')";
		
		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
		
		return (HashMap<String,ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
		{
	        
        	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		
        		while (rs.next())
        		{
        			ArrayList<String> al = new ArrayList<String>();
        			al.add(rs.getString(2));
        			al.add(rs.getString(3));
        			al.add(rs.getString(4));
        			al.add(rs.getString(6));
        			hm.put(rs.getString(2),al);	    
        		 
        		}
        		System.out.println(hm);
             return hm;
        	}
        
	});
	}
	
	@Override
	public HashMap<String, ArrayList<String>> displayUtilitiesComps() 
	{
		String quer = "select * from company where sector in ('Insurance', 'Banking','Retail','Financial services','Utilities', 'Electric Utilities', 'Internet')";
		
		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
		
		return (HashMap<String,ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
		{
	        
        	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		
        		while (rs.next())
        		{
        			ArrayList<String> al = new ArrayList<String>();
        			al.add(rs.getString(2));
        			al.add(rs.getString(3));
        			al.add(rs.getString(4));
        			al.add(rs.getString(6));
        			hm.put(rs.getString(2),al);	    
        		 
        		}
        		System.out.println(hm);
             return hm;
        	}
        
	});
	}
	
	@Override
	public HashMap<String, ArrayList<String>> displayEnergyComps() 
	{
		String quer = "select * from company where sector in ('Petroleum industry', 'Oil and gas','Energy','Natural gas utility','Power generation','Uninterruptible power supplies','Oilfield services & equipment')";
		
		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
		
		return (HashMap<String,ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
		{
	        
        	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		
        		while (rs.next())
        		{
        			ArrayList<String> al = new ArrayList<String>();
        			al.add(rs.getString(2));
        			al.add(rs.getString(3));
        			al.add(rs.getString(4));
        			al.add(rs.getString(6));
        			hm.put(rs.getString(2),al);	    
        		 
        		}
        		System.out.println(hm);
             return hm;
        	}
        
	});
	}
	
	@Override
	public HashMap<String, ArrayList<String>> displayTransDetails(StockInfo st) 
	{
		String quer = "select * from transactiondetails where email = '"+st.getEmail()+"' ORDER BY UPDATE_TM";
		
		
		
		return (HashMap<String,ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
		{
	        
        	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
        		while (rs.next())
        		{
        			ArrayList<String> al = new ArrayList<String>();
        			al.add(rs.getString(2));
        			al.add(rs.getString(3));
        			al.add(rs.getString(4));
        			al.add(rs.getString(5));
        			al.add(rs.getString(7));
        			hm.put(rs.getString(1),al);	    
        		 
        		}
        		System.out.println(hm);
             return hm;
        	}
        
	});
	}
	
	@Override
	public Person manageAccountPwd(Person person1) 
	{
		final Person person = new Person();
        String quer = "Update Person set pwd = '"+person1.getPassword()+"' where email = '"+person1.getLogin()+"'";
        jdbcTemplate.execute(quer);
        return person1; 
	}
	
	@Override
    public HashMap<String, String> dcompareStocks1(String name1) 
    {
		String quer = "WITH TEMPSTATS AS (SELECT STOCK.HIGH, STOCK.LOW, STOCK.SYMBOL,(SELECT DISTINCT EXTRACT(MONTH FROM INN.TIMESTAMP) FROM STOCKTRENDS INN WHERE INN.TIMESTAMP = STOCK.TIMESTAMP) AS MON, (SELECT DISTINCT EXTRACT(YEAR FROM INN2.TIMESTAMP) FROM STOCKTRENDS INN2 WHERE INN2.TIMESTAMP = STOCK.TIMESTAMP) AS YR FROM STOCKTRENDS STOCK WHERE STOCK.SYMBOL IN (SELECT SYMBOL FROM STOCKINFO WHERE C_ID IN (SELECT C_ID FROM COMPANY WHERE SYMBOL = '"+name1+"'))) SELECT TO_CHAR(TO_DATE(MON, 'MM'), 'Month'), AVG(HIGH) FROM TEMPSTATS WHERE YR = 2015 GROUP BY SYMBOL,MON ORDER BY MON";
        
		System.out.println("in dcompare 1  "+name1);
        return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
        {
            
            public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
            	System.out.println("in dcompare 1 method "+name1);
            	HashMap<String,String> hm = new HashMap<String,String>();
                while (rs.next()) 
                {
                    hm.put(rs.getString(1), rs.getString(2));
                }
                System.out.println(hm);
             return hm;
            }
    });
    }
    
    @Override
    public HashMap<String, String> dcompareStocks2(String name2) 
    {
    	System.out.println("in dcompare 2  "+name2);
        String quer = "WITH TEMPSTATS AS (SELECT STOCK.HIGH, STOCK.LOW, STOCK.SYMBOL,(SELECT DISTINCT EXTRACT(MONTH FROM INN.TIMESTAMP) FROM STOCKTRENDS INN WHERE INN.TIMESTAMP = STOCK.TIMESTAMP) AS MON, (SELECT DISTINCT EXTRACT(YEAR FROM INN2.TIMESTAMP) FROM STOCKTRENDS INN2 WHERE INN2.TIMESTAMP = STOCK.TIMESTAMP) AS YR FROM STOCKTRENDS STOCK WHERE STOCK.SYMBOL IN (SELECT SYMBOL FROM STOCKINFO WHERE C_ID IN (SELECT C_ID FROM COMPANY WHERE SYMBOL = '"+name2+"'))) SELECT TO_CHAR(TO_DATE(MON, 'MM'), 'Month'), AVG(HIGH) FROM TEMPSTATS WHERE YR = 2015 GROUP BY SYMBOL,MON ORDER BY MON";
        
        
        return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
        {
            
            public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
            	HashMap<String,String> hm = new HashMap<String,String>();
                while (rs.next()) 
                {
                    hm.put(rs.getString(1), rs.getString(2));
                }
                System.out.println(hm);
             return hm;
            }
    });
    }
    
	@Override
	public ArrayList<String> fetchStockSymbols() 
	{
		String quer = "select distinct symbol from stocktrends order by symbol";
		jdbcTemplate.execute(quer);
		
		
		return (ArrayList<String>) jdbcTemplate.query(quer, new ResultSetExtractor<ArrayList<String>>() 
		{ 
	        
        	public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{   
        		ArrayList<String> hm = new ArrayList<String>();
        		while (rs.next()) 
        		{
        			hm.add(rs.getString(1));
        			System.out.println(rs.getString(1));
        		}
        		
        		
             return hm;
             
        	}
	});
	}
	
	@Override
	public HashMap<String, ArrayList<String>> getTicker() 
	{
	String quer = "select SYMBOL,Round(close,0) as close from stocktrends where timestamp='08-MAR-17'";
	HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();

	return (HashMap<String, ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
	{
	       
	       	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
	       	{
	       	while (rs.next()) 
	       	{
	       	ArrayList<String> al = new ArrayList<String>();
	       	al.add(rs.getString(1));
	       	al.add(rs.getString(2));
	       	hm.put(rs.getString(1), al);
	       	}
	       	
	       	System.out.println(hm.keySet());
	            return hm;
	       	}
	});
	}
	@Override
	public HashMap<String, ArrayList<String>> getBestStocks() 
	{
	String quer = "SELECT  A2.SYMBOL, A1.SECTOR,ROUND(BESTSTOCK,0) AS BESTSTOCK FROM (SELECT MAX(AVERAGE_CLOSING) AS BESTSTOCK, SECTOR FROM (SELECT AVG(CLOSE) AS AVERAGE_CLOSING, STOCKINFO.SYMBOL, SECTOR FROM STOCKTRENDS, STOCKINFO, COMPANY WHERE COMPANY.C_ID = STOCKINFO.C_ID AND STOCKTRENDS.SYMBOL = STOCKINFO.SYMBOL GROUP BY SECTOR,STOCKINFO.SYMBOL ORDER BY SECTOR, AVERAGE_CLOSING DESC) GROUP BY SECTOR) A1, (SELECT AVG(CLOSE) AS AVERAGE_CLOSING, STOCKINFO.SYMBOL, SECTOR FROM STOCKTRENDS, STOCKINFO, COMPANY WHERE COMPANY.C_ID = STOCKINFO.C_ID AND STOCKTRENDS.SYMBOL = STOCKINFO.SYMBOL GROUP BY SECTOR,STOCKINFO.SYMBOL ORDER BY SECTOR, AVERAGE_CLOSING DESC) A2 WHERE A2.AVERAGE_CLOSING = BESTSTOCK";
	HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();

	return (HashMap<String, ArrayList<String>>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,ArrayList<String>>>() 
	{
	       
	       	public HashMap<String,ArrayList<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
	       	{
	       	while (rs.next()) 
	       	{
	       	ArrayList<String> al = new ArrayList<String>();
	       	al.add(rs.getString(1));
	       	al.add(rs.getString(2));
	       	al.add(rs.getString(3));
	       	hm.put(rs.getString(1), al);
	       	}
	       	
	       	System.out.println(hm.keySet());
	            return hm;
	       	}
	});
	}
	
	@Override
	public HashMap<String, String> bollingerStocks1(String stock1) 
	{
		String quer = "WITH MIDDLE_BAND AS (SELECT AVG(CLOSE) AS SMA FROM STOCKTRENDS WHERE SYMBOL='"+stock1+"' AND TIMESTAMP BETWEEN (TO_TIMESTAMP('24 JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')-20)  AND TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')), STD_DEV AS (SELECT POWER((CLOSE-(SELECT AVG(CLOSE) AS AVG_CLOSE FROM STOCKTRENDS WHERE TIMESTAMP BETWEEN (TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')-20) AND TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF') AND SYMBOL = '"+stock1+"')),2) AS SDV FROM STOCKTRENDS WHERE TIMESTAMP BETWEEN (TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')-20) AND TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF') AND SYMBOL = '"+stock1+"'), DIV AS (SELECT COUNT(*) AS CN FROM STOCKTRENDS) SELECT ROWNUM, TO_CHAR(MIDDLE_BAND.SMA,'99.999999') AS MIDDLE_BAND,TO_CHAR(MIDDLE_BAND.SMA+(SQRT(STD_DEV.SDV/DIV.CN)*2),'99.999999') AS UPPER_BAND,TO_CHAR(MIDDLE_BAND.SMA-(SQRT(STD_DEV.SDV/DIV.CN)*2),'99.999999') AS LOWER_BAND FROM STD_DEV,MIDDLE_BAND,DIV";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		System.out.println("here");
        		while (rs.next()) 
        		{
        			System.out.println(rs.getString(1)+"  "+rs.getString(3));
        			hm.put(rs.getString(1), rs.getString(3));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> bollingerStocks2(String stock2) 
	{
		String quer = "WITH MIDDLE_BAND AS (SELECT AVG(CLOSE) AS SMA FROM STOCKTRENDS WHERE SYMBOL='"+stock2+"' AND TIMESTAMP BETWEEN (TO_TIMESTAMP('24 JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')-20)  AND TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')), STD_DEV AS (SELECT POWER((CLOSE-(SELECT AVG(CLOSE) AS AVG_CLOSE FROM STOCKTRENDS WHERE TIMESTAMP BETWEEN (TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')-20) AND TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF') AND SYMBOL = '"+stock2+"')),2) AS SDV FROM STOCKTRENDS WHERE TIMESTAMP BETWEEN (TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF')-20) AND TO_TIMESTAMP('24-JUL-15 00:00:00.000000', 'DD-MON-RR HH24:MI:SS.FF') AND SYMBOL = '"+stock2+"'), DIV AS (SELECT COUNT(*) AS CN FROM STOCKTRENDS) SELECT ROWNUM, TO_CHAR(MIDDLE_BAND.SMA,'99.999999') AS MIDDLE_BAND,TO_CHAR(MIDDLE_BAND.SMA+(SQRT(STD_DEV.SDV/DIV.CN)*2),'99.999999') AS UPPER_BAND,TO_CHAR(MIDDLE_BAND.SMA-(SQRT(STD_DEV.SDV/DIV.CN)*2),'99.999999') AS LOWER_BAND FROM STD_DEV,MIDDLE_BAND,DIV";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		System.out.println("here");
        		while (rs.next()) 
        		{
        			System.out.println(rs.getString(1)+"  "+rs.getString(4));
        			hm.put(rs.getString(1), rs.getString(3));
        		}
             return hm;
        	}
	});
	}

	
}
