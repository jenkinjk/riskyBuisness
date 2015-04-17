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
	
	@Test
	public void getNeighborsExists(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritories().get(0).getNeighbors();
		//Should just run. Auto passes if no errors.
	}
	@Test
	public void getNeighborsAfghanistan() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Afghanistan").getNeighbors();
		ArrayList<Territory> expectedNeighbors = new ArrayList<Territory>();
		expectedNeighbors.add(board.getTerritoryNamed("Ukraine"));
		expectedNeighbors.add(board.getTerritoryNamed("Middle East"));
		expectedNeighbors.add(board.getTerritoryNamed("India"));
		expectedNeighbors.add(board.getTerritoryNamed("China"));
		expectedNeighbors.add(board.getTerritoryNamed("Ural"));
		assertEquals(neighbors, expectedNeighbors);
	}
	@Test
	public void getNeighborsMiddleEast() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Middle East").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Egypt")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Southern Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("India")));
		assertTrue(neighbors.size()==5);
	}
	@Test
	public void getNeighborsIndia() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("India").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Middle East")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siam")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsSiam() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Siam").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("India")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Indonesia")));
		assertTrue(neighbors.size()==3);
	}
	@Test
	public void getNeighborsChina() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("China").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("India")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siam")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ural")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.size()==6);
	}
	@Test
	public void getNeighborsUral() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Ural").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsSiberia() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Siberia").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ural")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Yakutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsMongolia() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Mongolia").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("China")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Japan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size()==5);
	}
	@Test
	public void getNeighborsJapan() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Japan").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size()==2);
	}
	@Test
	public void getNeighborsIrkutsk() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Irkutsk").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Yakutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsYakutsk() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Yakutsk").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Siberia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.size()==3);
	}
	@Test
	public void getNeighborsKamchatka() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Kamchatka").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Mongolia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Yakutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alaska")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Irkutsk")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Japan")));
		assertTrue(neighbors.size()==5);
	}
	@Test
	public void AsianNeighbors(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for(Territory t: board.getAsiaTerritories()){
			assertTrue(t.getNeighbors()!=null);
		}
	}
	@Test
	public void getNeighborsAlaska() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Alaska").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Kamchatka")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.size()==3);
	}
	@Test
	public void getNeighborsNorthWestTerritory() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Northwest Territory").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Greenland")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alaska")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsAlberta() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Alberta").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alaska")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsGreenland() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Greenland").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Iceland")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Quebec")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsOntario() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Ontario").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Greenland")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Northwest Territory")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Quebec")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.size()==6);
	}
	@Test
	public void getNeighborsQuebec() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Quebec").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Greenland")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.size()==3);
	}
	@Test
	public void getNeighborsWesternUS() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Western United States").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Centeral America")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Alberta")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsEasternUS() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Eastern United States").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Quebec")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ontario")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Centeral America")));
		assertTrue(neighbors.size()==4);
	}
	@Test
	public void getNeighborsCenteralAmerica() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Centeral America").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Venezula")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Western United States")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Eastern United States")));
		assertTrue(neighbors.size()==3);
	}
	@Test
	public void NANeighbors(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for(Territory t: board.getNATerritories()){
			assertTrue(t.getNeighbors()!=null);
		}
	}
	
	@Test
	public void getNeighborsUkraine() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Ukraine").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Northen Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Southern Europe")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Scandinavia")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Middle East")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Afghanistan")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ural")));
		assertTrue(neighbors.size()==6);
	}
	
	@Test
	public void getNeighborsScandinavia() throws Exception{
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		ArrayList<Territory> neighbors = board.getTerritoryNamed("Scandinavia").getNeighbors();
		assertTrue(neighbors.contains(board.getTerritoryNamed("Ukraine")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Iceland")));
		assertTrue(neighbors.contains(board.getTerritoryNamed("Northen Europe")));
		assertTrue(neighbors.size()==3);
	}
	
	@Test
	public void EuropeNeighbors(){
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		for(Territory t: board.getEuropeTerritories()){
			assertTrue(t.getNeighbors()!=null);
		}
	}
	
	@Test
	public void setUpArmyTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(fInput);
		
//		System.out.println("Case " + fInput);
//		ArrayList<Player> players = board.getPlayers();
//		for(Player p: players) {
//			System.out.println("Player "+ p.getName() + " has " + p.getNumberOfTerritories() + "territories.");
//			System.out.println("He occupies " + p.listTerritories());
//			
//		}
//		System.out.println();
//		System.out.println();
		
		/* 
		 * Currently, 3 armies for each country
		 * need to improve algorithm
		 */
		ArrayList<Player> players = board.getPlayers();
		for(Player p: players) {
			assertEquals(3 * p.getNumberOfTerritories(), p.getNumberOfArmies());
		}
	}
}