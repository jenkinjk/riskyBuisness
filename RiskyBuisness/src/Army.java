import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Army extends JComponent {
	private Player owner;
	private Territory location;

	public Army(Player p, Territory t) {
		this.owner = p;
		this.location = t;
		
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Territory getTerretory() {
		return location;
	}

	public void setLocation(Territory location) {
		this.location = location;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D armyUnit = new Ellipse2D.Double(100, 100, 10, 10);
		g2.setPaint(this.owner.getColor());
		g2.fill(armyUnit);
	}

}
