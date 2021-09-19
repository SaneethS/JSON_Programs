package com.deckofcardsqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author saneeths
 *this is the class where the cards are shuffled and distributed among players
 */
public class DeckOfCards {
	Random random = new Random();
	List<Integer> cardNo = new ArrayList<Integer>();
	String[] suit = {"Clubs","Diamonds", "Hearts", "Spades"};
	String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King", "Ace"};
	Card cards[] =new Card[52];
	
	
	/**
	 * this method is used to initialize the cards
	 */
	public void init() {
		for(int i=0; i<52; i++) {
			cards[i] = new Card();
		}
		int count = 0;
		for(int i=0 ; i<4 ; i++ )
			for(int j=0 ; j<13 ; j++) {
				cards[count].setSuit(suit[i]);
				cards[count].setRank(rank[j]);
				count++;
			}
	}
	
	/**this method is used to generate some random number for ranks 
	 * @return random number
	 */
	public int randomNumber() {
		int randomNum = random.nextInt(52);
		while(cardNo.contains(randomNum)) {
			randomNum = random.nextInt(52);
		}
		cardNo.add(randomNum);
		return randomNum;
	}
	
	/**this is the method where shuffling of card takes place
	 * @param players
	 */
	public void shuffleCards(Queue<Player> players){
		for(Node<Player> player : players) {
			for(int i=0 ; i<9 ; i++) {
				int randomNum = randomNumber();
				player.getData().addCard(cards[randomNum]);
			}
		}
			
	}
	
	/**this method is used to print which and all cards are assigned to which
	 * players
	 * @param players
	 */
	public void print(Queue<Player> players) {
		int count = 1;
		for(Node<Player> player : players) {
			System.out.print("Player "+count+" : ");
			for (Node<Card> card : player.getData().getDeckOfCards()) {
				System.out.print(card.getData() + " ; ");
			}
			count++;
			System.out.println();
		}
	}
}
