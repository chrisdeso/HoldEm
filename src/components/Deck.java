package components;

import java.util.Deque;
import java.util.Random;

import ai.HandEvaluator;

/**
 * Class that represents the deck of cards and actions performed on it
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class Deck {

	private static Deque<Card> currentDeck;
	private static Card[] playerHand = new Card[2];
	private static Card[] computerHand = new Card[2];
	private static Card[] flop = new Card[3];
	private static Card turn;
	private static Card river;
	private static String winningHand;
	private static String handResult;

	public static void seed(){
		currentDeck = DeckSeed.seedDeck();
		shuffle();
	}

	/**
	 * deals cards to each of the players
	 */
	public static void deal() {
		computerHand[0] = currentDeck.removeFirst();
		computerHand[1] = currentDeck.removeFirst();
		playerHand[0] = currentDeck.removeFirst();
		playerHand[1] = currentDeck.removeFirst();
	}

	/**
	 * deals cards to the flop
	 * @return flop
	 */
	public static Card[] dealFlop() {
		flop[0] = currentDeck.removeFirst();
		flop[1] = currentDeck.removeFirst();
		flop[2] = currentDeck.removeFirst();
		return flop;
	}

	/**
	 * deals the turn card
	 * @return turn
	 */
	public static Card dealTurn() {
		turn = currentDeck.removeFirst();
		return turn;
	}

	/**
	 * deals the river card
	 * @return river
	 */
	public static Card dealRiver() {
		river = currentDeck.removeFirst();
		return river;
	}

	/**
	 * Collects the cards off of the table and returns them to the deck
	 */
	public static void collect() {
		if(playerHand[0] != null){
			currentDeck.addLast(computerHand[0]);
			currentDeck.addLast(computerHand[1]);
			currentDeck.addLast(playerHand[0]);
			currentDeck.addLast(playerHand[1]);
			computerHand[0] = null;
			computerHand[1] = null;
			playerHand[0] = null;
			playerHand[1] = null;
		}
		if(flop[0] != null){
			currentDeck.addLast(flop[0]);
			currentDeck.addLast(flop[1]);
			currentDeck.addLast(flop[2]);
			flop[0] = null;
			flop[1] = null;
			flop[2] = null;
		}
		if(turn != null){
			currentDeck.addLast(turn);
			turn = null;
		}
		if(river != null){
			currentDeck.addLast(river);
			river = null;
		}
		shuffle();
	}

	/**
	 * returns playerHand
	 * 
	 * @return Card[]
	 */
	public static Card[] getPlayerHand() {
		return playerHand;
	}

	/**
	 * returns computer hand
	 * 
	 * @return Card[]
	 */
	public static Card[] getComputerHand() {
		return computerHand;
	}

	/**
	 * evaluates the player hand against the computer and returns a boolean
	 * based on the winner
	 * 
	 * @return true for player, false for computer
	 */
	public static boolean evaluateHands() {
		String playerResult;
		String computerResult;
		int playerScore = HandEvaluator.determineScore(playerHand, flop, turn, river);
		playerResult = handResult;
		int computerScore = HandEvaluator.determineScore(computerHand, flop, turn, river);
		computerResult = handResult;
		if(playerScore > computerScore){
			winningHand = playerResult;
			return true;
		} else {
			winningHand = computerResult;
			return false;
		}
	}

	/**
	 * takes current deck and randomizes the contents
	 */
	private static void shuffle() {
		Card[] tempDeck = new Card[52];
		Random rand = new Random();
		for(int i = 0; i < 52; i++){
			tempDeck[i] = currentDeck.removeFirst();
		}
		for(int i = 0; i < 52; i++){
			int position = rand.nextInt(52);
			Card temp = tempDeck[i];
			tempDeck[i] = tempDeck[position];
			tempDeck[position] = temp;
		}
		for(int i = 0; i < 52; i++){
			currentDeck.addLast(tempDeck[i]);
		}
	}

	public static String getWinningHand() {
		return winningHand;
	}

	public static Card getRiver(){
		return river;
	}
	
	public static Card getTurn(){
		return turn;
	}
	
	public static Card[] getFlop(){
		return flop;
	}
	
	public static void setHandResult(String result){
		Deck.handResult = result;
	}

}
