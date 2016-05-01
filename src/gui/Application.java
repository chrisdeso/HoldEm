package gui;

import ai.ComputerAI;
import components.Chips;
import components.Deck;

/**
 * Main application class, runs the game
 * 
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class Application {

	private static boolean playerTurn;
	private static boolean gameOver;
	private static final String DOWN = "resources/images/_Back.png";
	private static final int FIXEDBET = 10;
	private static UserView table;

	/**
	 * main application of game
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		table = new UserView();
		table.display("Welcome to Texas Hold'Em");
		Deck deck = new Deck();
		startGame();
	}

	/**
	 * starts a new game with new chips for each player
	 * @throws InterruptedException
	 */
	private static void startGame() throws InterruptedException {
		Chips.reset();
		cardsDown();
		while (!gameOver) {
			Deck.collect();
			preFlop();
		}
	}

	/**
	 * simulates the preflop phase, two differenct parts based on whose turn it is
	 * @throws InterruptedException
	 */
	private static void preFlop() throws InterruptedException {
		boolean fold = false;
		int playerDecision, computerDecision;
		if (playerTurn) {
			table.display("Computer plays the big blind\nPlayer plays the small blind\nPlayer goes first");
			Chips.bigBlind(false);
			Chips.smallBlind(true);
			Deck.deal();
			table.changePlayerHand1(Deck.getPlayerHand()[0].getLocation());
			table.changePlayerHand2(Deck.getPlayerHand()[1].getLocation());
			playerDecision = playerAction();
			if (playerDecision == 1 || playerDecision == 2) {
				Chips.smallBlind(true);
				table.display("Player has called");
			} else if (playerDecision == 3) {
				Chips.smallBlind(true);
				Chips.bet(true, FIXEDBET);
				table.display("Player has called and raised amount");
			} else if (playerDecision == 4) {
				fold = true;
			}
			if (fold) {
				foldResult(true);
			} else {
				Thread.sleep(2000);
				table.display("Computer's turn");
				Thread.sleep(2000);
				computerDecision = ComputerAI.computerTurn();
				Thread.sleep(2000);
				if(computerDecision == 4){
					foldResult(false);
				} else {
					if(playerDecision == 3){
						table.display("Computer has called");
						Chips.bet(false, FIXEDBET);
					} else {
						table.display("Computer checks");
					}
					Thread.sleep(2000);
					postFlop();
				}
			}
		} else {
			table.display("Player plays the big blind\nComputer plays the small blind\nComputer goes first");
			Chips.bigBlind(true);
			Chips.smallBlind(false);
			Deck.deal();
			table.changePlayerHand1(Deck.getPlayerHand()[0].getLocation());
			table.changePlayerHand2(Deck.getPlayerHand()[1].getLocation());
			computerDecision = ComputerAI.computerTurn();
			Thread.sleep(2000);
			if(computerDecision == 2){
				Chips.smallBlind(false);
				table.display("Computer has called");
			} else if(computerDecision == 3){
				Chips.smallBlind(false);
				Chips.bet(false, FIXEDBET);
				table.display("Computer has called and raised amount");
			} else if(computerDecision == 4){
				fold = true;
			}
			Thread.sleep(2000);
			if(fold){
				foldResult(false);
			} else {
				table.display("Player's turn");
				playerDecision = playerAction();
				if(playerDecision == 4){
					foldResult(true);
				} else {
					if(computerDecision == 3){
						table.display("Player has called");
						Chips.bet(true, FIXEDBET);
					} else {
						table.display("Player checks");
					}
					Thread.sleep(2000);
					postFlop();
				}
			}
		}
	}

	private static void postFlop() {

	}

	private static void postTurn() {

	}

	private static void postRiver() {

	}

	/**
	 * Puts all the cards down
	 */
	private static void cardsDown() {
		table.changeFlop1(DOWN);
		table.changeFlop2(DOWN);
		table.changeFlop3(DOWN);
		table.changeTurn(DOWN);
		table.changeRiver(DOWN);
		table.changeAiHand1(DOWN);
		table.changeAiHand2(DOWN);
		table.changePlayerHand1(DOWN);
		table.changePlayerHand2(DOWN);
	}

	/**
	 * loop that waits until player has selected an action
	 * @return
	 */
	private static int playerAction() {
		table.setPlayerActionPerformed(false);
		while (true) {
			if (table.getPlayerActionPerformed()) {
				break;
			}
		}
		return table.getPlayerDecision();
	}
	
	/**
	 * When someone folds, this methods goes about the actions involved in ending the round, and giving the appropriate 
	 * player the pot.
	 * @param player
	 * @throws InterruptedException
	 */
	private static void foldResult(boolean player) throws InterruptedException{
		if(player){
			table.display("Player has decided to fold");
			Chips.fold(true);
		} else {
			table.display("Computer has decided to fold");
			Chips.fold(false);
		}
		Thread.sleep(2000);
		table.display("Starting new round");
		cardsDown();
		playerTurn = !playerTurn;
		Thread.sleep(2000);
	}

}
