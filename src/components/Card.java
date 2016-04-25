package components;

public class Card {

	private int suit;
	private int value;
	private String location;
	
	public Card(int suit, int value, String location){
		this.suit = suit;
		this.value = value;
		this.location = location;
	}
	
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
