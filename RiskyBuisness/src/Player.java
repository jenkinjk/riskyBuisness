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

	public void removeCards(int i, int j, int k) {
//		System.out.println("Size: " + this.cards.size());
		int removeArt = i;
		int removeCav = j;
		int removeWar = k;
//		System.out.println("Inside method");
		while (removeArt > 0) {
			for (int a = 0; a < this.cards.size(); a++) {
				if (this.cards.get(a).getType().equals("artillery")) {
					this.cards.remove(a);
//					System.out.println("Removed art");
					removeArt--;
//					System.out.println(removeArt);
				}
			}
		}
		while (removeCav > 0) {
			for (int b = 0; b < this.cards.size(); b++) {
				if (this.cards.get(b).getType().equals("cavalry")) {
					this.cards.remove(b);
//					System.out.println("Removed cav");
					removeCav--;
//					System.out.println(removeCav);
				}
			}
		}
		while (removeWar > 0) {
			for (int c = 0; c < this.cards.size(); c++) {
				if (this.cards.get(c).getType().equals("warior")) {
					this.cards.remove(c);
//					System.out.println("Removed war");
					removeWar--;
//					System.out.println(removeWar);
				}
			}
		}
	}

//	public ArrayList<Army> getArmies() {
//		return this.armies;
//	}
}
