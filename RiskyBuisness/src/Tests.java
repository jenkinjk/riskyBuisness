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
		RiskBoard board = new RiskBoard(5);
		board.setUp();
		assertEqual(board.getPlayers().getSize(),5);
	}
}