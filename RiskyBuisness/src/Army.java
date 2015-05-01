import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Army extends JButton {
	private Player owner;
	private Territory location;
	private int size;
	
	private int x;
	private int y;
	private int w;
	private int h;

	public Army(Player p, Territory t) {
		this.owner = p;
		this.location = t;
		this.size = 3;
		
		this.x = t.getCoordinates().x;
		this.y = t.getCoordinates().y;
		this.w = 20;
		this.h = 40;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Army(Player p, Territory t, int i) {
		this.owner = p;
		this.location = t;
		this.size = i;
	}

	public int getArmySize() {
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

	public Territory getArmyLocation() {
		return location;
	}

	public void setLocation(Territory location) {
		this.location = location;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Point coordinates = this.location.getCoordinates();
		int x = coordinates.x;
		int y = coordinates.y;
		g2.setPaint(this.owner.getColor());
		g2.drawImage(soldier, x, y, 20, 40, null);
		g2.fillRect(x, y + 40, 20, 5);
		g2.drawString(Integer.toString(this.size), x, y);
	}

	public void takeLosses(int loss) {
		if(loss >= this.size) {
			this.size = 0;
		} else {
			this.size = this.size - loss;
		}
	}

}
