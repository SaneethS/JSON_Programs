package com.stockaccount;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockAccount implements StockInterface{
	private final String STOCKS_FILE = "data/stockAccount.json";
	private String fileName;
    private JSONArray stocksData;
    List<CompanyShares> companyShares = new ArrayList<CompanyShares>();
    
    StockAccount(String fileName) {
    	this.fileName = fileName;
    }
    
    public void init() {
    	List<CompanyShares> companySharesList = new ArrayList<CompanyShares>();
		try {
			FileReader reader = new FileReader(fileName);
			JSONParser parser = new JSONParser();
		    JSONObject obj = (JSONObject) parser.parse(reader);
		    JSONArray companyShares = (JSONArray) obj.get("companyShares");
		    Iterator<JSONObject> iterator = companyShares.iterator();
		    while (iterator.hasNext()) {
		       CompanyShares companyShare = new CompanyShares();
		       JSONObject compShare = iterator.next();
		       companyShare.setStockSymbol(compShare.get("stockSymbol").toString());
		       companyShare.setNoOfShares((long) compShare.get("noOfShares"));

		       JSONArray transactions = (JSONArray) compShare.get("transactions");
		       Iterator<JSONObject> iterator2 = transactions.iterator();

		       List<Transaction> transactionList = new ArrayList<Transaction>();
		       while (iterator2.hasNext()) {
		           Transaction transact = new Transaction();
		           JSONObject transaction = iterator2.next();
		           transact.setDateTime(transaction.get("DateTime").toString());
		           transact.setNoOfShares((long) transaction.get("noOfShares"));
		           transact.setState((String) transaction.get("State"));
		           transactionList.add(transact);
		       	}

		        companyShare.setList(transactionList);
		        companySharesList.add(companyShare);
		    }
		        this.companyShares = companySharesList;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
       
    }
    
    private void update(String symbol, long noOfShares, CompanyShares companyShare, String state) {
        long prevShares = companyShare.getNoOfShares();
        if (state == Transaction.BUY) {
            companyShare.setNoOfShares(prevShares + noOfShares);
        }
        else {
            companyShare.setNoOfShares(prevShares - noOfShares);
        }
        long millis = System.currentTimeMillis();
        Date dateTime = new Date(millis);
        Transaction transaction = new Transaction(dateTime.toString(), noOfShares, state);
        companyShare.addTransaction(transaction);
        companyShares.add(companyShare);

        Iterator<JSONObject> iterator = stocksData.iterator();
        
        while (iterator.hasNext()) {
            JSONObject stock = iterator.next();
            if (stock.get("stockSymbol").equals(symbol)) {
                prevShares = (long)stock.get("noOfShares");
                stock.remove("noOfShares");
                if (state == Transaction.BUY) {
                    stock.put("noOfShares", prevShares - noOfShares);
                }
                else {
                    stock.put("noOfShares", prevShares + noOfShares);
                }
            }
        }

        try {
            FileWriter writer = new FileWriter("data/stockAccount.json");
            JSONObject result = new JSONObject();
            result.put("stocks", stocksData);
            writer.write(result.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
	@Override
	public double valueOf() {
		double value = 0;
        for (CompanyShares companyShare : companyShares) {
            value += valueof(companyShare);
        }  
        return value;
	}
	
	private double valueof(CompanyShares companyShare) {
		read();
        Iterator<JSONObject> iterator = stocksData.iterator();
        double sharePrice = 0.0;
        while (iterator.hasNext()) {
            JSONObject stock = iterator.next();
            if (stock.get("stockSymbol").equals(companyShare.getStockSymbol())) {
                sharePrice = (double) stock.get("sharePrice");
            }
        }
        
        return sharePrice * companyShare.getNoOfShares();
	}

	@Override
	public void buy(int amount, String symbol) {
		read();
        Iterator<JSONObject> itr = stocksData.iterator();

        long noOfShares = 0;
        while (itr.hasNext()) {
            JSONObject stock = itr.next();
            if (stock.get("stockSymbol").equals(symbol)) {
                noOfShares = (long) stock.get("noOfShares");
            }
        }

        if (amount > noOfShares) {
            System.out.println("Insufficient Shares");
        }
        else {
            CompanyShares newCompanyShare = null;
            for (CompanyShares companyShare : companyShares) {
                if (companyShare.getStockSymbol().equals(symbol)) {
                    newCompanyShare = companyShare;
                    companyShares.remove(companyShare);
                    break;
                }
            }
            if (newCompanyShare == null) {
                newCompanyShare = new CompanyShares(symbol);
            }

            
            update(symbol, amount, newCompanyShare ,Transaction.BUY);
        }

	}

	private void read() {
		FileReader reader;
		try {
			reader = new FileReader("data/stockAccount.json");
			JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject) parser.parse(reader);
	        stocksData = (JSONArray) obj.get("stocks");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}

	@Override
	public void sell(int amount, String symbol) {
        long noOfShares = 0;

        for (CompanyShares companyShare : companyShares) {
            if (companyShare.getStockSymbol().equals(symbol)) {
                noOfShares = companyShare.getNoOfShares();
            }
        }

        if (noOfShares == 0 || amount > noOfShares) {
            System.out.println("Insufficient Shares");
        }
        else {
            CompanyShares selectedShare = null;
            for (CompanyShares companyShare : companyShares) {
                if (companyShare.getStockSymbol().equals(symbol)) {
                    selectedShare = companyShare;
                    companyShares.remove(companyShare);
                    break;
                }
            }

            if (selectedShare != null) {
                update(symbol, amount, selectedShare, Transaction.SELL);
            }
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(String filename) {
		JSONArray compShares = new JSONArray();
        for (CompanyShares companyShare : companyShares) {
            String stockSymbol = companyShare.getStockSymbol();
            long numberOfShares = companyShare.getNoOfShares();
            JSONArray transactions = new JSONArray();
            for (Transaction transaction : companyShare.getList()) {
                JSONObject transactionObject = new JSONObject();
                transactionObject.put("DateTime", transaction.getDateTime().toString());
                transactionObject.put("noOfShares", transaction.getNoOfShares());
                transactionObject.put("State", transaction.getState());
                transactions.add(transactionObject);
            }
            JSONObject object = new JSONObject();
            object.put("stockSymbol", stockSymbol);
            object.put("noOfShares", numberOfShares);
            object.put("transactions", transactions);
            compShares.add(object);
       }

        JSONObject object2 = new JSONObject();
        object2.put("companyShares", compShares);

       try {
           FileWriter writer = new FileWriter(filename);
           writer.write(object2.toJSONString());
           writer.flush();
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }       
	}

	@Override
	public void printReport() {
		System.out.println("Stock Report");
        System.out.println("Holding Shares\n");
        for (CompanyShares companyShare : companyShares) {
            System.out.println("Share Symbol : " + companyShare.getStockSymbol());
            System.out.println("Number of Shares Holding : " + companyShare.getNoOfShares());
            System.out.println("Value of each share : " + valueof(companyShare)/companyShare.getNoOfShares());
            System.out.println("Total Share Value : " + valueof(companyShare));
            System.out.println();
        }
        System.out.println("Total Value : " + valueOf());
	}

	
}
