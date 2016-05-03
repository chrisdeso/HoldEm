package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import components.Card;
import components.Deck;

/**
 * Takes into account all scenarios to devise a winner based on cards given
 * 
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class HandEvaluator {

	private final static int HIGH = 100;
	private final static int ONEPAIR = 200;
	private final static int TWOPAIR = 300;
	private final static int THREEKIND = 400;
	private final static int STRAIGHT = 500;
	private final static int FLUSH = 600;
	private final static int FULLHOUSE = 700;
	private final static int FOURKIND = 800;
	private final static int STRAIGHTFLUSH = 900;
	private final static int ROYALFLUSH = 1000;

	private static Card[] cards = new Card[7];
	private static List<Integer> values = new ArrayList<Integer>();
	private static int hearts;
	private static int diamonds;
	private static int clubs;
	private static int spades;
	private static int score;
	private static int high;

	/**
	 * Main method is flow of logic, does a suit check, and then if necessary,
	 * gathers values to compare hands and returns a Score
	 * 
	 * @param hand
	 * @param flop
	 * @param turn
	 * @param river
	 * @return total score
	 */
	public static int determineScore(Card[] hand, Card[] flop, Card turn, Card river) {
		int total;
		cards[0] = hand[0];
		cards[1] = hand[1];
		if(flop != null){
			cards[2] = flop[0];
			cards[3] = flop[1];
			cards[4] = flop[2];
		}
		if(turn != null){
			cards[5] = turn;
		}
		if(river != null){
			cards[6] = river;
		}
		countSuits();
		flushHand();
		if (score < 900) {
			stackValues();
			if (!fourOfAKindCheck()) {
				if (!fullHouseCheck()) {
					if (score < 600) {
						if (!straightCheck()) {
							if (!threeOfAKindCheck()) {
								if (!pairCheck()) {
									highCard();
								}
							}
						}
					}
				}
			}
		}
		total = score + high;
		reset();
		return total;
	}

	/**
	 * Counts suits of table cards
	 */
	private static void countSuits() {
		for (int i = 0; i <= 6; i++) {
			if(cards[i] != null){
				if (cards[i].getSuit() == 1) {
					hearts++;
				} else if (cards[i].getSuit() == 2) {
					diamonds++;
				} else if (cards[i].getSuit() == 3) {
					clubs++;
				} else {
					spades++;
				}
			}
		}
	}

	/**
	 * Determines if player has a flush
	 * 
	 */
	private static void flushHand() {
		if (hearts >= 5) {
			score = FLUSH;
			Deck.setHandResult("Flush");
			high = straightFlushHand(1);
		} else if (diamonds >= 5) {
			score = FLUSH;
			Deck.setHandResult("Flush");
			high = straightFlushHand(2);
		} else if (clubs >= 5) {
			score = FLUSH;
			Deck.setHandResult("Flush");
			high = straightFlushHand(3);
		} else if (spades >= 5) {
			score = FLUSH;
			Deck.setHandResult("Flush");
			high = straightFlushHand(4);
		}
	}

	/**
	 * Checks to see if flush is straight or royal, also returns highest value
	 * of flush in case of tie breaker
	 * 
	 * @param suit
	 * @return
	 */
	private static int straightFlushHand(int suit) {
		int high = 0;
		List<Integer> tempCards = new ArrayList<Integer>();
		for (int i = 0; i <= 6; i++) {
			if (cards[i] != null && cards[i].getSuit() == suit) {
				tempCards.add(cards[i].getValue());
			}
		}
		Collections.sort(tempCards);
		boolean straight = false;
		while (!straight && tempCards.size() >= 5) {
			if (straightCompare(tempCards)) {
				straight = true;
			} else {
				tempCards.remove(0);
			}
		}
		if (straight) {
			if (tempCards.get(tempCards.size() - 1) == 14) {
				score = ROYALFLUSH;
				Deck.setHandResult("Royal Flush");
			} else {
				score = STRAIGHTFLUSH;
				Deck.setHandResult("Straight Flush");
				high = tempCards.get(tempCards.size() - 1);
			}
		} else {
			high = tempCards.get(tempCards.size() - 1);
		}
		return high;
	}

	/**
	 * This method is to account for when there may be more than 5 of the given
	 * suit and there is a gap between
	 * 
	 * @param list
	 * @return
	 */
	private static boolean straightCompare(List<Integer> list) {
		int current = list.get(0);
		boolean straight = true;
		for (int i = 1; i < list.size(); i++) {
			if (current + 1 == list.get(i)) {
				current = list.get(i);
			} else {
				straight = false;
				break;
			}
		}
		return straight;
	}

	/**
	 * Creates an arrayList containing counts of each value;
	 */
	private static void stackValues() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 13; i++) {
			list.add(0);
		}
		for (int i = 0; i <= 6; i++) {
			if(cards[i] != null){
				list.set(cards[i].getValue() - 2, list.get(cards[i].getValue() - 2) + 1);
			}
		}
		values = list;
	}

	/**
	 * Checks if player has a four of a kind and returns true
	 * 
	 * @return
	 */
	private static boolean fourOfAKindCheck() {
		if (values.contains(4)) {
			score = FOURKIND;
			Deck.setHandResult("Four Of A Kind");
			high = values.lastIndexOf(4);
			return true;
		}
		return false;
	}

	/**
	 * Checks if player has a full house and returns true
	 * 
	 * @return
	 */
	private static boolean fullHouseCheck() {
		if (values.contains(3) && values.contains(2)) {
			score = FULLHOUSE;
			Deck.setHandResult("Full House");
			high = values.lastIndexOf(3) + values.lastIndexOf(2);
			return true;
		}
		return false;
	}

	/**
	 * Checks if player has a straight
	 * 
	 * @return
	 */
	private static boolean straightCheck() {
		int count = 0, lastIndex = 0, highCard = 0;
		for(int i = values.size() - 1; i >= 0; i--){
			if(values.get(i) > 0){
				lastIndex = i;
				break;
			}
		}
		for (int i = lastIndex; i >= 0; i--) {
			if (values.get(i) > 0) {
				count++;
				highCard = lastIndex + 2;
			} else if(values.get(i) == 0 && count < 5) {
				count = 0;
				lastIndex = i - 1;
			}
		}
		if (count >= 5) {
			score = STRAIGHT;
			Deck.setHandResult("Straight");
			high = highCard;
			return true;
		}
		return false;
	}

	/**
	 * Checks to see if player has 3 of a kind
	 * 
	 * @return
	 */
	private static boolean threeOfAKindCheck() {
		if (values.contains(3)) {
			score = THREEKIND;
			Deck.setHandResult("Three Of A Kind");
			high = values.lastIndexOf(3);
			return true;
		}
		return false;
	}

	/**
	 * Checks to see if player has 1 or 2 pairs
	 * 
	 * @return
	 */
	private static boolean pairCheck() {
			if (values.contains(2)) {
				score = ONEPAIR;
				Deck.setHandResult("One Pair");
				high = values.lastIndexOf(2);
				values.remove(values.lastIndexOf(2));
				if (values.contains(2)) {
					score = TWOPAIR;
					Deck.setHandResult("Two Pair");
					high += values.lastIndexOf(2);
				}
				return true;
			}
		return false;
	}

	/**
	 * If no other hands are determined, high card is found for player
	 * 
	 * @return
	 */
	private static void highCard() {
			for (int i = values.size() - 1; i >= 0; i--) {
				if (values.get(i) != 0) {
					score = HIGH;
					Deck.setHandResult("High Card");
					high = values.lastIndexOf(1);
					break;
				}
			}
	}

	/**
	 * resets the scoring mechanics to null for evaluating another hand
	 */
	private static void reset() {
		cards = new Card[7];
		values.clear();
		score = 0;
		high = 0;
		hearts = 0;
		diamonds = 0;
		clubs = 0;
		spades = 0;
	}

}
