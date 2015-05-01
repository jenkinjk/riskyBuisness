import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AllTests{
	@Test
	public void armyListenerStorageTest() {
		RiskBoard board = new RiskBoard();
		board.initialGame(1);
		Army army = null;
		try {
			army = board.getArmy(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		army.doClick();
		//assertTrue(board.getBattleSetup().get(0).equals(army));
	}
}
