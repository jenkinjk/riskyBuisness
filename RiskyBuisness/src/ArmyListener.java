import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ArmyListener implements MouseListener {
	private Army army;
	private Icon soldierIcon;
	private Icon soldierSelectedIcon;
	
	public ArmyListener(Army a) {
		this.army = a;
		Image soldierImage = null;
		Image soldierSelectedImage = null;
		try {
			soldierImage = ImageIO.read(new File("soldier.png"));
			soldierSelectedImage = ImageIO.read(new File("soldier_selected.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image scaledImage = soldierImage.getScaledInstance(20, 40, Image.SCALE_SMOOTH);
		Image scaledSelectedImage = soldierSelectedImage.getScaledInstance(20, 40, Image.SCALE_SMOOTH);
		soldierIcon = new ImageIcon(scaledImage);
		soldierSelectedIcon = new ImageIcon(scaledSelectedImage);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Owner: " + this.army.getOwner().getName()
				+ " Territory: " + this.army.getArmyLocation().getName());
		RiskBoard board = army.getBoard();
		if (board.getPhase().equals("Combat Phase")) {
			if (board.getBattleSetup().isEmpty())
				board.getBattleSetup().add(0, army);
			else {
				if (!board.getBattleSetup().get(0).equals(army)) {
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
		}else{
			army.setArmySize(army.getArmySize()+1);
			army.repaint();
			System.out.println(army.getArmySize());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JButton b = (JButton) arg0.getComponent();
		b.setIcon(soldierSelectedIcon);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JButton b = (JButton) arg0.getComponent();
		b.setIcon(soldierIcon);
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
