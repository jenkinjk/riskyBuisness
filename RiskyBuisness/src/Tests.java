import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

@RunWith(org.junit.runners.Parameterized.class)
public class Tests {
	private int fInput;

	@Parameters
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { { 1 }, { 2 }, { 3 }, { 4 },
				{ 5 }, { 6 } });
	}

	public Tests(int input) {
		fInput = input;
	}

	@Test
	public void setupTerritoriesEquallyTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Player> players = board.getPlayers();
		assertTrue(playersEquallyDiveded(players));
	}
	
	private boolean playersEquallyDiveded(ArrayList<Player> players) {
		for (Player p : players) {
			for (Player p2 : players) {
				if (!(p.getNumberOfTerritories() == p2.getNumberOfTerritories()
						|| p.getNumberOfTerritories() == p2
								.getNumberOfTerritories() + 1 || p
						.getNumberOfTerritories() + 1 == p2
						.getNumberOfTerritories())) {
					return false;
				}
			}
		}
		return true;
	}

	@Test
	public void playersEquallyDivededTest() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player p = new Player(null, null);
		Player p2 = new Player(null, null);
		p.addTerritory(new Territory("Test"));
		p.addTerritory(new Territory("Test2"));
		p.addTerritory(new Territory("Test3"));
		players.add(p);
		players.add(p2);
		assertFalse(playersEquallyDiveded(players));
	}

	@Test
	public void setupTerritoriesRandomlyTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		RiskBoard board2 = new RiskBoard();
		board2.initialGame(fInput);
		ArrayList<Player> players = board.getPlayers();
		ArrayList<Player> players2 = board2.getPlayers();
		boolean failing = true;
		for (int k = 0; k < 100; k++) {
			for (int i = 0; i < fInput; i++) {
				failing = niceName(players, players2, failing, i);
			}
		}
		assertFalse(failing);
	}

	private boolean niceName(ArrayList<Player> players,
			ArrayList<Player> players2, boolean failing, int i) {
		ArrayList<Territory> territories = players.get(i)
				.getTerritories();
		ArrayList<Territory> territories2 = players2.get(i)
				.getTerritories();
		if (territories.size() > territories2.size()
				|| territories.size() < territories2.size()) {
			failing = false;
		} else {
			for (int j = 0; j < territories.size(); j++) {
				if (!territories2.contains(territories.get(j))) {
					failing = false;
					break;
				} else
					; // Do nothing. Just need to catch for coverage.
			}
		}
		return failing;
	}

	@Test
	public void displayBoardTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		board.display();
		// Note, since this is GUI stuff it mostly is better to test by hand.
		// This just makes sure the GUI throws no errors.
	}

	@Test
	public void setupPlayerTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(5);
		assertEquals(board.getPlayers().size(), 5);
		assertEquals(board.getPlayers().get(0).getName(), "Player One");
		assertEquals(board.getPlayers().get(4).getName(), "Player Five");
	}

	@Test
	public void nextPlayerTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		HashMap<Integer, String> numToNum = new HashMap<Integer, String>();
		numToNum.put(1, "One");
		numToNum.put(2, "Two");
		numToNum.put(3, "Three");
		numToNum.put(4, "Four");
		numToNum.put(5, "Five");
		numToNum.put(6, "Six");
		assertEquals("Player " + numToNum.get(1), board.getCurrentPlayer()
				.getName());
		for (int i = 1; i < fInput; i++) {
			assertEquals("Player " + numToNum.get(i + 1), board.getNextPlayer()
					.getName());
		}
		assertEquals("Player " + numToNum.get(1), board.getNextPlayer()
				.getName());
	}

	@Test
	public void allTerriories() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(42, board.getTerritories().size());
	}

	@Test
	public void AsiaTerriories() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(12, board.getAsiaTerritories().size());
	}

	@Test
	public void EuropeTerriories() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(7, board.getEuropeTerritories().size());
	}

	@Test
	public void NATerriories() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(9, board.getNATerritories().size());
	}

	@Test
	public void SATerriories() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(4, board.getSATerritories().size());
	}

	@Test
	public void AustrailaTerriories() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(4, board.getAustralaTerritories().size());
	}

	@Test
	public void AfricaTerriories() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(6, board.getAfricaTerritories().size());
	}

	@Test
	public void getNeighborsExists() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		@SuppressWarnings("unused")
		ArrayList<Territory> neighbors = board.getTerritories().get(0)
				.getNeighbors();
		// Should just run. Auto passes if no errors.
	}

	@Test
	public void getNeighborsAfghanistan() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Afghanistan")
				.getNeighbors();
		ArrayList<Territory> expectedNeighbors = new ArrayList<Territory>();
		expectedNeighbors.add(board.getTerritoryNamed("Ukraine"));
		expectedNeighbors.add(board.getTerritoryNamed("Middle East"));
		expectedNeighbors.add(board.getTerritoryNamed("India"));
		expectedNeighbors.add(board.getTerritoryNamed("China"));
		expectedNeighbors.add(board.getTerritoryNamed("Ural"));
		assertEquals(neighbors, expectedNeighbors);
	}

	@Test
	public void getNeighborsMiddleEast() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Middle East")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Egypt")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Southern Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("India")));
		assertTrue(neighbors.size() == 5);
	}

	@Test
	public void getNeighborsIndia() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("India")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Middle East")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siam")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsSiam() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Siam")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("India")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Indonesia")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsChina() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("China")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("India")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siam")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ural")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.size() == 6);
	}

	@Test
	public void getNeighborsUral() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Ural")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsSiberia() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Siberia")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ural")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Yakutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsMongolia() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Mongolia")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Japan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size() == 5);
	}

	@Test
	public void getNeighborsJapan() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Japan")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size() == 2);
	}

	@Test
	public void getNeighborsIrkutsk() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Irkutsk")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Yakutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsYakutsk() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Yakutsk")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsKamchatka() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Kamchatka")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Yakutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alaska")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Japan")));
		assertTrue(neighbors.size() == 5);
	}

	@Test
	public void AsianNeighbors() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for (Territory t : board.getAsiaTerritories()) {
			assertTrue(t.getNeighbors() != null);
		}
	}

	@Test
	public void getNeighborsAlaska() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Alaska")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsNorthWestTerritory() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Northwest Territory").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Greenland")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alaska")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsAlberta() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Alberta")
				.getNeighbors();
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alaska")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsGreenland() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Greenland")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Iceland")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Quebec")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsOntario() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Ontario")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Greenland")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Quebec")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.size() == 6);
	}

	@Test
	public void getNeighborsQuebec() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Quebec")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Greenland")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsWesternUS() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Western United States").getNeighbors();
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Centeral America")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsEasternUS() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Eastern United States").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Quebec")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Centeral America")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsCenteralAmerica() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Centeral America").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Venezula")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void NANeighbors() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for (Territory t : board.getNATerritories()) {
			assertTrue(t.getNeighbors() != null);
		}
	}

	@Test
	public void getNeighborsUkraine() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Ukraine")
				.getNeighbors();
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Northen Europe")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Southern Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Scandinavia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Middle East")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ural")));
		assertTrue(neighbors.size() == 6);
	}

	@Test
	public void getNeighborsScandinavia() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Scandinavia")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Iceland")));
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Northen Europe")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsNorthEurope() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Northen Europe").getNeighbors();
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Southern Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Scandinavia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Western Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Great Britain")));
		assertTrue(neighbors.size() == 5);
	}

	@Test
	public void getNeighborsSouthEurope() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Southern Europe").getNeighbors();
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Western Europe")));
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Northen Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Middle East")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsWestEurope() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Western Europe").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("North Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Great Britain")));
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Northen Europe")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Southern Europe")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsGreatBritain() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Great Britain").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Iceland")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Scandinavia")));
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Northen Europe")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Southern Europe")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsIceland() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Iceland")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Scandinavia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Great Britain")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Greenland")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void EuropeNeighbors() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for (Territory t : board.getEuropeTerritories()) {
			assertTrue(t.getNeighbors() != null);
		}
	}

	@Test
	public void getNeighborsBrazil() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Brazil")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Venezula")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Peru")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Argentina")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("North Africa")));
		assertTrue(neighbors.size() == 4);
	}

	@Test
	public void getNeighborsPeru() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Peru")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Brazil")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Venezula")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Argentina")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsArgentina() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Argentina")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Brazil")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Peru")));
		assertTrue(neighbors.size() == 2);
	}

	@Test
	public void getNeighborsVenezuela() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Venezula")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Brazil")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Peru")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Centeral America")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void SANeighbors() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for (Territory t : board.getSATerritories()) {
			assertTrue(t.getNeighbors() != null);
		}
	}

	@Test
	public void getNeighborsCongo() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Congo")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("North Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("East Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("South Africa")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsEastAfrica() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("East Africa")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Congo")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("North Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("South Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Egypt")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Madagascar")));
		assertTrue(neighbors.size() == 5);
	}

	@Test
	public void getNeighborsEgypt() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Egypt")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Middle East")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("North Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("East Africa")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsMadagascar() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Madagascar")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("East Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("South Africa")));
		assertTrue(neighbors.size() == 2);
	}

	@Test
	public void getNeighborsNorthAfrica() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board
				.getTerritoryNamed("North Africa").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Congo")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("East Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Egypt")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Brazil")));
		assertTrue(neighbors
				.contains(board.getTerritoryNamed("Western Europe")));
		assertTrue(neighbors.size() == 5);
	}

	@Test
	public void getNeighborsSouthAfrica() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board
				.getTerritoryNamed("South Africa").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Congo")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("East Africa")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Madagascar")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void AfricaNeighbors() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for (Territory t : board.getAfricaTerritories()) {
			assertTrue(t.getNeighbors() != null);
		}
	}

	@Test
	public void getNeighborsEastAust() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Eastern Australia").getNeighbors();
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Western Australia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("New Guinea")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Indonesia")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsIndonesia() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Indonesia")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siam")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Western Australia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("New Guinea")));
		assertTrue(neighbors.size() == 3);
	}

	@Test
	public void getNeighborsNewGuinea() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("New Guinea")
				.getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Indonesia")));
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Eastern Australia")));
		assertTrue(neighbors.size() == 2);
	}

	@Test
	public void getNeighborsWestAust() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed(
				"Western Australia").getNeighbors();
		assertTrue(neighbors.contains(board
				.getTerritoryNamed("Eastern Australia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Indonesia")));
		assertTrue(neighbors.size() == 2);
	}

	@Test
	public void AustraliaNeighbors() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for (Territory t : board.getAustralaTerritories()) {
			assertTrue(t.getNeighbors() != null);
		}
	}

	@Test
	public void setUpArmyTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		/*
		 * Currently, 3 armies for each country need to improve algorithm
		 */
		ArrayList<Player> players = board.getPlayers();
		for (Player p : players) {
			// Refactored so that each "army" consists of a number of armies in
			// a territory. This makes figuring out whether an attack is legal
			// or not easier.
			assertEquals(p.getNumberOfTerritories(), p.getNumberOfArmies());
		}
	}

	@Test
	public void retrieveNonExistantTerritory() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		boolean failed = false;
		try {
			board.getTerritoryNamed("This is not a real name");
		} catch (Exception e) {
			failed = true;
		}
		assertTrue(failed);
	}

	@Test
	public void runMain() { // Just check to make sure no errors
		RiskMain main = new RiskMain();
		try {
			main.main(null);
		} catch (IOException e) {
			assertTrue(false);
		}
	}

	@Test
	public void BattleConstructor() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska,7);
		Army b = new Army(p2, Kamchatka);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
	}

	@Test
	public void BattleConstructorBadPlayers() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska);
		Army b = new Army(p1, Kamchatka);
		p1.addArmy(a);
		p1.addArmy(b);
		p1.addTerritory(Alaska);
		p1.addTerritory(Kamchatka);
		boolean errored = false;
		try {
			Battle battle = new Battle(a, b);
		} catch (Exception e) {
			errored = true;
		}
		assertTrue(errored);
	}

	@Test
	public void BattleConstructorBadTerritories() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Madagascar");
		Army a = new Army(p1, Alaska);
		Army b = new Army(p2, Kamchatka);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		boolean errored = false;
		try {
			Battle battle = new Battle(a, b);
		} catch (Exception e) {
			errored = true;
		}
		assertTrue(errored);
	}

	@Test
	public void BattleConstructorAttackSelfTerritory() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Alaska");
		Army a = new Army(p1, Alaska);
		Army b = new Army(p2, Kamchatka);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		boolean errored = false;
		try {
			Battle battle = new Battle(a, b);
		} catch (Exception e) {
			errored = true;
		}
		assertTrue(errored);
	}

	@Test
	public void BattleConstructorAttackLargerArmy() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 4);
		Army b = new Army(p2, Kamchatka, 6);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		boolean errored = false;
		try {
			Battle battle = new Battle(a, b);
		} catch (Exception e) {
			errored = true;
		}
		assertTrue(errored);
	}

