package components;

/**
 * Simple Pojo for Cards
 * @author Sean Clements, Christopher de Sousa
 *
 */
public class Card {

	// suit value and location to png image
	private int suit;
	private int value;
	private String location;
	
	public Card(int suit, int value, String location){
		this.suit = suit;
		this.value = value;
		this.location = location;
	}
	// Getters and Setters
	public int getSuit() {
		return suit;
	}
	public int getValue() {
		return value;
	}
	public String getLocation(){
		return location;
	}
}
