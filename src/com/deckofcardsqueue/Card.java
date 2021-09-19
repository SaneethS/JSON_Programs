package com.deckofcardsqueue;

/**
 * @author saneeths
 *this is the class where the cards with their suits and ranks are chosen using 
 *getters and setters
 */
public class Card {
	private String suit;
	private String rank;
	
	public Card() {
		
	}

	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return rank+" of "+suit;
	}
	
	
}
