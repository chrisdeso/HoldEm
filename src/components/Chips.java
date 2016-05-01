package components;

import gui.Application;

/**
 * Class representing chips and the actions performed with them
 * 
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class Chips {

	private final static int BIG = 10;
	private final static int SMALL = 5;

	private static int playerChips;
	private static int computerChips;
	private static int pot;
	private static int raiseAmount;

	public Chips(){
		
	}
	
	/**
	 * Adds small blind to pot
	 * @param player -true, computer -false
	 * @return chips
	 */
	public static boolean smallBlind(boolean player) {
		if (player) {
			playerChips -= SMALL;
			pot += SMALL;
			return stakes(true, playerChips);
		} else {
			computerChips -= SMALL;
			pot += SMALL;
			return stakes(false, computerChips);
		}
	}

	/**
	 * Adds big blind to pot
	 * @param player - true, computer false
	 * @return chips
	 */
	public static boolean bigBlind(boolean player) {
		if(player){
			playerChips -= BIG;
			pot += BIG;
			return stakes(true, playerChips);
		} else {
			computerChips -= BIG;
			pot += BIG;
			return stakes(false, computerChips);
		}
	}

	/**
	 * Adds given amount to pot from chips
	 * @param player -true, computer false
	 * @param amount
	 * @return chips
	 */
	public static boolean bet(boolean player, int amount) {
		raiseAmount = amount;
		if(player){
			playerChips -= amount;
			pot += amount;
			return stakes(true, playerChips);
		} else {
			computerChips -= amount;
			pot += amount;
			return stakes(false, computerChips);
		}
	}

	/**
	 * Matches the bet of the player who raised
	 * @param player -true, computer -false
	 * @return chips
	 */
	public static boolean check(boolean player) {
		if(player){
			playerChips -= raiseAmount;
			pot += raiseAmount;
			raiseAmount = 0;
			return stakes(true, playerChips);
		} else {
			computerChips -= raiseAmount;
			pot += raiseAmount;
			raiseAmount = 0;
			return stakes(false, computerChips);
		}
	}

	/**
	 * Player folds and chips are give to other player
	 * @param player
	 * @return chips
	 */
	public static int fold(boolean player) {
		if(player){
			computerChips += pot;
			pot = 0;
			displayChips();
			return computerChips;
		} else {
			playerChips += pot;
			pot = 0;
			displayChips();
			return playerChips;
		}
	}
	
	/**
	 * Winner receives the pot
	 * @param player
	 * @return chips
	 */
	public static int winnerChips(boolean player){
		if(player){
			playerChips += pot;
			pot = 0;
			displayChips();
			return playerChips;
		} else {
			computerChips += pot;
			pot = 0;
			displayChips();
			return computerChips;
		}
	}
	
	/**
	 * resets the amount for when a new game is started
	 */
	public static void reset(){
		playerChips = 500;
		computerChips = 500;
		pot = 0;
		raiseAmount = 0;
	}
	
	/**
	 * Determines if the player has enough to bet, and returns false if this is the last amount they can bet
	 * @param chips
	 * @return
	 */
	public static boolean stakes(boolean player, int chips){
		boolean allIn;
		if(chips == 5){
			if(player){
				pot += playerChips;
				playerChips = 0;
			} else {
				pot += computerChips;
				computerChips = 0;
			}
			allIn = true;
		} else if(chips == 0){
			allIn = true;
		} else {
			allIn = false;
		}
		displayChips();
		return allIn;
	}
	
	/**
	 * Change the display of chips on the gui
	 */
	private static void displayChips(){
		Application.table.currentPlayerChips(playerChips);
		Application.table.currentComputerChips(computerChips);
		// Application.table.currentPot(pot);
	}

	// Getters
	
	public static int getPlayerChips() {
		return playerChips;
	}

	public static int getComputerChips() {
		return computerChips;
	}
	
	public static int getRaiseAmount() {
		return raiseAmount;
	}
	
	public static int getPot() {
		return pot;
	}
}
