package com.stockqueue;

public interface StockInterface {
	double valueOf();
	
	void buy(int amount,String symbol);
	
	void sell(int amount,String symbol);
	
	void save(String filename);
	
	void printReport();
}
