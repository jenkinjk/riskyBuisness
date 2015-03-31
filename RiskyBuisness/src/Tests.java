import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class Tests {

	@Test
	public void displayBoardTest() throws IOException {
		RiskBoard board = new RiskBoard();
		board.display();
		//Note, since this is GUI stuff it mostly is better to test by hand.
	}
	
	@Test
	public void listenerTest() {
		ContinentListener riskyBuisness = new ContinentListener("Risky Buisness");
		assertEquals("I am Risky Buisness. Fear me!\n", riskyBuisness.identity());
	}

	@Test
	public void setupTest() {
		RiskBoard board = new RiskBoard();
		board.setUp();
		//Part of the set up is players
		assertEquals(board.getPlayers().size(),5);
		assertEquals(board.getPlayers().get(0).getName(),"Player One");
		assertEquals(board.getPlayers().get(4).getName(),"Player Five");
		Player player1 = new Player("Player One");
		Player player2 = new Player("Player Two");
		assertEquals(board.getNextPlayer().getName(),player1.getName());
		assertEquals(board.getNextPlayer().getName(),player2.getName());
		board.getNextPlayer();
		board.getNextPlayer();
		Player player5 = new Player("Player Five");
		assertEquals(board.getNextPlayer().getName(),player5.getName());
		assertEquals(board.getNextPlayer().getName(),player1.getName());
		//Part of set up is the initialization of territories
		assertEquals(board.getTerritories().size(),25);
	}
	
	
}