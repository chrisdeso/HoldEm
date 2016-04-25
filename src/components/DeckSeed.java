package components;
import java.util.Queue;

public class DeckSeed {
	
	final static String PATH = "resources/images/";
	
	public static Queue<Card> seedDeck(){
		Queue<Card> deck = null;
		deck.add(new Card(1, 2, PATH + "Hearts2.png"));
		deck.add(new Card(1, 3, PATH + "Hearts3.png"));
		deck.add(new Card(1, 4, PATH + "Hearts4.png"));
		deck.add(new Card(1, 5, PATH + "Hearts5.png"));
		deck.add(new Card(1, 6, PATH + "Hearts6.png"));
		deck.add(new Card(1, 7, PATH + "Hearts7.png"));
		deck.add(new Card(1, 8, PATH + "Hearts8.png"));
		deck.add(new Card(1, 9, PATH + "Hearts9.png"));
		deck.add(new Card(1, 10, PATH + "Hearts10.png"));
		deck.add(new Card(1, 11, PATH + "Hearts11.png"));
		deck.add(new Card(1, 12, PATH + "Hearts12.png"));
		deck.add(new Card(1, 13, PATH + "Hearts13.png"));
		deck.add(new Card(1, 14, PATH + "Hearts1.png"));
		deck.add(new Card(2, 2, PATH + "Diamonds2.png"));
		deck.add(new Card(2, 3, PATH + "Diamonds3.png"));
		deck.add(new Card(2, 4, PATH + "Diamonds4.png"));
		deck.add(new Card(2, 5, PATH + "Diamonds5.png"));
		deck.add(new Card(2, 6, PATH + "Diamonds6.png"));
		deck.add(new Card(2, 7, PATH + "Diamonds7.png"));
		deck.add(new Card(2, 8, PATH + "Diamonds8.png"));
		deck.add(new Card(2, 9, PATH + "Diamonds9.png"));
		deck.add(new Card(2, 10, PATH + "Diamonds10.png"));
		deck.add(new Card(2, 11, PATH + "Diamonds11.png"));
		deck.add(new Card(2, 12, PATH + "Diamonds12.png"));
		deck.add(new Card(2, 13, PATH + "Diamonds13.png"));
		deck.add(new Card(2, 14, PATH + "Diamonds1.png"));
		deck.add(new Card(3, 2, PATH + "Clubs2.png"));
		deck.add(new Card(3, 3, PATH + "Clubs3.png"));
		deck.add(new Card(3, 4, PATH + "Clubs4.png"));
		deck.add(new Card(3, 5, PATH + "Clubs5.png"));
		deck.add(new Card(3, 6, PATH + "Clubs6.png"));
		deck.add(new Card(3, 7, PATH + "Clubs7.png"));
		deck.add(new Card(3, 8, PATH + "Clubs8.png"));
		deck.add(new Card(3, 9, PATH + "Clubs9.png"));
		deck.add(new Card(3, 10, PATH + "Clubs10.png"));
		deck.add(new Card(3, 11, PATH + "Clubs11.png"));
		deck.add(new Card(3, 12, PATH + "Clubs12.png"));
		deck.add(new Card(3, 13, PATH + "Clubs13.png"));
		deck.add(new Card(3, 14, PATH + "Clubs1.png"));
		deck.add(new Card(4, 2, PATH + "Spades2.png"));
		deck.add(new Card(4, 3, PATH + "Spades3.png"));
		deck.add(new Card(4, 4, PATH + "Spades4.png"));
		deck.add(new Card(4, 5, PATH + "Spades5.png"));
		deck.add(new Card(4, 6, PATH + "Spades6.png"));
		deck.add(new Card(4, 7, PATH + "Spades7.png"));
		deck.add(new Card(4, 8, PATH + "Spades8.png"));
		deck.add(new Card(4, 9, PATH + "Spades9.png"));
		deck.add(new Card(4, 10, PATH + "Spades10.png"));
		deck.add(new Card(4, 11, PATH + "Spades11.png"));
		deck.add(new Card(4, 12, PATH + "Spades12.png"));
		deck.add(new Card(4, 13, PATH + "Spades13.png"));
		deck.add(new Card(4, 14, PATH + "Spades1.png"));
		
		return deck;
	}

}
