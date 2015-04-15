import static org.junit.Assert.*;

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
		ContinentListener c = new ContinentListener("Asia", board);
		c.setUpTerritories();
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

	// This is unsatisfactorily tested. It only tests if there are exactly 5
	// players. Should be parameterized.

	@Test
	public void setupTerritoriesRandomlyTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		RiskBoard board2 = new RiskBoard();
		board2.initialGame(fInput);
		ContinentListener c = new ContinentListener("Asia", board);
		c.setUpTerritories();
		ArrayList<Player> players = board.getPlayers();
		ContinentListener c2 = new ContinentListener("Asia", board2);
		c2.setUpTerritories();
		ArrayList<Player> players2 = board2.getPlayers();
		int count = 0;
		boolean failing = true;
		while (failing) {
			for (int i = 0; i < fInput; i++) {
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
						}
					}
				}
			}
			if (count >= 100)
				break;
			count++;
		}
		assertFalse(failing);
	}



	@Test
	public void displayBoardTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.display();
		// Note, since this is GUI stuff it mostly is better to test by hand.
		// This just makes sure the GUI throws no errors.
	}

	@Test
	public void listenerTest() {
		RiskBoard board = new RiskBoard();
		ContinentListener riskyBuisness = new ContinentListener(
				"Risky Buisness", board);
		assertEquals("I am Risky Buisness. Fear me!\n",
				riskyBuisness.identity());
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
		for(int i = 0; i<fInput;i++){
			assertEquals("Player "+numToNum.get(i+1),board.getNextPlayer().getName());
		}
		assertEquals("Player "+numToNum.get(1),board.getNextPlayer().getName());
	}
	
	@Test
	public void allTerriories(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(42,board.getTerritories().size());
	}
	@Test
	public void AsiaTerriories(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(12,board.getAsiaTerritories().size());
	}
	@Test
	public void EuropeTerriories(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(7,board.getEuropeTerritories().size());
	}
	@Test
	public void NATerriories(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(9,board.getNATerritories().size());
	}
	@Test
	public void SATerriories(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(4,board.getSATerritories().size());
	}
	@Test
	public void AustrailaTerriories(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(4,board.getAustralaTerritories().size());
	}	@Test
	public void AfricaTerriories(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		assertEquals(6,board.getAfricaTerritories().size());
	}
}