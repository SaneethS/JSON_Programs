package com.stockqueue;

import java.util.*;

/**this is the class used to get getter and setters for stocksymbol and noOfShares to
 * implement in other classes
 * @author saneeths
 *
 */
public class CompanyShares {
	private String stockSymbol;
	private long noOfShares;
	
	List<Transaction> list = new ArrayList<Transaction>();
	
	CompanyShares(){}
	
	CompanyShares(String stockSymbol){
		this.stockSymbol =stockSymbol;
		this.noOfShares = 0;
	}
	
	public void addTransaction(Transaction transaction) {
		list.add(transaction);
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public long getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(long noOfShares) {
		this.noOfShares = noOfShares;
	}

	public List<Transaction> getList() {
		return list;
	}

	public void setList(List<Transaction> list) {
		this.list = list;
	}
	
	
	
	
}
