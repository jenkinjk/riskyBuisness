import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Army {
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

	public Territory getLocation() {
		return location;
	}

	public void setLocation(Territory location) {
		this.location = location;
	}
	
	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Point coordinates = this.location.getCoordinates();
		double x = coordinates.x;
		double y = coordinates.y;
		Ellipse2D armyUnit = new Ellipse2D.Double(x, y, 15, 15);
		g2.setPaint(this.owner.getColor());
		g2.fill(armyUnit);
	}

}
