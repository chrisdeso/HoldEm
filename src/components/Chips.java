package components;

/**
 * Class representing chips and the actions performed with them
 * 
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class Chips {

	private final int BIG = 20;
	private final int SMALL = 10;

	private int playerChips;
	private int computerChips;
	private int pot;
	private int raiseAmount;

	/**
	 * Adds small blind to pot
	 * @param player -true, computer -false
	 * @return chips
	 */
	public int smallBlind(boolean player) {
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
	public int bigBlind(boolean player) {
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
	public int bet(boolean player, int amount) {
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
	public int check(boolean player) {
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
	public int fold(boolean player) {
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
	public int winnerChips(boolean player){
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

	// Getters
	
	public int getPlayerChips() {
		return playerChips;
	}

	public int getComputerChips() {
		return computerChips;
	}
	
	public int getRaiseAmount() {
		return raiseAmount;
	}
	
	public int getPot() {
		return pot;
	}
}
