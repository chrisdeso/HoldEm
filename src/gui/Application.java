package gui;

import java.io.IOException;

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

	private static boolean playerTurn = true;
	private static boolean gameOver;
	private static final String DOWN = "resources/images/_Back.png";
	private static final int FIXEDBET = 10;
	public static PokerGui table;

	/**
	 * main application of game
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		table = new PokerGui();
		table.display("Welcome to Texas Hold'Em");
		Deck.seed();
		startGame();
		System.exit(0);
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
		if(Chips.getPlayerChips() == 0){
			table.display("Computer has won the game, thanks for playing!");
		} else {
			table.display("Player has won the game! Thanks for playing!");
		}
		Thread.sleep(10000);
	}

	/**
	 * simulates the preflop phase
	 * @throws InterruptedException
	 */
	private static void preFlop() throws InterruptedException {
		int result = 0;
		boolean playerStakes;
		boolean computerStakes;
		int playerChips = Chips.getPlayerChips();
		int computerChips = Chips.getComputerChips();
		Thread.sleep(2000);
		Deck.deal();
		table.changeCard(1, Deck.getPlayerHand()[0].getLocation());
		table.changeCard(2, Deck.getPlayerHand()[1].getLocation());
		if (playerTurn) {
			table.display("Computer plays the big blind\nPlayer plays the small blind");
			Thread.sleep(2000);
			table.display("Player goes first");
			computerStakes = Chips.bigBlind(false);
			playerStakes = Chips.smallBlind(true);
			if(playerStakes){
				result = allIn(true, 1, playerChips);
			} else if(computerStakes){
				result = allIn(false, 1, computerChips);
			} else {
				result = bettingRound1(true, false, 1);
			}
		} else {
			table.display("Player plays the big blind\nComputer plays the small blind");
			Thread.sleep(2000);
			table.display("Computer goes first");
			playerStakes = Chips.bigBlind(true);
			computerStakes = Chips.smallBlind(false);
			if(playerStakes){
				result = allIn(true, 1, playerChips);
			} else if(computerStakes){
				result = allIn(false, 1, computerChips);
			} else {
				result = bettingRound1(false, false, 1);
			}
		}
		if(result == 4){
			foldResult(true);
		} else if(result == 5){
			foldResult(false);
		} else if(result == 7){
			gameOver = true;
		} else if(result == 8){
			table.display("The game continues");
		} else {
			postFlop();
		}
	}

	/**
	 * simulates the postflop phase
	 * @throws InterruptedException 
	 */
	private static void postFlop() throws InterruptedException {
		int result;
		Thread.sleep(2000);
		Deck.dealFlop();
		table.display("Dealing Flop");
		table.changeCard(5, Deck.getFlop()[0].getLocation());
		table.changeCard(6, Deck.getFlop()[1].getLocation());
		table.changeCard(7, Deck.getFlop()[2].getLocation());
		Thread.sleep(2000);
		if(playerTurn){
			result = bettingRound2(true, false, 1, 2);
		} else {
			result = bettingRound2(false, false, 1, 2);
		}
		if(result == 4){
			foldResult(true);
		} else if(result == 5){
			foldResult(false);
		} else if(result == 7){
			gameOver = true;
		} else if(result == 8){
			table.display("The game continues");
		} else {
			postTurn();
		}
	}

	/**
	 * simulates the postTurn phase
	 * @throws InterruptedException 
	 */
	private static void postTurn() throws InterruptedException {
		int result;
		Thread.sleep(2000);
		Deck.dealTurn();
		table.display("Dealing the Turn");
		table.changeCard(8, Deck.getTurn().getLocation());
		Thread.sleep(2000);
		if(playerTurn){
			result = bettingRound2(true, false, 1, 3);
		} else {
			result = bettingRound2(false, false, 1, 3);
		}
		if(result == 4){
			foldResult(true);
		} else if(result == 5){
			foldResult(false);
		} else if(result == 7){
			gameOver = true;
		} else if(result == 8){
			table.display("The game continues");
		} else {
			postRiver();
		}
	}

	/**
	 * simulates the postRiver phase
	 * @throws InterruptedException 
	 */
	private static void postRiver() throws InterruptedException {
		int result;
		Thread.sleep(2000);
		Deck.dealRiver();
		table.display("Dealing the river");
		table.changeCard(9, Deck.getRiver().getLocation());
		Thread.sleep(2000);
		if(playerTurn){
			result = bettingRound2(true, false, 1, 4);
		} else {
			result = bettingRound2(false, false, 1, 4);
		}
		if(result == 4){
			foldResult(true);
		} else if(result == 5){
			foldResult(false);
		} else if(result == 7){
			gameOver = true;
		} else if(result == 8){
			table.display("The game continues");
		} else {
			roundPhase();
		}
	}
	
	/**
	 * Simulates the evaluation of a hand and declares the winner of the round
	 * @throws InterruptedException
	 */
	private static void roundPhase() throws InterruptedException {
		boolean winner;
		Thread.sleep(2000);
		table.display("Let's see who won the round");
		table.changeCard(3, Deck.getComputerHand()[0].getLocation());
		table.changeCard(4, Deck.getComputerHand()[1].getLocation());
		Thread.sleep(2000);
		winner = Deck.evaluateHands();
		if(winner){
			table.display("Player wins the round with a " + Deck.getWinningHand());
			Chips.winnerChips(true);
		} else {
			table.display("Computer wins the round with a " + Deck.getWinningHand());
			Chips.winnerChips(false);
		}
		Thread.sleep(5000);
		table.display("Starting new round");
		cardsDown();
		playerTurn = !playerTurn;
		Thread.sleep(2000);
	}
	
	/**
	 * simulates the last round of the game when one of the players has bet all of their chips
	 * @return returns 7 game is over, 8 if game continues, 4 or 5 if opponent folds
	 * @throws InterruptedException
	 */
	private static int allIn(boolean player, int stage, int amount) throws InterruptedException {
		boolean winner;
		int decision;
		int result = 0;
		Thread.sleep(2000);
		if(player){
			table.display("Player has gone all in");
		} else {
			table.display("Computer has gone all in");
		}
		if(stage == 1){
			Thread.sleep(2000);
			table.display("Turning the flop");
			Deck.dealFlop();
			table.changeCard(5, Deck.getFlop()[0].getLocation());
			table.changeCard(6, Deck.getFlop()[1].getLocation());
			table.changeCard(7, Deck.getFlop()[2].getLocation());
			stage = 2;
		} 
		if(stage == 2){
			Thread.sleep(2000);
			table.display("Turning the turn");
			Deck.dealTurn();
			table.changeCard(8, Deck.getTurn().getLocation());
			stage = 3;
		}
		if(stage == 3){
			Thread.sleep(2000);
			table.display("Turning the river");
			Deck.dealRiver();
			table.changeCard(9, Deck.getRiver().getLocation());
			stage = 4;
		}
		if(stage == 4){
			Thread.sleep(2000);
			if(player){
				decision = ComputerAI.computerTurn();
			} else {
				table.display("What will player do?");
				decision = playerAction();
			}
			if(decision == 4){
				if(!player){
					table.display("Player has decided to fold");
					result = 4;
				} else {
					Thread.sleep(2000);
					table.display("Computer has decided to fold");
					result = 5;
				}
			} else {
				if(player){
					Thread.sleep(2000);
					table.display("Computer has decided to call");
					if(amount > 0){
						Chips.bet(false, amount);
					}
				} else {
					table.display("Player has decided to call");
					if(amount > 0){
						Chips.bet(true, amount);
					}
				}
				Thread.sleep(2000);
				table.display("Let's see who won the round");
				table.changeCard(3, Deck.getComputerHand()[0].getLocation());
				table.changeCard(4, Deck.getComputerHand()[1].getLocation());
				Thread.sleep(2000);
				winner = Deck.evaluateHands();
				if(winner){
					table.display("Player wins the round with a " + Deck.getWinningHand());
					Chips.winnerChips(true);
				} else {
					table.display("Computer wins the round with a " + Deck.getWinningHand());
					Chips.winnerChips(false);
				}
				Thread.sleep(5000);
				if(((player && winner) || (!player && !winner)) && Chips.getPlayerChips() > 0 && Chips.getComputerChips() > 0){
					result = 8;
				} else {
					result = 7;
				}
			}
		}
		return result;
	}

	/**
	 * Puts all the cards down
	 */
	private static void cardsDown() {
		for(int i = 1; i <= 9; i++){
			table.changeCard(i, DOWN);
		}
	}

	/**
	 * loop that waits until player has selected an action
	 * @return
	 * @throws InterruptedException 
	 */
	private static int playerAction() throws InterruptedException {
		table.setPlayerActionPerformed(false);
		while (true) {
			if (table.getPlayerActionPerformed()) {
				break;
			} else {
				Thread.sleep(500);
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
		Thread.sleep(2000);
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
	
	/**
	 * Recursive method that simulates betting round between players, returns result of round
	 * This method is for the preFlop phase
	 * @param player
	 * @param raise
	 * @param stage
	 * @return result of round
	 * @throws InterruptedException
	 */
	private static int bettingRound1(boolean player, boolean raise, int stage) throws InterruptedException{
		int decision;
		boolean stakes;
		String who;
		int result = 0;
		int currentChips;
		if(player){
			who = "Player";
			currentChips = Chips.getPlayerChips();
			if(stage != 1){
				Thread.sleep(2000);
				table.display("Player's turn");
			}
			decision = playerAction();
		} else {
			decision = ComputerAI.computerTurn();
			who = "Computer";
			currentChips = Chips.getComputerChips();
			if(stage != 1){
				Thread.sleep(2000);
				table.display("Computer's turn");
			}
		}
		if(!player){
			Thread.sleep(2000);
		}
		if(decision == 4){
			if(player){
				return 4;
			} else {
				return 5;
			}
		} else {
			if(stage == 1){
				if(decision == 1 || decision == 2){
					table.display(who + " calls the big blind");
					stakes = Chips.smallBlind(player);
					if(stakes){
						result = allIn(player, 1, 0);
					} else {
						result = bettingRound1(!player, false, stage + 1);
					}
				} else if (decision == 3){
					table.display(who + " calls and raises the amount");
					stakes = Chips.smallBlind(player);
					if(!stakes){
						stakes = Chips.bet(player, FIXEDBET);
					} 
					if(stakes){
						result = allIn(player, 1, currentChips - 5);
					} else {
						result = bettingRound1(!player, true, stage + 1);
					}
				}
			} else if (stage == 2){
				if(raise){
					if(decision == 1 || decision == 2){
						table.display(who + " calls");
						stakes = Chips.bet(player, FIXEDBET);
						if(stakes){
							result = allIn(player, 1, 0);
						} else {
							result = 1;
						}
					} else if(decision == 3){
						table.display(who + " calls and raises the amount");
						stakes = Chips.bet(player, FIXEDBET);
						if(!stakes){
							stakes = Chips.bet(player, FIXEDBET);
						}
						if(stakes){
							result = allIn(player, 1, currentChips - 10);
						} else {
							result = bettingRound1(!player, true, stage + 1);
						}
					}
				} else {
					if(decision == 1 || decision == 2){
						table.display(who + " checks");
						result = 2;
					} else if(decision == 3){
						table.display(who + " raises the amount");
						stakes = Chips.bet(player, FIXEDBET);
						if(stakes){
							result = allIn(player, 1, currentChips);
						} else {
							result = bettingRound1(!player, true, stage + 1);
						}
					}
				}
			} else {
				if(decision == 1 || decision == 2){
					table.display(who + " calls");
					stakes = Chips.bet(player, FIXEDBET);
					if(stakes){
						result = allIn(player, 1, 0);
					} else {
						result = 1;
					}
				} else if(decision == 3){
					table.display(who + " calls and raises the amount");
					stakes = Chips.bet(player, FIXEDBET);
					if(!stakes){
						stakes = Chips.bet(player, FIXEDBET);
					}
					if(stakes){
						result = allIn(player, 1, currentChips - 10);
					} else {
						result = bettingRound1(!player, true, stage + 1);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * similar to betting method above, except this one doesn't deal with blinds
	 * @param player
	 * @param raise
	 * @param stage
	 * @return
	 * @throws InterruptedException
	 */
	private static int bettingRound2(boolean player, boolean raise, int stage, int phase) throws InterruptedException{
		int decision;
		boolean stakes;
		String who;
		int result = 0;
		int currentChips;
		if(stage > 1){
			Thread.sleep(2000);
		}
		if(player){
			table.display("Player's turn");
			decision = playerAction();
			who = "Player";
			currentChips = Chips.getPlayerChips();
		} else {
			table.display("Computer's turn");
			decision = ComputerAI.computerTurn();
			who = "Computer";
			currentChips = Chips.getComputerChips();
			Thread.sleep(2000);
		}
		if(decision == 4){
			if(player){
				return 4;
			} else {
				return 5;
			}
		} else {
			if(stage == 1){
				if(decision == 1 || decision == 2){
					table.display(who + " checks");
					result = bettingRound2(!player, false, stage + 1, phase);
				} else if(decision == 3){
					table.display(who + " raises");
					stakes = Chips.bet(player, FIXEDBET);
					if(stakes){
						result = allIn(player, phase, currentChips);
					} else {
						result = bettingRound2(!player, true, stage + 1, phase);
					}
				}
			} else {
				if(raise){
					if(decision == 1 || decision == 2){
						table.display(who + " calls");
						stakes = Chips.bet(player, FIXEDBET);
						if(stakes){
							result = allIn(player, phase, 0);
						} else {
							result = 1;
						}
					} else if(decision == 3){
						table.display(who + " calls and raises amount");
						stakes = Chips.bet(player, FIXEDBET);
						if(!stakes){
							stakes = Chips.bet(player, FIXEDBET);
						}
						if(stakes){
							result = allIn(player, phase, currentChips - 10);
						} else {
							result = bettingRound2(!player, true, stage + 1, phase);
						}
					}
				} else {
					if(decision == 1 || decision == 2){
						table.display(who + " checks");
						result = 1;
					} else if(decision == 3){
						table.display(who + " raises");
						stakes = Chips.bet(player, FIXEDBET);
						if(stakes){
							result = allIn(player, phase, currentChips);
						} else {
							result = bettingRound2(!player, true, stage + 1, phase);
						}
					}
				}
			}
		}
		return result;
	}

}
