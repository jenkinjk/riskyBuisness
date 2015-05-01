import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ArmyListener implements MouseListener {
	private Army army;
	
	public ArmyListener(Army a) {
		this.army = a;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Owner: " + this.army.getOwner().getName()
				+ " Territory: " + this.army.getArmyLocation().getName());
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
