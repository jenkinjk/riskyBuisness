import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
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
		ContinentListener riskyBuisness = new ContinentListener(
				"Risky Buisness");
		assertEquals("I am Risky Buisness. Fear me!\n",
				riskyBuisness.identity());
	}

	@Test
	public void setupPlayerTest() {
		RiskBoard board = new RiskBoard();
		// Part of the set up is players. Note for now we are assuming 5
		// players.
		assertEquals(board.getPlayers().size(), 5);
		assertEquals(board.getPlayers().get(0).getName(), "Player One");
		assertEquals(board.getPlayers().get(4).getName(), "Player Five");
		Player player1 = new Player("Player One");
		Player player2 = new Player("Player Two");
		assertEquals(board.getNextPlayer().getName(), player1.getName());
		assertEquals(board.getNextPlayer().getName(), player2.getName());
		board.getNextPlayer();
		board.getNextPlayer();
		Player player5 = new Player("Player Five");
		assertEquals(board.getNextPlayer().getName(), player5.getName());
		assertEquals(board.getNextPlayer().getName(), player1.getName());
	}

	@Test
	public void setupTerritoriesTest() {
		RiskBoard board = new RiskBoard();
		// Part of set up is the initialization of territories. Note that for
		// now we are simply hardcoding in 25 territories
		ArrayList<Territory> territories = board.getTerritories();
		assertEquals(territories.size(), 25);
		assertEquals(territories.get(0).getClass(), new Territory(
				"Name").getClass());
		assertEquals(territories.get(0).getName(), "1");
		assertEquals(territories.get(24).getName(), "25");
		int player1cnt = 0,player2cnt = 0,player3cnt = 0,player4cnt = 0,player5cnt = 0;
		for(Territory t: territories){
		assertNotNull(t.getPlayer());
		if(t.getPlayer().equals("Player One")){
			player1cnt++;
		}
		if(t.getPlayer().equals("Player Two")){
			player2cnt++;
		}
		if(t.getPlayer().equals("Player Three")){
			player3cnt++;
		}
		if(t.getPlayer().equals("Player Four")){
			player4cnt++;
		}
		if(t.getPlayer().equals("Player Five")){
			player5cnt++;
		}
		}
		assertEquals(player1cnt, 5);
		assertEquals(player2cnt, 5);
		assertEquals(player3cnt, 5);
		assertEquals(player4cnt, 5);
		assertEquals(player5cnt, 5);
	}

}