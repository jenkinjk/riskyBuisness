import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class Tests {

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
		board.initialGame(5);
		Player player1 = new Player("Player One");
		Player player2 = new Player("Player Two");
		assertEquals(player1.getName(), board.getNextPlayer().getName());
		assertEquals(player2.getName(), board.getNextPlayer().getName());
		board.getNextPlayer();
		board.getNextPlayer();
		Player player5 = new Player("Player Five");
		assertEquals(player5.getName(), board.getNextPlayer().getName());
		assertEquals(player1.getName(), board.getNextPlayer().getName());
	}

	// This is unsatisfactorily tested. It only tests if there are exactly 5
	// players. Should be generalized and parameterized.
	@Test
	public void setupTerritoriesEquallyTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(5);
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
		board.initialGame(5);
		RiskBoard board2 = new RiskBoard();
		board2.initialGame(5);
		ContinentListener c = new ContinentListener("Asia", board);
		c.setUpTerritories();
		ArrayList<Player> players = board.getPlayers();
		ContinentListener c2 = new ContinentListener("Asia", board2);
		c2.setUpTerritories();
		ArrayList<Player> players2 = board2.getPlayers();
		int count = 0;
		boolean failing = true;
		while (failing) {
			for (int i = 0; i < 5; i++) {
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

}