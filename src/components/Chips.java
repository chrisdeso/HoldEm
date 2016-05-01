package components;

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

	/**
	 * Adds small blind to pot
	 * @param player -true, computer -false
	 * @return chips
	 */
	public static int smallBlind(boolean player) {
		if (player) {
			playerChips -= SMALL;
			pot += SMALL;
			return playerChips;
		} else {
			computerChips -= SMALL;
			pot += SMALL;
			return computerChips;
		}
	}

	/**
	 * Adds big blind to pot
	 * @param player - true, computer false
	 * @return chips
	 */
	public static int bigBlind(boolean player) {
		if(player){
			playerChips -= BIG;
			pot += BIG;
			return playerChips;
		} else {
			computerChips -= BIG;
			pot += BIG;
			return computerChips;
		}
	}

	/**
	 * Adds given amount to pot from chips
	 * @param player -true, computer false
	 * @param amount
	 * @return chips
	 */
	public static int bet(boolean player, int amount) {
		raiseAmount = amount;
		if(player){
			playerChips -= amount;
			pot += amount;
			return playerChips;
		} else {
			computerChips -= amount;
			pot += amount;
			return computerChips;
		}
	}

	/**
	 * Matches the bet of the player who raised
	 * @param player -true, computer -false
	 * @return chips
	 */
	public static int check(boolean player) {
		if(player){
			playerChips -= raiseAmount;
			pot += raiseAmount;
			raiseAmount = 0;
			return playerChips;
		} else {
			computerChips -= raiseAmount;
			pot += raiseAmount;
			raiseAmount = 0;
			return computerChips;
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
			return computerChips;
		} else {
			playerChips += pot;
			pot = 0;
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
			return playerChips;
		} else {
			computerChips += pot;
			pot = 0;
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
