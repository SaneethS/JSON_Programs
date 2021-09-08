package com.stockmanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockManagementSystem {
	@SuppressWarnings("unchecked")
	public static void stockManagementSystem() {
		Reader reader;
		StocksPortfolio stockPortfolio = new StocksPortfolio();
		List<Stocks> list = stockPortfolio.getList();
		try {
			reader = new FileReader("data/stocks.json");
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(reader);
			JSONArray array = (JSONArray) obj.get("stocks");
			array.forEach((item)->{
				Stocks stock = new Stocks();
				JSONObject object = (JSONObject) item;
				String stockName = (String) object.get("stockName");
				long noOfShares = (long) object.get("noOfShares");
				double sharePrice = (double) object.get("sharePrice");
				stock.setStockName(stockName);
				stock.setNoOfShares(noOfShares);
				stock.setSharePrice(sharePrice);
				list.add(stock);
			}); 
			stockPortfolio.setList(list);	
			stockPortfolio.stockValue();
			System.out.println();
			stockPortfolio.totalStockValue();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
