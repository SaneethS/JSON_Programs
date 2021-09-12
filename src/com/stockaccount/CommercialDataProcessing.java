package com.stockaccount;

public class CommercialDataProcessing {
	public static void commercialDataProcessing(){
		StockAccount stock = new StockAccount("data/stockAccountResult.json");
		stock.init();
		
		stock.buy(100, "$M");
		stock.sell(20, "$M");
		
		stock.save("data/stockAccountResult.json");
		
		stock.printReport();
	}
}
