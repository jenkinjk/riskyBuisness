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

	@Test
	public void setupTerritoriesTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(5);
		ContinentListener c = new ContinentListener("Asia", board);
		c.setUpTerritories();
		
		ArrayList<Player> players = board.getPlayers();
		assertEquals(players.get(0).getNumberOfTerritories(), 3);
		assertEquals(players.get(1).getNumberOfTerritories(), 3);
		assertEquals(players.get(2).getNumberOfTerritories(), 2);
		assertEquals(players.get(3).getNumberOfTerritories(), 2);
		assertEquals(players.get(4).getNumberOfTerritories(), 2);
	}

}