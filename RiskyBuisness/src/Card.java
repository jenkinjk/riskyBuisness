import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class Card extends JButton {
	private String type;
	private Player owner;
	private int x;
	private int y;
	private int w;
	private int h;

	public Card(String type, Player p) {
		this.type = type;
		this.owner = p;
		
		this.x = 10;
		this.y = 720;
		this.w = 70;
		this.h = 90;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public void drawOn(Graphics g, int x) {
		Graphics2D g2 = (Graphics2D) g;
		Container parent = this.getParent();
		
		//card icon
		Icon icon = this.getIcon();
		icon.paintIcon(parent, g2, x, y);
		
		//transparent background
		this.setContentAreaFilled(false);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
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
}
