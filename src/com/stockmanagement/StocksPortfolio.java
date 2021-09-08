package com.stockmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StocksPortfolio {
	List<Stocks> list = new ArrayList<Stocks>();
	Map<String,Double> map = new HashMap<String,Double>();
	public List<Stocks> getList() {
		return list;
	}
	public void setList(List<Stocks> list) {
		this.list = list;
	}
	public Map<String, Double> getMap() {
		return map;
	}
	public void setMap(Map<String, Double> map) {
		this.map = map;
	}
	
	public void stockValue() {
		for(Stocks s: list) {
			double value = s.getSharePrice() * s.getNoOfShares();
			System.out.println("Stock Name ::"+s.getStockName()+" , Stock Value ::"+value);
			map.put(s.getStockName(), value);
		}
	}
	
	public void totalStockValue() {
		double totalValue = 0.0;
		Collection<Double> valueList = map.values();
		for(Double i: valueList) {
			totalValue+=i;
		}
		System.out.println("Total Value of Stocks :: "+totalValue);
	}
}
