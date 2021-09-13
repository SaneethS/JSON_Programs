package com.stockaccount;

/**
 * @author saneeths
 *class used to get date and time and history of tansaction
 */
public class Transaction {
	
	final static String BUY = "buy";
    final static String SELL = "sell";

    private String dateTime;
    private long noOfShares;
    private String state;

    Transaction() {}

    Transaction(String dateTime, long noOfShares, String state) {
        this.dateTime = dateTime;
        this.noOfShares = noOfShares;
        this.state = state;
    }

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public long getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(long noOfShares) {
		this.noOfShares = noOfShares;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
