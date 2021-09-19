package com.deckofcardsqueue;

import java.util.*;


public class DeckOfCardsQueue {
	public static void deckOfCardsQueue() {
		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.init();
		Queue<Player> player = new Queue<Player>();
		int numOfPlayers = 4;
		for(int i=0; i<numOfPlayers ; i++) {
			player.enQueue(new Player());
		}
		
		deckOfCards.shuffleCards(player);
		
		for(Node<Player> p: player) {
			p.getData().sortingRank();
		}
		deckOfCards.print(player);
	}
}