//	@Test
//	public void BattleExecute() throws Exception {
//		RiskBoard board = new RiskBoard();
//		board.initialGame(fInput);
//		Player p1 = new Player("1", Color.green);
//		Player p2 = new Player("2", Color.red);
//		Territory Alaska = board.getTerritoryNamed("Alaska");
//		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
//		Army a = new Army(p1, Alaska);
//		Army b = new Army(p2, Kamchatka);
//		p1.addArmy(a);
//		p2.addArmy(b);
//		p1.addTerritory(Alaska);
//		p2.addTerritory(Kamchatka);
//		Battle battle = new Battle(a, b);
//		int attacker = 3, defender = 2;
//		battle.execute(attacker, defender);
//		assertTrue(b.size() == 0);
//		assertTrue(a.size() == 1);
//	}

//	@Test
//	public void BattleExecuteLarge() throws Exception {
//		RiskBoard board = new RiskBoard();
//		board.initialGame(fInput);
//		Player p1 = new Player("1", Color.green);
//		Player p2 = new Player("2", Color.red);
//		Territory Alaska = board.getTerritoryNamed("Alaska");
//		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
//		Army a = new Army(p1, Alaska, 7);
//		Army b = new Army(p2, Kamchatka, 5);
//		p1.addArmy(a);
//		p2.addArmy(b);
//		p1.addTerritory(Alaska);
//		p2.addTerritory(Kamchatka);
//		Battle battle = new Battle(a, b);
//		int attacker = 3, defender = 2;
//		battle.execute(attacker, defender);
//		assertTrue(b.size() == 2);
//		assertTrue(a.size() == 5);
//	}

