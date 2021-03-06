import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ArmyListener implements ActionListener, MouseListener {
	private Army army;
	private Icon soldierIcon;
	private Icon soldierSelectedIcon;
	static Image soldierImage = null;
	static Image soldierSelectedImage = null;
	static Image scaledImage = null;
	static Image scaledSelectedImage = null;

	public ArmyListener(Army a) {
		this.army = a;
		if (soldierImage == null) {
			try {
				soldierImage = ImageIO.read(new File("soldier.png"));
				soldierSelectedImage = ImageIO.read(new File(
						"soldier_selected.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			scaledImage = soldierImage.getScaledInstance(20, 40,
					Image.SCALE_SMOOTH);
			scaledSelectedImage = soldierSelectedImage.getScaledInstance(20,
					40, Image.SCALE_SMOOTH);
		}
		soldierIcon = new ImageIcon(scaledImage);
		soldierSelectedIcon = new ImageIcon(scaledSelectedImage);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		RiskBoard board = army.getBoard();
		if (board.getPhase().equals("Combat Phase")) {
			if (board.getCurrentPlayer() == army.getOwner()
					&& board.getBattleSetup().isEmpty()) {
				board.getBattleSetup().add(0, army);
			} else {
				if (!board.getBattleSetup().get(0).equals(army)) {
					Battle battle = null;
					try {
						battle = new Battle(board.getBattleSetup().get(0), army);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						battle.display();
						board.getFrame().setVisible(false);
						battle.getFrame().revalidate();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				board.getBattleSetup().clear();
			}
		} else {
			if (board.getCurrentPlayer() == army.getOwner()
					&& board.getNumberDeployed() < board.getNumberAllowed()) {
				board.increaseNumberDeployed();
				board.updateMenuBar();
				army.setArmySize(army.getArmySize() + 1);
			}
		}
		if(this.army.getRootPane()!=null)this.army.getRootPane().repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton b = (JButton) e.getComponent();
		b.setIcon(soldierSelectedIcon);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getComponent();
		b.setIcon(soldierIcon);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mouseClicked(null);
	}

}
