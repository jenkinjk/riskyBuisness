import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//Just a data class for now. Won't stay that way!
public class Player {

	private String name;
	private Color color;
	private int numberOfTerritories;
	private int numberOfArmies;
	private int numberOfCards;
	private ArrayList<Territory> territories;
	private ArrayList<Army> armies;
	private ArrayList<Card> cards;
	

	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
		this.numberOfTerritories = 0;
		this.numberOfArmies = 0;
		this.numberOfCards = 0;
		this.territories = new ArrayList<Territory>();
		this.armies = new ArrayList<Army>();
		this.cards = new ArrayList<Card>();
	}

	public void addTerritory(Territory t) {
		this.territories.add(t);
		this.numberOfTerritories++;
	}

	public void addArmy(Army a) {
		this.armies.add(a);
		this.numberOfArmies++;
	}
	
	public void addCard(Card c) {
		this.cards.add(c);
		this.numberOfCards++;
	}

	public String getName() {
		return this.name;
	}

	public Color getColor() {
		return this.color;
	}

	public int getNumberOfTerritories() {
		return this.numberOfTerritories;
	}

	public int getNumberOfArmies() {
		return this.numberOfArmies;
	}

	public ArrayList<Territory> getTerritories() {
		return this.territories;
	}

	public void removeTerritory(Territory armyLocation) {
		this.territories.remove(armyLocation);
		this.numberOfTerritories--;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

//	public ArrayList<Army> getArmies() {
//		return this.armies;
//	}
}
