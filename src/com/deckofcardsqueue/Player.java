package com.deckofcardsqueue;

/**
 * @author saneeths
 *this class maintains the queue of cards for the players and add or enqueue the
 *cards for each player
 */
public class Player {
	private Queue<Card> deckOfCards;
	
	Player(){
		deckOfCards = new Queue<Card>();
	}

	public Queue<Card> getDeckOfCards() {
		return deckOfCards;
	}

	public void setDeckOfCards(Queue<Card> deckOfCards) {
		this.deckOfCards = deckOfCards;
	}
	
	public void addCard(Card card) {
		deckOfCards.enQueue(card);
	}
	
	/**this is the method where the String format suits are converted to integer 
	 * format
	 * @param value
	 * @return integer format of String cards
	 */
	public int getIntValue(String value) {
		int integer = 0;
		
		try {
			integer = Integer.parseInt(value);
		}catch(NumberFormatException e) {
			switch (value) {
			case "Jack":
				integer = 11;
				break;
			case "Queen":
				integer = 12;
				break;
			case "King":
				integer = 13;
				break;
			case "Ace":
				integer = 14;
				break;
			}
		}
		return integer;
	}
	
	/**
	 * this is the method where sorting of the rank of cards takes place
	 */
	public void sortingRank() {
		int sizeD = deckOfCards.size();
		for(int i=0 ; i<sizeD ; i++) {
			Node<Card> currVal = deckOfCards.getFront();
			Node<Card> nextVal = currVal.getNext();
			while(nextVal != null) {
				int firstVal = getIntValue(currVal.getData().getRank());
				int secondVal = getIntValue(nextVal.getData().getRank());
				if(firstVal > secondVal) {
					Card temp = currVal.getData();
					currVal.setData(nextVal.getData());
					nextVal.setData(temp);
				}
				currVal = nextVal;
				nextVal = nextVal.getNext();
			}
		}
	}
}
