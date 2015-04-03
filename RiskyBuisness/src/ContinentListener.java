import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ContinentListener implements ActionListener {
	private String label;
	private RiskBoard board;
	private String[] asianCountries = { "Afghanistan", "China", "India",
			"Irkutsk", "Japan", "Kamchatka", "Middle East", "Mongolia", "Siam",
			"Siberia", "Ural", "Yakutsk" };
	private String[] europeCountries = { "" };
	//private String[] arcticCountries = { "" };
	private String[] naCountries = { "" };
	private String[] saCountries = { "" };
	private String[] africaCountries = { "" };
	private String[] aussieCountries = { "" };
	
	private Map<String, String[]> continentToCountriesMap;

	public ContinentListener(String label, RiskBoard board) {
		this.label = label;
		this.board = board;
		initializeMap();
	}

	private void initializeMap() {
		this.continentToCountriesMap = new HashMap<String, String[]>();
		this.continentToCountriesMap.put("Asia", this.asianCountries);
		this.continentToCountriesMap.put("Africa", this.africaCountries);
		this.continentToCountriesMap.put("Australia", this.aussieCountries);
		this.continentToCountriesMap.put("Europe", this.europeCountries);
		this.continentToCountriesMap.put("North America", this.naCountries);
		this.continentToCountriesMap.put("South America", this.saCountries);
		//this.continentToCountriesMap.put("Asia", this.arcticCountries);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.printf(identity());
		setUpTerritories();
	}

	public void setUpTerritories() {
		for(String country: asianCountries) {
			Territory t = new Territory(country);
			board.getNextPlayer().addTerritory(t);
		}
	}

	public String identity() {
		return String.format("I am %s. Fear me!\n", label);
	}
}
