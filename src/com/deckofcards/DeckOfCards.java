package com.deckofcards;

import java.util.*;


public class DeckOfCards {
	Random random = new Random();
	List<Integer> cardNo = new ArrayList<Integer>();
	String[] suit = {"Clubs","Diamonds", "Hearts", "Spades"};
	String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King", "Ace"};
	String[] cards = new String[52];
	
	public static void deckOfCards() {
		DeckOfCards deck = new DeckOfCards();
		
		deck.init();
		
		String[][] players = deck.shuffleCards();
		
		deck.twoDimensionalArray(players);
	}
	
	public void init() {
		int count = 0;
		for(int i=0 ; i<4 ; i++ )
			for(int j=0 ; j<13 ; j++) {
				cards[count] = suit[i]+" "+rank[j];
				count++;
			}
	}
	
	public int randomNumber() {
		int randomNum = random.nextInt(52);
		while(cardNo.contains(randomNum)) {
			randomNum = random.nextInt(52);
		}
		cardNo.add(randomNum);
		return randomNum;
	}
	
	public String[][] shuffleCards(){
		String[][] players = new String[4][9];
		for(int i=0 ; i<4 ; i++)
			for(int j=0 ; j<9 ; j++) {
				int randomNum = randomNumber();
				players[i][j] = cards[randomNum];
			}
		return players;
	}
	
	public void twoDimensionalArray(String[][] players) {
		for(int i=0 ; i<4 ; i++) {
			System.out.print("Player"+(i+1)+" = ");
			for(int j=0 ; j<9 ; j++) {
				System.out.print(players[i][j]+" ; ");
			}
			System.out.println();
		}
	}
}
