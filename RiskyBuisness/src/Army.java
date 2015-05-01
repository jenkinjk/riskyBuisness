import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private RiskBoard board;

	public Army(Player p, Territory t) {
		this.owner = p;
		this.location = t;
		this.size = 3;
		
		this.x = t.getCoordinates().x;
		this.y = t.getCoordinates().y;
		this.w = 15;
		this.h = 15;
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

	public Army(Player p, Territory t, RiskBoard riskBoard) {
		this.owner = p;
		this.location = t;
		this.board = riskBoard;
		this.size = 3;
	}
	
	public Army(Player p, Territory t, int size) {
		this.owner = p;
		this.location = t;
		this.board = null;
		this.size = size;
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
		double x = coordinates.x;
		double y = coordinates.y;
		Ellipse2D armyUnit = new Ellipse2D.Double(x, y, 15, 15);
		g2.setPaint(this.owner.getColor());
		g2.fill(armyUnit);
		g2.drawString(Integer.toString(this.size), coordinates.x, coordinates.y);
		this.setEnabled(true);
	}

	public void takeLosses(int loss) {
		if(loss >= this.size) {
			this.size = 0;
		} else {
			this.size = this.size - loss;
		}
	}

	public RiskBoard getBoard() {
		return this.board;
	}

}
