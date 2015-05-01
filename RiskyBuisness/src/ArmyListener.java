import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


public class ArmyListener implements MouseListener {
	private Army army;
	
	public ArmyListener(Army a) {
		this.army = a;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Owner: " + this.army.getOwner().getName()
				+ " Territory: " + this.army.getArmyLocation().getName());
		RiskBoard board = army.getBoard();
		if(board.getBattleSetup().isEmpty()) board.getBattleSetup().add(0, army);
		else{
			if(!board.getBattleSetup().get(0).equals(army)){
				Battle battle = null;
				try {
					battle = new Battle(board.getBattleSetup().get(0), army);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					battle.display();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			board.getBattleSetup().remove(0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
