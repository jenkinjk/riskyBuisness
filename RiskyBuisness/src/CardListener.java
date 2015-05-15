import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class CardListener implements MouseListener {
	private Card card;
	private String type;
	
	static Image artilleryImage = null;
	static Image cavalryImage = null;
	static Image wariorImage = null;
	
	static Image scaledArtilleryImage = null;
	static Image scaledCavalryImage = null;
	static Image scaledWariorImage = null;
	
	private Icon artilleryIcon = null;
	private Icon cavalryIcon = null;
	private Icon wariorIcon = null; 
	
	public CardListener(Card c) {
		this.card = c;
		this.type = c.getType();
		assignProperIcon();
	}
	
	private void assignProperIcon() {
		if(this.type == "artillery") {
			if(artilleryImage == null) {
				try {
					artilleryImage = ImageIO.read(new File("artillery.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				scaledArtilleryImage = artilleryImage.getScaledInstance(100, 200, Image.SCALE_SMOOTH);
			}
			artilleryIcon = new ImageIcon(scaledArtilleryImage);
		} else if (this.type == "cavalry") {
			//Cavalry
			if(cavalryImage == null) {
				try {
					cavalryImage = ImageIO.read(new File("cavalry.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				scaledCavalryImage = cavalryImage.getScaledInstance(100, 200, Image.SCALE_SMOOTH);
			}
			cavalryIcon = new ImageIcon(scaledCavalryImage);
		} else {
			if(wariorImage == null) {
				try {
					wariorImage = ImageIO.read(new File("warior.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				scaledWariorImage = wariorImage.getScaledInstance(100, 200, Image.SCALE_SMOOTH);
			}
			wariorIcon = new ImageIcon(scaledWariorImage);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
