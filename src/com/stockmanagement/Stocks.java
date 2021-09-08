package com.stockmanagement;

public class Stocks {
	private String stockName;
	private long noOfShares;
	private double sharePrice;
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public long getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(long noOfShares) {
		this.noOfShares = noOfShares;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}
	@Override
	public String toString() {
		return "Stocks [stockName=" + stockName + ", noOfShares=" + noOfShares + ", sharePrice=" + sharePrice + "]";
	}
	
	
	
}
