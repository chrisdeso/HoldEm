package ai;
import java.util.Random;

import components.Card;
import components.Chips;
import components.Deck;

/**
 * Class that determines the logic behind the computer's actions
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class ComputerAI {
	
	private static int confidence;

	/**
	 * Uses logic to determine what to do for the turn 
	 * @return 4 for fold, 1 for check or call, 3 for raise
	 */
	public static int computerTurn(){
		if(Deck.getRiver() == null){
			if(Deck.getTurn() == null){
				if(Deck.getFlop() == null){
					preFlopDecision();
				} else {
					postFlopDecision();
				}
			} else {
				postTurnDecision();
			}
		} else {
			postRiverDecision();
		}
		int result = determineRisk();
		confidence = 0;
		return result;
	}
	
	/**
	 * determines score of current hand and adds confidence level
	 * @param score
	 */
	private static void handConfidence(int score){
		confidence += (score / 50);
	}
	
	/**
	 * adds to confidence level based on turn, with computer being more willing preflop, and then more cautious while investing further
	 * @param turn
	 */
	private static void turnConfidence(int turn){
		confidence += turn;
	}
	
	/**
	 * adds to confidence level based on chips, with computer being more ambitious with more chips, cautious with lower chips
	 * and pessimistic at very low levels
	 */
	//TODO/////////////////////This is based on if each player starts with 500 chips//////////////////////////////////
	private static void chipConfidence(){
		int chips = Chips.getComputerChips();
		if(chips < 100){
			confidence += 8;
		} else if (chips >= 100 && chips < 400){
			confidence += (chips / 100);
		} else if(chips >= 400){
			confidence += (5 + (chips * .005));
		}
	}
	
	/**
	 * Decides confidence level for the pre-flop phase
	 */
	private static void preFlopDecision(){
		handConfidence(HandEvaluator.determineScore(Deck.getComputerHand(), null, null, null));
		turnConfidence(5);
		chipConfidence();
	}
	
	/**
	 * Decides confidence level for the post-flop phase
	 */
	private static void postFlopDecision(){
		handConfidence(HandEvaluator.determineScore(Deck.getComputerHand(), Deck.getFlop(), null, null));
		turnConfidence(4);
		chipConfidence();
	}
	
	/**
	 * Decides confidence level for the post-turn phase
	 */
	private static void postTurnDecision(){
		handConfidence(HandEvaluator.determineScore(Deck.getComputerHand(), Deck.getFlop(), Deck.getTurn(), null));
		turnConfidence(7);
		chipConfidence();
	}
	
	/**
	 * Decides confidence level for the post-river phase
	 */
	private static void postRiverDecision(){
		handConfidence(HandEvaluator.determineScore(Deck.getComputerHand(), Deck.getFlop(), Deck.getTurn(), Deck.getRiver()));
		turnConfidence(10);
		chipConfidence();
	}
	
	/**
	 * Takes confidence level and determines result from it
	 * @return
	 */
	private static int determineRisk(){
		// will need to lower this amount if computer folds too often, raise if not enough
		if(confidence < 10){
			return 4;
			// will need to lower this amount if computer doesn't raise enough, raise if too often
		} else if(confidence > 20){
			return 3;
		} else {
			return 1;
		}
	}
	
}
