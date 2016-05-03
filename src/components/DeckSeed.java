package components;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Seeds the deck of cards
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class DeckSeed {
	
	final static String PATH = "resources/images/";
	
	/**
	 * Assignes 52 cards with suits, values and image locations
	 * @return
	 */
	public static Deque<Card> seedDeck(){
		Deque<Card> deck = new LinkedList<Card>();
		deck.addLast(new Card(1, 2, PATH + "Hearts 2.png"));
		deck.addLast(new Card(1, 3, PATH + "Hearts 3.png"));
		deck.addLast(new Card(1, 4, PATH + "Hearts 4.png"));
		deck.addLast(new Card(1, 5, PATH + "Hearts 5.png"));
		deck.addLast(new Card(1, 6, PATH + "Hearts 6.png"));
		deck.addLast(new Card(1, 7, PATH + "Hearts 7.png"));
		deck.addLast(new Card(1, 8, PATH + "Hearts 8.png"));
		deck.addLast(new Card(1, 9, PATH + "Hearts 9.png"));
		deck.addLast(new Card(1, 10, PATH + "Hearts 10.png"));
		deck.addLast(new Card(1, 11, PATH + "Hearts 11.png"));
		deck.addLast(new Card(1, 12, PATH + "Hearts 12.png"));
		deck.addLast(new Card(1, 13, PATH + "Hearts 13.png"));
		deck.addLast(new Card(1, 14, PATH + "Hearts 1.png"));
		deck.addLast(new Card(2, 2, PATH + "Diamonds 2.png"));
		deck.addLast(new Card(2, 3, PATH + "Diamonds 3.png"));
		deck.addLast(new Card(2, 4, PATH + "Diamonds 4.png"));
		deck.addLast(new Card(2, 5, PATH + "Diamonds 5.png"));
		deck.addLast(new Card(2, 6, PATH + "Diamonds 6.png"));
		deck.addLast(new Card(2, 7, PATH + "Diamonds 7.png"));
		deck.addLast(new Card(2, 8, PATH + "Diamonds 8.png"));
		deck.addLast(new Card(2, 9, PATH + "Diamonds 9.png"));
		deck.addLast(new Card(2, 10, PATH + "Diamonds 10.png"));
		deck.addLast(new Card(2, 11, PATH + "Diamonds 11.png"));
		deck.addLast(new Card(2, 12, PATH + "Diamonds 12.png"));
		deck.addLast(new Card(2, 13, PATH + "Diamonds 13.png"));
		deck.addLast(new Card(2, 14, PATH + "Diamonds 1.png"));
		deck.addLast(new Card(3, 2, PATH + "Clubs 2.png"));
		deck.addLast(new Card(3, 3, PATH + "Clubs 3.png"));
		deck.addLast(new Card(3, 4, PATH + "Clubs 4.png"));
		deck.addLast(new Card(3, 5, PATH + "Clubs 5.png"));
		deck.addLast(new Card(3, 6, PATH + "Clubs 6.png"));
		deck.addLast(new Card(3, 7, PATH + "Clubs 7.png"));
		deck.addLast(new Card(3, 8, PATH + "Clubs 8.png"));
		deck.addLast(new Card(3, 9, PATH + "Clubs 9.png"));
		deck.addLast(new Card(3, 10, PATH + "Clubs 10.png"));
		deck.addLast(new Card(3, 11, PATH + "Clubs 11.png"));
		deck.addLast(new Card(3, 12, PATH + "Clubs 12.png"));
		deck.addLast(new Card(3, 13, PATH + "Clubs 13.png"));
		deck.addLast(new Card(3, 14, PATH + "Clubs 1.png"));
		deck.addLast(new Card(4, 2, PATH + "Spades 2.png"));
		deck.addLast(new Card(4, 3, PATH + "Spades 3.png"));
		deck.addLast(new Card(4, 4, PATH + "Spades 4.png"));
		deck.addLast(new Card(4, 5, PATH + "Spades 5.png"));
		deck.addLast(new Card(4, 6, PATH + "Spades 6.png"));
		deck.addLast(new Card(4, 7, PATH + "Spades 7.png"));
		deck.addLast(new Card(4, 8, PATH + "Spades 8.png"));
		deck.addLast(new Card(4, 9, PATH + "Spades 9.png"));
		deck.addLast(new Card(4, 10, PATH + "Spades 10.png"));
		deck.addLast(new Card(4, 11, PATH + "Spades 11.png"));
		deck.addLast(new Card(4, 12, PATH + "Spades 12.png"));
		deck.addLast(new Card(4, 13, PATH + "Spades 13.png"));
		deck.addLast(new Card(4, 14, PATH + "Spades 1.png"));
		
		return deck;
	}

}
