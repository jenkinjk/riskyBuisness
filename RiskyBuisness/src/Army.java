import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class Army {
	private Player owner;
	private Territory location;
	private int size;

	public Army(Player p, Territory t) {
		this.owner = p;
		this.location = t;
		this.size = 3;
	}

	public Army(Player p, Territory t, int i) {
		this.owner = p;
		this.location = t;
		this.size = i;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
	
	public void drawOn(Graphics g, Image soldier) {
		Graphics2D g2 = (Graphics2D) g;
		Point coordinates = this.location.getCoordinates();
		int x = coordinates.x;
		int y = coordinates.y;
		g2.setPaint(this.owner.getColor());
		g2.drawImage(soldier, x, y, 20, 40, null);
		g2.fillRect(x, y + 40, 20, 5);
		g2.drawString(Integer.toString(this.size), x, y);
	}

	public int size() {
		return this.size;
	}

	public void takeLosses(int loss) {
		if(loss >= this.size) {
			this.size = 0;
		} else {
			this.size = this.size - loss;
		}
	}

}