//	@Test
//	public void BattleExecuteLargeSizeValidation() throws Exception {
//		RiskBoard board = new RiskBoard();
//		board.initialGame(fInput);
//		Player p1 = new Player("1", Color.green);
//		Player p2 = new Player("2", Color.red);
//		Territory Alaska = board.getTerritoryNamed("Alaska");
//		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
//		Army a = new Army(p1, Alaska, 70);
//		Army b = new Army(p2, Kamchatka, 50);
//		p1.addArmy(a);
//		p2.addArmy(b);
//		p1.addTerritory(Alaska);
//		p2.addTerritory(Kamchatka);
//		Battle battle = new Battle(a, b);
//		int attacker = 4, defender = 2;
//		boolean errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//
//		attacker = 1;
//		defender = 1;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertFalse(errored);
//		attacker = 1;
//		defender = 3;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 3;
//		defender = 2;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			// errored = true;
//		}
//		assertFalse(errored);
//		attacker = 2;
//		defender = 3;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 2;
//		defender = 1;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertFalse(errored);
//		attacker = 4;
//		defender = 3;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 4;
//		defender = 2;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 4;
//		defender = 1;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 0;
//		defender = 3;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 0;
//		defender = 2;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 0;
//		defender = 1;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		attacker = 3;
//		defender = 0;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		assertTrue(errored);
//		attacker = 2;
//		defender = 0;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//		assertTrue(errored);
//		attacker = 1;
//		defender = 0;
//		errored = false;
//		try {
//			battle.execute(attacker, defender);
//		} catch (Exception e) {
//			errored = true;
//		}
//		assertTrue(errored);
//	}

	@Test
	public void BattleExecuteLargeSizeValidation2() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 7);
		Army b = new Army(p2, Kamchatka, 5);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		int attacker = 3, defender = 3;
		boolean errored = false;
		try {
			battle.execute(attacker, defender);
		} catch (Exception e) {
			errored = true;
		}
		assertTrue(errored);
	}
	@Test
	public void BattleExecuteLargeSizeValidation5() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 7);
		Army b = new Army(p2, Kamchatka, 5);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		int attacker = 2, defender = 2;
		boolean errored = false;
		try {
			battle.execute(attacker, defender);
		} catch (Exception e) {
			errored = true;
		}
		assertFalse(errored);
	}
	@Test
	public void BattleExecuteLargeSizeValidation3() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 7);
		Army b = new Army(p2, Kamchatka, 5);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		int attacker = 1, defender = 2;
		boolean errored = false;
		try {
			battle.execute(attacker, defender);
		} catch (Exception e) {
			errored = true;
		}
		assertFalse(errored);
	}
	@Test
	public void BattleExecuteLargeSizeValidation4() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 7);
		Army b = new Army(p2, Kamchatka, 5);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		int attacker = 3, defender = 1;
		boolean errored = false;
		try {
			battle.execute(attacker, defender);
		} catch (Exception e) {
			errored = true;
		}
		assertFalse(errored);
	}
	@Test
	public void BattleExecuteSmallSizeValidation() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 2);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		int attacker = 3, defender = 1;
		boolean errored = false;
		try {
			battle.execute(attacker, defender);
		} catch (Exception e) {
			errored = true;
		}
		assertTrue(errored);
		a = new Army(p1, Alaska, 2);
		b = new Army(p2, Kamchatka, 1);
		attacker = 2;
		defender = 2;
		errored = false;
		try {
			battle.execute(attacker, defender);
		} catch (Exception e) {
			errored = true;
		}
		assertTrue(errored);
	}
	
	@Test
	public void BattleRandomTester() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 5);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		int attacker = 3, defender = 1;
		boolean errored = false;
		try {
			battle.execute(attacker, defender);
		} catch (Exception e) {
			errored = true;
		}
		assertFalse(errored);
		int originalArmiesLeftAttk = a.getArmySize();
		int originalArmiesLeftDef = b.getArmySize();
		int maxRuns = 100;
		int runs = 0;
		boolean failing = true;
		while (runs < maxRuns) {
			a = new Army(p1, Alaska, 5);
			b = new Army(p2, Kamchatka, 1);
			p1.addArmy(a);
			p2.addArmy(b);
			battle = new Battle(a, b);
			attacker = 3; 
			defender = 1;
			errored = false;
			try {
				battle.execute(attacker, defender);
			} catch (Exception e) {
				errored = true;
			}
			assertFalse(errored);
			if (a.getArmySize() != originalArmiesLeftAttk) {
				failing = false;
				break;
			}
			if (b.getArmySize() != originalArmiesLeftDef) {
				failing = false;
				break;
			}
		}
		assertFalse(failing);
	}
	
	@Test
	public void ArmyConstructer() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.RED);
		Player p2 = new Player("2", Color.YELLOW);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska);
		Army b = new Army(p2, Kamchatka);
	}
	@Test
	public void ArmyOwner() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.RED);
		Player p2 = new Player("2", Color.YELLOW);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska);
		Army b = new Army(p2, Kamchatka);
		assertEquals("1", a.getOwner().getName());
		assertEquals("2", b.getOwner().getName());
	}
	@Test
	public void ArmyLocation() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.RED);
		Player p2 = new Player("2", Color.YELLOW);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska);
		Army b = new Army(p2, Kamchatka);
		assertEquals("Alaska", a.getArmyLocation().getName());
		assertEquals("Kamchatka", b.getArmyLocation().getName());
	}
	@Test
	public void ArmySize() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.RED);
		Player p2 = new Player("2", Color.YELLOW);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska);
		Army b = new Army(p2, Kamchatka, 4);
		assertEquals(3, a.getArmySize());
		assertEquals(4, b.getArmySize());
	}
	@Test
	public void BattleDisplay() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 5);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		battle.display();
	}
	@Test
	public void BattleTitle() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 5);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		assertEquals("Battle between Jonathan and Zach in Kamchatka",battle.getTitle());
	}
	
	@Test
	public void BattleLargeAttackerDiceOptions() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 5);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		Integer[] options = { 1, 2, 3 };
		assertEquals(options,battle.getAttackerOptions());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void BattleBorderAttackerDiceOptions() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 3);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		Integer[] options = { 1, 2, 3 };
		assertEquals(options,battle.getAttackerOptions());
	}
	@Test
	public void BattleSmallAttackerDiceOptions() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 2);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		Integer[] options = { 1, 2 };
		assertEquals(options,battle.getAttackerOptions());
	}
	
	@Test
	public void BattleSmallDefenderDiceOptions() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 2);
		Army b = new Army(p2, Kamchatka, 1);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		Integer[] options = {  1 };
		assertEquals(options,battle.getDefenderOptions());
	}
	
	@Test
	public void BattleBorderDefenderDiceOptions() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 7);
		Army b = new Army(p2, Kamchatka, 2);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		Integer[] options = { 1, 2 };
		assertEquals(options,battle.getDefenderOptions());
	}
	@Test
	public void BattleLargeDefenderDiceOptions() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("Jonathan", Color.green);
		Player p2 = new Player("Zach", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 7);
		Army b = new Army(p2, Kamchatka, 3);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		Integer[] options = { 1, 2 };
		assertEquals(options,battle.getDefenderOptions());
	}
	@Test
	public void armyListenerConstructor() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.RED);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Army a = new Army(p1, Alaska);
		ArmyListener al = new ArmyListener(a);
	}
	
	@Test
	public void currentPlayerTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		HashMap<Integer, String> numToNum = new HashMap<Integer, String>();
		numToNum.put(1, "One");
		numToNum.put(2, "Two");
		numToNum.put(3, "Three");
		numToNum.put(4, "Four");
		numToNum.put(5, "Five");
		numToNum.put(6, "Six");
		String nextPlayer = "Player One";
		assertEquals(nextPlayer, board.getCurrentPlayer().getName());
		for (int i = 1; i < fInput; i++) {
			nextPlayer = board.getNextPlayer().getName();
			assertEquals("Player " + numToNum.get(i + 1) , nextPlayer);
			assertEquals("Player " + numToNum.get(i + 1), board.getCurrentPlayer().getName());
		}
	}
	
	@Test
	public void getLabelTextTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(board.getLabelText(), "Player One's Turn");
	}
	
	
	@Test
	public void getLabelAdvancingLogicTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		HashMap<Integer, String> numToNum = new HashMap<Integer, String>();
		numToNum.put(1, "One");
		numToNum.put(2, "Two");
		numToNum.put(3, "Three");
		numToNum.put(4, "Four");
		numToNum.put(5, "Five");
		numToNum.put(6, "Six");
		String nextPlayer = "Player One";
		assertEquals(nextPlayer, board.getCurrentPlayer().getName());
		board.endDeployment();
		board.endTurn();
		board.updateMenuBar();
		for (int i = 1; i < fInput; i++) {
			board.endDeployment();
			nextPlayer = board.getCurrentPlayer().getName();
			board.endTurn();
			assertEquals("Player " + numToNum.get(i + 1), nextPlayer);
			board.updateMenuBar();
			//System.out.println(fInput + " Next:" + nextPlayer);
			//assertEquals(board.getLabelText(), "Player " + numToNum.get(i + 1) + "'s Turn");
		}
	}
	
	@Test
	public void armyListenerStorageTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		board.endDeployment();
		Army army = null;
		try {
			army = board.getArmy(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		army.doClick();
		assertTrue(board.getBattleSetup().get(0).equals(army));
	}
	
	@Test
	public void armyListenerChangedMindTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Army army=null;
		try {
			army = board.getArmy(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		army.doClick();
		army.doClick();
		assertTrue(board.getBattleSetup().isEmpty());
	}
	@Test
	public void armyListenerBattleCreationTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		board.endDeployment();
		Army army = null;
		try {
			army = board.getArmy(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		army.doClick();
		assertTrue(board.getBattleSetup().get(0).equals(army));
	}
	
	@Test
	public void stateExists(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		if(fInput!=1){
		assertEquals(board.getPhase(), "Deployment Phase");
		}else{
			assertEquals(board.getPhase(), "Player One has Won!");
		}
	}
	
	@Test
	public void stateAdvances(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		if(fInput!=1){
		assertEquals(board.getPhase(), "Deployment Phase");
		board.endDeployment();
		assertEquals(board.getPhase(), "Combat Phase");
		board.endTurn();
		assertEquals(board.getPhase(), "Deployment Phase");
		assertEquals(board.getCurrentPlayer().getName(), "Player Two");
		}else{
			assertEquals(board.getPhase(), "Player One has Won!");
		}
	}
	
	@Test
	public void resolveBattle() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		Player p1 = new Player("1", Color.green);
		Player p2 = new Player("2", Color.red);
		Territory Alaska = board.getTerritoryNamed("Alaska");
		Territory Kamchatka = board.getTerritoryNamed("Kamchatka");
		Army a = new Army(p1, Alaska, 5);
		Army b = new Army(p2, Kamchatka, 0);
		p1.addArmy(a);
		p2.addArmy(b);
		p1.addTerritory(Alaska);
		p2.addTerritory(Kamchatka);
		Battle battle = new Battle(a, b);
		battle.attackerWon=true;
		battle.resolveCombat();
		battle.conquer(3);
		assertEquals(b.getOwner(), p1);
		assertEquals(b.getArmySize(), 3);
		assertEquals(p2.getTerritories(), new ArrayList<Territory>());
		assertEquals(p2.getNumberOfTerritories(), 0);
		assertEquals(a.getOwner(), p1);
		assertEquals(a.getArmySize(), 2);
		ArrayList<Territory> ts = new ArrayList<Territory>();
		ts.add(Alaska);
		ts.add(Kamchatka);
		assertEquals(p1.getTerritories(), ts);
		assertEquals(p1.getNumberOfTerritories(), 2);
	}
	
	@Test
	public void armyDeplymentGUI() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		if (fInput != 1) {
			Army a = board.getArmy(0);
			a.doClick();
			System.out.println(board.getPhase());
			assertEquals(4, a.getArmySize());
		} else {
			assertEquals(board.getPhase(), "Player One has Won!");
		}
	}
	
	@Test
	public void phaseChangeManagerConstructer() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		PhaseChangeManager pcm = new PhaseChangeManager(board);
	}
	
	@Test
	public void phaseChangeButtonTest() throws Exception {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		if(fInput!=1){
			assertEquals(board.getPhase(), "Deployment Phase");
			board.getPhaseChangeButton().doClick();
			assertEquals(board.getPhase(), "Combat Phase");
			board.getPhaseChangeButton().doClick();
			assertEquals(board.getPhase(), "Deployment Phase");
			assertEquals(board.getCurrentPlayer().getName(), "Player Two");
			} else {
				assertEquals(board.getPhase(), "Player One has Won!");
		}
	}
	
	@Test
	public void incrementArmiesDeployedTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(0, board.getNumberDeployed());
		board.increaseNumberDeployed();
		assertEquals(1, board.getNumberDeployed());
	}
	
	@Test
	public void allowedDeployedTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		int expect = board.getCurrentPlayer().getTerritories().size()/3;
		if(expect < 3) expect = 3;
		if(board.getPlayers().size()==1) expect = 38;
		assertEquals(expect, board.getNumberAllowed());
	}
	
	@Test
	public void accountForCountriesSATest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		try{
			board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed("Peru"));
			board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed("Brazil"));
			board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed("Argentina"));
			board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed("Venezula"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(board.getPlayers().size()!=1)
		assertEquals(2, board.accountForCountries());
	}
	
	@Test
	public void accountForCountriesNATest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		try{
			String[] naCountries = { "Alaska", "Alberta", "Centeral America",
					"Eastern United States", "Greenland", "Northwest Territory",
					"Ontario", "Quebec", "Western United States" };
			for(String name: naCountries){
				board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(board.getPlayers().size()!=1)
		assertEquals(5, board.accountForCountries());
	}
	
	@Test
	public void accountForCountriesETest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		try{
			String[] europeCountries = { "Great Britain", "Iceland",
					"Northen Europe", "Scandinavia", "Southern Europe", "Ukraine",
					"Western Europe" };
			for(String name: europeCountries){
				board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(board.getPlayers().size()!=1)
		assertEquals(5, board.accountForCountries());
	}
	
	@Test
	public void accountForCountriesAsiaTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		try{
			String[] asianCountries = { "Afghanistan", "China", "India", "Irkutsk",
					"Japan", "Kamchatka", "Middle East", "Mongolia", "Siam",
					"Siberia", "Ural", "Yakutsk" };
			for(String name: asianCountries){
				board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(board.getPlayers().size()!=1)
		assertEquals(7, board.accountForCountries());
	}
	
	@Test
	public void accountForCountriesAusTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		try{
			String[] australiaCountries = { "Eastern Australia", "Indonesia",
					"New Guinea", "Western Australia" };
			for(String name: australiaCountries){
				board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(board.getPlayers().size()!=1)
		assertEquals(2, board.accountForCountries());
	}
	
	@Test
	public void accountForCountriesAfrTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		try{
			String[] africaCountries = { "Congo", "East Africa", "Egypt",
					"Madagascar", "North Africa", "South Africa" };
			for(String name: africaCountries){
				board.getCurrentPlayer().getTerritories().add(board.getTerritoryNamed(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(board.getPlayers().size()!=1)
		assertEquals(3, board.accountForCountries());
	}
	
	@Test
	public void accountForMultiContinentsTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(1);
		assertEquals(24, board.accountForCountries());
	}
	
	@Test
	public void cardsClassConstructorTest() throws IOException {
		Player p = new Player("test", Color.BLACK);
		Card cavalry = new Card("cavalry", p);
		assertTrue(cavalry != null);
	}
	
	@Test
	public void setUpCardTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for(int i=0; i < fInput; i++) {
			assertEquals(3, board.getPlayers().get(i).getCards().size());
		}
	}
	
	@Test
	public void removeCardsTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		board.getPlayers().get(0).removeCards(1, 1, 1,board);
		assertEquals(board.getPlayers().get(0).getCards().size(), 0);
	}
	
	@Test
	public void removeThreeTest() {
		removeThreeRunner("artillery", 3, 0, 0);
		removeThreeRunner("cavalry", 0, 3, 0);
		removeThreeRunner("warior", 0, 0, 3);
	}
	
	private void removeThreeRunner(String name, int i, int j, int k) {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		
		Card c1 = new Card(name, board.getPlayers().get(0));
		Card c2 = new Card(name, board.getPlayers().get(0));
		Card c3 = new Card(name, board.getPlayers().get(0));
		c1.addMouseListener(new CardListener(c1));
		board.getPlayers().get(0).addCard(c3);
		board.getPlayers().get(0).addCard(c2);
		board.getPlayers().get(0).removeCards(i, j, k, board);
		assertEquals(board.getPlayers().get(0).getCards().size(), 2);
	}
}