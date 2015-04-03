import java.util.ArrayList;

//Just a data class for now. Won't stay that way!
public class Player {

	private String name;
	private int numberOfTerritories;
	private ArrayList<Territory> territories;

	public Player(String string) {
		this.name = string;
		this.numberOfTerritories = 0;
		this.territories = new ArrayList<Territory>();
	}
	
	public void addTerritoty(Territory t) {
		this.territories.add(t);
		this.numberOfTerritories++;
	}

	public String getName() {
		return this.name;
	}
	
	public int getNumberOfTerritories() {
		return this.numberOfTerritories;
	}
	
	public ArrayList<Territory> getTerritories() {
		return this.territories;
	}

}
