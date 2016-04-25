package components;

import java.util.Queue;
import java.util.Random;

import ai.HandEvaluator;

/**
 * Class that represents the deck of cards and actions performed on it
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class Deck {

	private Queue<Card> currentDeck;
	private Card[] playerHand = new Card[2];
	private Card[] computerHand = new Card[2];
	private Card[] flop = new Card[3];
	private Card turn;
	private Card river;

	/**
	 * Constuctor will seed deck and shuffle the contents
	 */
	public Deck() {
		currentDeck = DeckSeed.seedDeck();
		shuffle();
	}

	/**
	 * deals cards to each of the players
	 */
	public void deal() {
		computerHand[0] = currentDeck.remove();
		computerHand[1] = currentDeck.remove();
		playerHand[0] = currentDeck.remove();
		playerHand[1] = currentDeck.remove();
	}

	/**
	 * deals cards to the flop
	 * @return flop
	 */
	public Card[] dealFlop() {
		flop[0] = currentDeck.remove();
		flop[1] = currentDeck.remove();
		flop[2] = currentDeck.remove();
		return flop;
	}

	/**
	 * deals the turn card
	 * @return turn
	 */
	public Card dealTurn() {
		turn = currentDeck.remove();
		return turn;
	}

	/**
	 * deals the river card
	 * @return river
	 */
	public Card dealRiver() {
		river = currentDeck.remove();
		return river;
	}

	/**
	 * Collects the cards off of the table and returns them to the deck, must happen after cards are dealt
	 */
	public void collect() {
		currentDeck.add(computerHand[0]);
		currentDeck.add(computerHand[1]);
		currentDeck.add(playerHand[0]);
		currentDeck.add(playerHand[1]);
		computerHand = null;
		playerHand = null;
		if(flop != null){
			currentDeck.add(flop[0]);
			currentDeck.add(flop[1]);
			currentDeck.add(flop[2]);
			flop = null;
		}
		if(turn != null){
			currentDeck.add(turn);
			turn = null;
		}
		if(river != null){
			currentDeck.add(river);
			river = null;
		}
		shuffle();
	}

	/**
	 * returns playerHand
	 * 
	 * @return Card[]
	 */
	public Card[] getPlayerHand() {
		return playerHand;
	}

	/**
	 * returns computer hand
	 * 
	 * @return Card[]
	 */
	public Card[] getComputerHand() {
		return computerHand;
	}

	/**
	 * evaluates the player hand against the computer and returns a boolean
	 * based on the winner
	 * 
	 * @return true for player, false for computer
	 */
	public boolean evaluateHands() {
		return HandEvaluator.determineWinner(playerHand, computerHand, flop, turn, river);
	}

	/**
	 * takes current deck and randomizes the contents
	 */
	private void shuffle() {
		Card[] tempDeck = new Card[52];
		Random rand = new Random();
		for(int i = 0; i < 52; i++){
			tempDeck[i] = currentDeck.remove();
		}
		for(int i = 0; i < 52; i++){
			int position = rand.nextInt(52);
			Card temp = tempDeck[i];
			tempDeck[i] = tempDeck[position];
			tempDeck[position] = temp;
		}
		for(int i = 0; i < 52; i++){
			currentDeck.add(tempDeck[i]);
		}
	}

}
